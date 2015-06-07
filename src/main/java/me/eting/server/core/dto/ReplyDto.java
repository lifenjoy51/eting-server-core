package me.eting.server.core.dto;

import lombok.Data;
import me.eting.common.domain.reply.Reply;

/**
 * Created by lifenjoy51 on 2015-06-02.
 */
@Data
public class ReplyDto {

    private String exchangeId;
    private String storyId;
    private String content;
    private String emoticonList;
    
    public ReplyDto(){}

    public ReplyDto(Reply reply) {
        this.setExchangeId(String.valueOf(reply.getId()));
        this.setStoryId(String.valueOf(reply.getExchangedStory().getStory().getId()));
        this.setContent(reply.getContent());
        this.setEmoticonList(reply.getEmoticon().toString());
    }

    public int getIncognitoId() throws NumberFormatException{
        //story id에서 incognito id를 발라낸다.
        String storyId = String.valueOf(exchangeId);
        String incognitoId = storyId.substring(8, 17);
        return Integer.parseInt(incognitoId);
    }

    @Override
    public String toString() {
        return "ReplyDto{" +
                "exchangeId=" + exchangeId +
                ", storyId=" + storyId +
                ", content='" + content + '\'' +
                ", emoticonList='" + emoticonList + '\'' +
                '}';
    }
}
