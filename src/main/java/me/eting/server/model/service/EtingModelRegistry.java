package me.eting.server.model.service;

import me.eting.server.model.domain.ModelType;
import me.eting.server.model.models.age.AgeModelService;
import me.eting.server.model.models.mood.MoodModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by lifenjoy51 on 2015-04-17.
 */
@Component
public class EtingModelRegistry {

    private Map<ModelType, List<EtingModelService>> map;

    private Set<EtingModelService> models;
    
    private double maximumDistance;

    @Autowired
    private ApplicationContext context;

    //전체 초기화.
    public EtingModelRegistry() {
        //init map
        map = new HashMap<ModelType, List<EtingModelService>>();
        for (ModelType modelType : ModelType.values()) {
            map.put(modelType, new ArrayList<EtingModelService>());
        }

        //init models
        models = new TreeSet<EtingModelService>();
    }

    /**
     * 모델타입에 해당하는 모든 모델을 등록한다.
     * 이를테면 Story타입에 해당하는 모델이 3개가 있을 수 있다.
     */
    @PostConstruct
    public void init() {
        //add maps. motel-type, model-service.
        map.get(ModelType.Story).add(context.getBean(MoodModelService.class));
        map.get(ModelType.Reply).add(context.getBean(MoodModelService.class));
        map.get(ModelType.AbnormalContentStory).add(context.getBean(MoodModelService.class));
        map.get(ModelType.Incognito).add(context.getBean(AgeModelService.class));

        //add models
        models.add(context.getBean(MoodModelService.class));
        models.add(context.getBean(AgeModelService.class));
        
        //calculate maximum distance
        int totalDistanceSum = 0;
        for(int i=0; i<models.size(); i++){
            totalDistanceSum += Math.pow(2,2);
        }
        
        //최대거리의 1/2.
        maximumDistance = totalDistanceSum / 2;
    }

    /**
     * 모델유형에 해당하는 모든 모델을 반환한다.
     *
     * @param modelType
     * @return
     */
    public List<EtingModelService> getModels(ModelType modelType) {
        return map.get(modelType);
    }

    /**
     * 등록된 모든 모델을 리턴한다.
     *
     * @return
     */
    public Set<EtingModelService> getModels() {
        return models;
    }

    /**
     * 모델에서 가능한 최장거리.
     * @return
     */
    public double getMaximumDistance() {
        return maximumDistance;
    }
}
