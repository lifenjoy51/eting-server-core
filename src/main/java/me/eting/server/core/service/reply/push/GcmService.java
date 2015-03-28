package me.eting.server.core.service.reply.push;

import me.eting.common.domain.reply.Reply;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by lifenjoy51 on 2015-02-28.
 */
@Component
public class GcmService extends PushService{

    /**
     * 전송! 비동기이므로 시간이 오래걸림.
     * @param paramList
     * @return
     */
    private String doGcm(List<NameValuePair> paramList) {
        HttpClient client = new DefaultHttpClient();

        try {
            HttpPost post = new HttpPost("https://android.googleapis.com/gcm/send");
            //log.debug("POST : " + post.getURI());

            /**
             * 헤더설정
             */
            post.setHeader("Authorization",
                    "key=AIzaSyCU5QvXL3GO-0fVA_Obtma3c1vJBgbDOuE");
            post.setHeader("Content-Type",
                    "application/x-www-form-urlencoded;charset=UTF-8");

            post.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));

            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            //log.debug(EntityUtils.toString(entity));

            //return response.toString();
            //TODO response를 처리하는 로직도 만들어야.
            return EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.getConnectionManager().shutdown();
        }

        //TODO response를 처리하는 로직도 만들어야.
        return "error";
    }
    
    @Override
    public void push(Reply reply) {
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        paramList.add(new BasicNameValuePair("registration_id", reply.getIncognito().getDevice().getPushKey()));
        paramList.add(new BasicNameValuePair("data.content_type", "Reply"));
        paramList.add(new BasicNameValuePair("data.story_id", String.valueOf(reply.getExchangedStory().getStory().getId())));
        paramList.add(new BasicNameValuePair("data.emoticon_list", reply.getEmoticon().toString()));
        paramList.add(new BasicNameValuePair("data.reply_content", reply.getContent()));
        paramList.add(new BasicNameValuePair("data.reply_id", String.valueOf(reply.getId())));
        doGcm(paramList);
    }
}
