package me.eting.server.core.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by lifenjoy51 on 2015-06-02.
 */
@Data
public class StoryDto {
    private long id;
    private String content;

    public int getIncognitoId() throws NumberFormatException{
        //story id에서 incognito id를 발라낸다.
        String storyId = String.valueOf(id);
        String incognitoId = storyId.substring(8, 17);
        return Integer.parseInt(incognitoId);
    }
}
