package me.eting.server.model.models.mood;

import me.eting.common.domain.reply.Reply;
import me.eting.common.domain.story.Story;
import me.eting.common.domain.user.Incognito;
import me.eting.server.core.service.reply.check.ReplyChecker;
import me.eting.server.core.service.reply.check.ReplyCheckerRegistry;
import me.eting.server.model.domain.EtingModelSource;
import me.eting.server.model.domain.ModelType;
import me.eting.server.model.service.EtingModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lifenjoy51 on 2015-04-17.
 */
@Component
public class MoodModelService extends EtingModelService {

    @Autowired
    MoodModelRepository moodModelRepository;

    @Autowired
    private ReplyCheckerRegistry replyCheckerRegistry;
    
    @Override
    public void process(EtingModelSource etingModelSource) {
        // Incognito
        Incognito incognito = etingModelSource.getIncognito();

        //들어오는 소스에 맞게 작업 수행.
        Object obj = etingModelSource.getObj();
        MoodModel model = null;
        if (obj instanceof Story) {
            model = processStory(incognito, (Story) obj);
        } else if (obj instanceof Reply) {
            model = processReply(incognito, (Reply) obj);
        }

        // 각자 보관중인 점수 테이블이 필요함.
        // 점수를 산정한 다음.
        int score = model.getPositive() - model.getNegative();
        if (score < -10) {
            model.setValue(-1);
        } else if (score >= -10 && score < 15) {
            model.setValue(0);
        } else {
            model.setValue(1);
        }

        // 해당 점수에 따라 결과값을 정한다.
        moodModelRepository.save(model);
    }

    private MoodModel processStory(Incognito incognito, Story story) {
        MoodModel model = moodModelRepository.findOne(incognito.getId());
        if (model == null) model = new MoodModel(incognito);

        //점수를 매기자.

        return model;
    }

    /**
     * 저장하는 답글을 검사해서 점수를 매긴다.*
     *
     * @param incognito
     * @param reply
     * @return
     */
    private MoodModel processReply(Incognito incognito, Reply reply) {

        ReplyChecker replyChecker = replyCheckerRegistry.getChecker(incognito.getEtingKey().getEtingLang());

        Story savedStory = reply.getExchangedStory().getStory();

        MoodModel model = moodModelRepository.findOne(incognito.getId());
        if (model == null) model = new MoodModel(incognito);
        int pos = model.getPositive();    //좋은점수.
        int neg = model.getNegative();    //감점!!!
        int value = model.getValue();

        // 내용검사.
        if (!replyChecker.isValid(reply)) {
            neg++;
        }

        //점수매기기
        int sl = savedStory.getContent().length();
        int rl = reply.getContent().length();
        int rm = 0; // 답글길이의 중앙값.

        if (sl < 15) {
            rm = (int) (24.0 / 30.0 * sl);
        } else if (sl >= 15 && sl < 25) {
            rm = (int) (12.0 / 30.0 * sl + 6);
        } else if (sl >= 25 && sl < 55) {
            rm = (int) (6.0 / 30.0 * sl + 11);
        } else if (sl >= 55 && sl < 250) {
            rm = (int) (3.0 / 30.0 * sl + 16);
        } else if (sl >= 250) {
            rm = 40;
        }

        //글쓴이의 grp_q에 따라서 rm을 조정한다..
        if (value < 0) {
            //짧은사람들이니까 기준도 짧게.
            rm = rm / 3;
        } else if (value == 0) {
            //평범한 사람들 어떻게하지? 
            rm = rm * 2 / 3;
        } else if (value > 0) {

        }

        int lowest = rm / 3; // 중앙값 의 1/3
        int low = rm * 2 / 3; // 중앙값 의 2/3
        int high = rm * 2; // 중앙값 *2
        int highest = rm * 3; // 중앙값 *3
        int extra = rm * 4; // 중앙값 *4


        // 이게 기준이다.
        if (rl < lowest)
            neg += 2;
        if (rl < low)
            neg += 1;
        if (rl > high)
            pos += 1;
        if (rl > highest)
            pos += 2;
        if (rl > extra)
            pos += 3;

        if (sl < 15) {
            // 너무 짧으니 적용하지 않는다.
            if (rl > extra)
                pos = 1;
        } else if (sl >= 15 && sl < 25) {
            // 애매한 길이다 심한경우만 적용하자.
            if (rl < lowest)
                neg = 1;
            if (rl > highest)
                pos = 1;
        } else if (sl >= 25 && sl < 55) {
            // 이게 기준.
        } else if (sl >= 55 && sl < 250) {
            // 가중치를 *2
            neg *= 2;
            pos *= 2;
        } else if (sl >= 250) {
            // 가중치 *3
            neg *= 3;
            pos *= 3;
        }

        model.setPositive(pos);
        model.setNegative(neg);

        return model;
    }


    @Override
    public int value(Incognito incognito) {
        MoodModel moodModel = moodModelRepository.findOne(incognito.getId());
        // TODO 분위기 모델 기본값이 없을 때에 어떻게 처리할것인가?
        if(moodModel == null) return 0;
        int value = moodModel.getValue();
        return value;
    }

}
