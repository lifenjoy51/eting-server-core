package me.eting.common.domain.reply;

/**
 * Created by lifenjoy51 on 2015-03-28.
 */
public enum ReplyStatus {
    // 처리되지 않음. Q
    // 정상 N
    // 비정상 A
    // 삭제 D
    // 신고 R
    // 푸쉬에러 E
    Queued, Normal, Abnormal, Deleted, Reported, Error;
}
