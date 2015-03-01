package me.eting.server.core.service.story;
import me.eting.common.domain.story.Story;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 사용자가 작성한 이야기를 담고있는 대기열이다.
 * <div>비동기 처리를 위해 존재한다.</div>
 * <div>추후 메세지큐로 대체될 수 있음.</div>
 */
@Component
public class StoryQueue
{
	/**
	 */
	private Queue<Story> stories;
	
	/**
	 */
	public StoryQueue(){
        stories = new LinkedList<Story>();
	}

	/**
     * queue에 있는 이야기를 하나씩 빼온다.
	 */
	public Story poll() {
		return stories.poll();
	}
	
	/**
     * quque에 이야기를 넣는다.
	 */
	public boolean offer(Story story) {
		return stories.add(story);	
	}
	
}

