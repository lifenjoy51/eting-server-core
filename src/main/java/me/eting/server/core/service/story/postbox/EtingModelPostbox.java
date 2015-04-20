package me.eting.server.core.service.story.postbox;


import me.eting.common.domain.story.Story;
import me.eting.common.domain.user.EtingModelValue;
import me.eting.common.domain.user.Incognito;
import me.eting.server.model.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Vector;

/**
 * 이팅모델을 적용한 우체통이다.
 * <div>연령별, 분위기별 그룹이 적용된다.</div>
 */
public class EtingModelPostbox extends Postbox {


    ModelService modelService;

    /**
     */
    public EtingModelPostbox(PostboxRegistry postboxRegistry, ModelService modelService) {
        super(postboxRegistry);
        queue = new LinkedList<Story>();
        this.modelService = modelService;
    }

    @Override
    public void put(Story story) {
        queue.add(story);
        postboxRegistry.registStory(story, this);
    }

    @Override
    public Story pick(Incognito incognito) {
        EtingModelValue emvs = modelService.getValue(incognito);
        for(Story s : queue){
            //TODO 캐쉬작업이 필요할것 같은데 우선은 작업한다.
            EtingModelValue emvt = modelService.getValue(s.getIncognito());
            //비교를 해서 최대 거리보다 작은 만!
            boolean isValidDistance = calculateDistance(emvs, emvt);
            if(!s.getIncognito().equals(incognito) && isValidDistance) return s;
        }
        return null;
    }

    /**
     * 두 모델의 거리를 계산한다.
     * @param emvs
     * @param emvt
     * @return
     */
    private boolean calculateDistance(EtingModelValue emvs, EtingModelValue emvt) {
        Vector<Integer> vs = emvs.getValues();
        Vector<Integer> vt = emvt.getValues();
        if(vs.size() == vt.size()){
            int distance = 0;
            for(int i=0; i< vs.size(); i++){
                distance += Math.pow(vs.get(i)-vt.get(i), 2);
            }
            if(distance < modelService.getMaximumDistance())
                return true;
        }

        return false;

    }

    @Override
    public void remove(Story story) {
        queue.remove(story);
    }

}

