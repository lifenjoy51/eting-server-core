package me.eting.common.domain;


/**
 * 이팅 유형 enum
 */
public enum EtingType
{
    NORMAL  //일반유저
    , BLOCKED   //차단됨. 똥.
    , REPORTED  //신고누적    
    , TRASH //애매한놈 처리.
    
    , CELEBRITY //연애인, 유행하는말 등.
    , SLANGS    //비속어. 욕설.
    , PORNO //포르노틱한. 정도가 심한 언어.
    , ANNOYING  //설레게, 박력, 등 성가시게하는거.
    , ANONYMITY //톡아이디, 전화번호 등등
    
    , MEANINGLESS //의미없는.

}

