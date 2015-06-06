package me.eting.server.core.dto;

import lombok.Data;

/**
 * Created by lifenjoy51 on 2015-06-02.
 */
@Data
public class DeviceDto {
    private String uuid;
    private String pushKey;
    private String ts;
    private String os;
}
