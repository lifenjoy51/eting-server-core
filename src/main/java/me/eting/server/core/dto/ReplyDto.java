package me.eting.server.core.dto;

import lombok.Data;

/**
 * Created by lifenjoy51 on 2015-06-02.
 */
@Data
public class ReplyDto {

    private long exchangeId;
    private String content;
    private String emoticonList;

    public int getIncognitoId() throws NumberFormatException{
        //story id에서 incognito id를 발라낸다.
        String storyId = String.valueOf(exchangeId);
        String incognitoId = storyId.substring(8, 17);
        return Integer.parseInt(incognitoId);
    }
}
