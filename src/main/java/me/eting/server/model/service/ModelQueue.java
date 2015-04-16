package me.eting.server.model.service;

import me.eting.server.model.domain.EtingModelSource;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by lifenjoy51 on 2015-04-16.
 */
@Component
public class ModelQueue  {
    
    private Queue<EtingModelSource> queue = new LinkedList<EtingModelSource>();
    
    public void offer(EtingModelSource modelSource){
        queue.offer(modelSource);
    }
    
    public EtingModelSource poll(){
        return queue.poll();
    }
    
}
