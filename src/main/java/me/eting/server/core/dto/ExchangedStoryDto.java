package me.eting.server.core.dto;

import lombok.Data;
import me.eting.common.domain.story.ExchangedStory;

/**
 * Created by lifenjoy51 on 2015-06-02.
 */
@Data
public class ExchangedStoryDto {
    private String id;

    private String storyId;
    private String storyTs;
    private String content;

    public ExchangedStoryDto(ExchangedStory exchangedStory) {
        this.setId(String.valueOf(exchangedStory.getId()));
        this.setStoryId(String.valueOf(exchangedStory.getStory().getId()));
        this.setStoryTs(String.valueOf(exchangedStory.getStory().getTs().getTime()));
        this.setContent(exchangedStory.getStory().getContent());
    }

    public int getIncognitoId() throws NumberFormatException{
        //story id에서 incognito id를 발라낸다.
        String storyId = String.valueOf(id);
        String incognitoId = storyId.substring(8, 17);
        return Integer.parseInt(incognitoId);
    }

    @Override
    public String toString() {
        return "ExchangedStoryDto{" +
                "id=" + id +
                ", storyId='" + storyId + '\'' +
                ", storyTs='" + storyTs + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
