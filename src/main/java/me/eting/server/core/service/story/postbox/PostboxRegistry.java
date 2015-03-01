package me.eting.server.core.service.story.postbox;
import javafx.geometry.Pos;
import me.eting.common.domain.EtingKey;
import me.eting.common.domain.EtingLang;
import me.eting.common.domain.EtingType;
import me.eting.common.domain.story.Story;
import me.eting.server.core.service.story.classifier.EnglishStoryClassifier;
import me.eting.server.core.service.story.classifier.KoreanStoryClassifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


/**
 * 우체통을 등록하고 관리한다.
 * <div>Map&lt;EtingPostboxKey, Postbox&gt;로 관리한다.</div>
 * <div>여러가지 유형에 따라 알맞는 우체통을 만들어 넣고 관리한다.</div>
 */
@Component
public class PostboxRegistry
{
	/**
     * 우체통 보관함. 
	 */
	private Map<EtingKey, Postbox> postboxMap;

    /**
     * 이야기 보관함. 역인덱스 
     */
    private Map<Long, Postbox> storyIndex;

    @Autowired
    private ApplicationContext context;
	
	/**
	 */
	public PostboxRegistry(){
        postboxMap = new HashMap<EtingKey, Postbox>();
        storyIndex = new HashMap<Long, Postbox>();
	}

    /**
     * 이 부분이 계속 변할것이다. 
     * 모든 EtingLang에 대하여 각 EtingType에 맞는 우체통을 만들어서 등록한다.
     */
    @PostConstruct
    public void init() {

        //bean 등록을 위한 bean factory.
        ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) context).getBeanFactory();
        
        for(EtingLang etingLang : EtingLang.values()){
            for(EtingType etingType : EtingType.values()){
                //우체통을 식별할 key 생성.
                EtingKey etingKey = new EtingKey(etingLang, etingType);
                
                Postbox postbox = null;
                switch (etingType){

                    case NORMAL:
                        postbox = new EtingModelPostbox(this);
                        break;

                    case REPORTED:
                        postbox = new NormalPostbox(this);
                        break;
                    case TRASH:
                        postbox = new NormalPostbox(this);
                        break;
                    case CELEBRITY:
                        postbox = new NormalPostbox(this);
                        break;
                    case SLANGS:
                        postbox = new NormalPostbox(this);
                        break;
                    case ANNOYING:
                        postbox = new NormalPostbox(this);
                        break;
                    case MEANINGLESS:
                        postbox = new NormalPostbox(this);
                        break;
                    
                    case BLOCKED:
                        postbox = new BanedPostbox(this);
                        break;
                    case PORNO:
                        postbox = new BanedPostbox(this);
                        break;
                    case ANONYMITY:
                        postbox = new BanedPostbox(this);
                        break;
                }
                
                //bean 등록.
                String beanName = etingLang.name().concat(etingType.name()).concat("Postbox");
                if(postbox != null)
                    beanFactory.registerSingleton(beanName, postbox);
                
                //map에 등록.
                postboxMap.put(etingKey, (Postbox) context.getBean(beanName));
                
            }
        }
    }

    /**
     * 적절한 우체통을 받아온다.
     * @param etingKey
     * @return
     */
    public Postbox getPostbox(EtingKey etingKey){
        return postboxMap.get(etingKey);        
    }

    /**
     * 나중에 삭제로직을 위해 어떤 이야기가 어느 우체통에 들어갔는지 기록한다. 
     * @param story
     * @param postbox
     */
    public void registStory(Story story, Postbox postbox){
        storyIndex.put(story.getId(), postbox);
    }

    /**
     * 답장이 달렸을 때 해당 이야기를 우체통에서 삭제한다. 
     * @param story
     */
    public void removeStory(Story story){
        Postbox postbox = storyIndex.get(story.getId());
        postbox.remove(story);
    }
    
    

}

