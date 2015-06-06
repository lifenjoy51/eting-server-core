package me.eting.server.core.service.reply.push;

import com.relayrides.pushy.apns.ApnsEnvironment;
import com.relayrides.pushy.apns.PushManager;
import com.relayrides.pushy.apns.PushManagerFactory;
import com.relayrides.pushy.apns.util.ApnsPayloadBuilder;
import com.relayrides.pushy.apns.util.SimpleApnsPushNotification;
import com.relayrides.pushy.apns.util.TokenUtil;
import me.eting.common.domain.reply.Reply;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * Created by lifenjoy51 on 2015-02-28.
 */
@Component
public class ApnsService extends PushService {

    @Autowired
    ApplicationContext context;

    private volatile PushManager<SimpleApnsPushNotification> pushManager = null;

    @PostConstruct
    private void init() {
        PushManagerFactory<SimpleApnsPushNotification> pushManagerFactory = null;

        //System.out.println("loadService");
        Resource certResource = context.getResource("classpath:cert/eting_apns_cert_dis.p12");
        // final String certPath = rootPath + File.separator + "cert"+
        // File.separator + "eting_apns_cert_passwd.p12";
        final String certPassword = "nexters";
        // final String certPassword = "eting00";

        try {
            String certPath = certResource.getFile().getAbsolutePath();
            pushManagerFactory = new PushManagerFactory<SimpleApnsPushNotification>(
                    ApnsEnvironment.getSandboxEnvironment(),
                    PushManagerFactory.createDefaultSSLContext(certPath,
                            certPassword));
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            //TODO e.printStackTrace();
        }

        try {
            pushManager = pushManagerFactory.buildPushManager();

            pushManager.start();
        } catch (Exception e) {
            //TODO 임시처리...
        }
    }

    /**
     * 전송!!
     *
     * @param deviceToken
     * @param jsonKey
     * @param jsonString
     * @param alert
     */
    private void sendApns(String deviceToken, String jsonKey, String jsonString,
                          String alert) {

        final byte[] token = TokenUtil.tokenStringToByteArray(deviceToken);

        final ApnsPayloadBuilder payloadBuilder = new ApnsPayloadBuilder();

        payloadBuilder.setBadgeNumber(0);

        payloadBuilder.setAlertBody(alert);
        payloadBuilder.addCustomProperty(jsonKey, jsonString);

        final String payload = payloadBuilder.buildWithDefaultMaximumLength();

        try {
            pushManager.getQueue().put(
                    new SimpleApnsPushNotification(token, payload));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        pushManager.shutdown();
    }

    @Override
    public void push(Reply reply) {

        //TODO key값 변경 필요함.
        JSONObject obj = new JSONObject();
        obj.put("type", "Stamp");
        obj.put("story_id", reply.getExchangedStory().getStory().getId());
        obj.put("stamps", reply.getEmoticon().toString());
        obj.put("comment", reply.getContent());
        obj.put("comment_id", reply.getId());
        try {
            sendApns(reply.getIncognito().getDevice().getPushKey(), "reply", obj.toString(), "eting!!");
        } catch (IllegalArgumentException iae) {
            //TODO apns의 경우 payload길이가 정해져있다. 추후 조치가 필요함..
        }
    }
}
