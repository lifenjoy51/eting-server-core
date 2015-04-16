package me.eting.server.model.service;

import me.eting.server.model.domain.EtingModelSource;
import me.eting.server.model.domain.ModelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Created by lifenjoy51 on 2015-04-17.
 */
public class ModelConsumer {

    @Autowired
    ModelQueue modelQueue;

    @Autowired
    EtingModelRegistry etingModelRegistry;

    @Scheduled(fixedDelay = 1000) //queue가 비어있다면 잠시 쉰다.
    public void handle() {

        EtingModelSource source = null;
        while ((source = modelQueue.poll()) != null) {
            ModelType modelType = source.getModelType();
            List<EtingModelService> models = etingModelRegistry.getModels(modelType);
            for (EtingModelService model : models) {
                model.process(source);
            }
        }
    }

}
