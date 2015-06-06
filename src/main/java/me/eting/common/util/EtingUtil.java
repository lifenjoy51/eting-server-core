package me.eting.common.util;

import com.google.common.base.Strings;

import java.util.Date;

/**
 * Created by lifenjoy51 on 2015-02-28.
 */
public class EtingUtil {

    //2013. 9. 1. 오전 12:00:00 GMT+9:00 #1000으로 나눈 값이다.
    private static final long BASE_TIMESTAMP = 1377961200l;

    /**
     * 타임스탬프 + 익명아이디 형식으로 id를 생성한다.
     * 타임스탬프는 2013-09-01을 시점으로한다. 즉 현재 타임스탬프에서 2013-09-01의 타임스탬프를 뺀다.* *
     *
     * @param incognitoId
     * @return
     */
    public static long generatedId(int incognitoId) {
        //변환한 타임스탬프
        String ts = String.valueOf(timestamp() - BASE_TIMESTAMP);
        //incognito id를 9자리 문자열로 바꾼다.
        String id = Strings.padStart(String.valueOf(incognitoId), 9, '0');
        //합친다.
        String generatedId = ts.concat(id);
        //long으로 변환해서 반환.
        return Long.parseLong(generatedId);
    }

    public static long timestamp() {
        return new Date().getTime() / 1000;
    }

    public static Date str2date(String str) throws NumberFormatException {
        long timestamp = Long.parseLong(str);
        return new Date(timestamp);
    }
    
    public static void main(String[] args){
        long sId = EtingUtil.generatedId(1);
        String storyId = String.valueOf(sId);
        System.out.println(sId);
        System.out.println(storyId.substring(0,8));
        System.out.println(storyId.substring(8,17));
        
    }   
    
}
