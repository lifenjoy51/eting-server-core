package me.eting.server.core.service.content;

import me.eting.common.domain.reply.Reply;
import me.eting.common.domain.story.Story;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 내용 검사를 위한 클래스인데. 이건 다국어벼전으로 지원해야한다. 인터페이스를 만들고 구현체를 언어마다 만들자. 디폴트도 필요함!
 *
 * @author lifenjoy51
 */
@Component
public class KoreanContentChecker implements ContentChecker {

    // 자음몇개 제외 ㅇ,ㅎ,
    // 특수문자와 알파벳 포함
    private final static char[] wordP90 = {'이', '고', '는', '다', '하', '어', '지',
            '아', '요', '가', '나', '도', '해', '그', '게', '사', '내', '에', '을', '은',
            '너', '서', '자', '기', '니', '거', '한', '있', '만', '면', '데', '보', '좋',
            '야', '라', '마', '안', '리', '까', '들', '말', '로', '무', '싶', '랑', '생',
            '으', '일', 'ㅠ', '시', 'ㅋ', '람', '구', '수', '오', '할', '힘', '잘', '주',
            '래', '인', '없', '정', '신', '를', '같', '각', '친', '제', '여', '더', '대',
            '겠', '진', '네', '상', '금', '되', '의', '난', '세', '당', '때', '것', '남',
            '음', '미', '부', '려', '우', '모', '워', '연', '했', '러', '많', '저', '못',
            '않', '날', '었', '심', '행', '간', '히', '건', '봐', '전', '걸', '왜', '바',
            '복', '알', '런', '테', '살', '와', '스', '짜', '애', '줄', '렇', '줘', '든',
            '분', '늘', '예', '뭐', '중', '치', '르', 'ㅜ', '장', '두', '길', '화', '소',
            '먹', '던', '운', '빠', '학', '울', '감', '직', '조', '번', '받', '루', '죠',
            '근', '원', '누', '꺼', '실', '될', '넌', '처', '속', '답', '싫', '락', '았',
            '노', '좀', '동', '른', '언', '공', '적', '질', '년', '냥', '과', '눈', '응',
            '버', '팅', '후', '비', '문', '꿈', '디', '잖', '위', '널', '갈', '성', '럼',
            '드', '회', '물', '계', '져', '잊', '달', '느', '얼', '냐', '용', '단', '차',
            '엄', '잇', '께', '혼', '돌', '올', '선', '항', '레', '터', '설', '추', '꼭',
            '새', '겨', '괜', '귀', '방', '떻', '따', '헤', '잠', '프', '습', '찮', '참',
            '재', '력', '유', '매', '불', '또', '작', '반', '교', '배', '명', '민', '집',
            '피', '억', '발', '웃', '입', '꾸', '맞', '트', '쁜', '외', '열', '잡', '먼',
            '님', '개', '결', '앞', '걱', '타', '얘', '편', '관', '엔', '죽', '린', '된',
            '현', '톡', '준', '백', '머', '파', '천', '경', '막', '카', '별', '닌', '절',
            '긴', '볼', '였', '돼', '름', '쁘', '함', '쓰', '믿', '군', '찾', '끝', '놓',
            '존', '겟', '몰', '큼', '순', '식', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', '\\', '!', '@', '#', '^', '*', '_',
            '-', '=', '.', ',', '?', '>', '<', ')', '(', '~', '♥', '↑', '0',
            '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private final static int minCharCnt = 10;
    private final static int cutLine = 18;

    //140509
    //답글내용 검사용 단어로 변경.
    private final static String[] trashWords = {"중2병", "못읽", "어쩌라", "뭐래니", "어쩌라고", "알빠", "ㅇㅉ", "초딩", "사망해라", "노잼", "지랄", "개년", "씨발", "니애미", "꺼저", "꺼져", "ㄲㅈ", "병신", "싫어요", "ㅇㅇ", "ㅡㅡ", "ㅗ", "닥쳐", "빨아", "어쩌라고"};
    private final static String[] trashWordsForShort = {"길어서", "그러게요", "ㅅㄱ", "머래", "ㅇㅋ", "소설", "오크", "ㅉㅉ", "ㅈㅈ", "씹돼지", "니미"};

    public KoreanContentChecker() {

    }

    /**
     * 정상적인 글인지 확인한다.
     *
     * @param content
     * @return
     */
    public boolean isNormalContent(String content) {
        // long st = System.currentTimeMillis();
        int cnt = 0;
        int contentLength = content.length();

        for (char charAtString : content.toCharArray()) {
            if (cnt > minCharCnt) {
                break;
            }
            for (char checkChar : wordP90) {
                // System.out.format("%c %c \n", charAtString, checkChar);
                if (charAtString == checkChar) {
                    cnt++;
                    break;
                }
            }
        }

        if (contentLength == 0)
            contentLength = 1;
        int p = cnt * 100 / contentLength;
        // long en = System.currentTimeMillis();

        // 특정개수 이상 정상글자가 있으면 정상으로 판단.
        if (cnt > minCharCnt) {
            // System.out.println(true + "\t" + (int) p + "\t" + (en - st) +
            // "\t"+ content);
            return true;
        }

        // 글자 길이에 따라서 퍼센트를 다르게 적용.
        if (contentLength > (minCharCnt * cutLine) / 100) {
            if (p > cutLine * 2) {
                // System.out.println(true + "\t" + (int) p + "\t" + (en - st)+
                // "\t" + content);
                return true;

            }
        } else {
            if (p > cutLine) {
                // System.out.println(true + "\t" + (int) p + "\t" + (en - st) +
                // "\t"+ content);
                return true;
            }
        }

        // System.out.println(true+"\t"+(int)p+"\t"+(en-st)+"\t"+content);
        // System.out.println(false + "\t" + (int) p + "\t" + (en - st) + "\t"+
        // content);
        return false;
    }

    /**
     * 답글검사.*
     *
     * @param replyContent
     * @param storyLength
     * @return
     */
    public boolean replyHasTrashWord(String replyContent, int storyLength) {

        if (replyContent == null) return false;

        //무조건 검사.
        for (String w : trashWords) {
            if (replyContent.contains(w)) {
                return true;
            }
        }


        //짧은글만 검사.
        if (storyLength < 10) return false;
        for (String w : trashWordsForShort) {
            if (replyContent.length() < 10) {
                if (replyContent.contains(w)) {
                    return true;
                }
            }
        }
        // '톡'이 들어가고 or '연락'
        if (replyContent.contains("톡") || replyContent.contains("연락")) {
            // 진짜 짧으면
            if (replyContent.length() <= 10) {
                return true;
                //적당히 짧고
            } else {
                // 아이디가 들어가있으면!
                // 이게 정말 아이디일까
                Pattern p = Pattern.compile("[a-zA-Z][a-zA-Z]+[0-9]*");
                Matcher m = p.matcher(replyContent);
                if (m.find()) {
                    return true;
                }
            }
        }

        //140510
        if (replyContent.contains("엑소")) {
            return true;
        }

        //150128
        if (replyContent.contains("aro7")) {
            return true;
        }

        //150304
        if (replyContent.contains("luck3")) {
            return true;
        }

        //140510
        if (replyContent.contains("패스") || replyContent.contains("패쓰") || replyContent.contains("pass")) {
            Pattern p = Pattern.compile("남자|여자");
            Matcher m = p.matcher(replyContent);
            if (m.find()) {
                return true;
            }
        }

        return false;
    }


    /**
     * 길이로 차단여부 판별.
     * @param savedReply
     * @return
     */
    public boolean isEnoughReplyLength(Reply savedReply) {
        Story savedStory = savedReply.getExchangedStory().getStory();
        int rm = 0;
        int sl = savedStory.getContent().length();
        int rl = savedReply.getContent().length();

        //중앙값을 구한다.
        if (sl < 15) {
            rm = (int) (24.0 / 30.0 * sl);
        } else if (sl >= 15 && sl < 25) {
            rm = (int) (12.0 / 30.0 * sl + 6);
        } else if (sl >= 25 && sl < 55) {
            rm = (int) (6.0 / 30.0 * sl + 11);
        } else if (sl >= 55 && sl < 250) {
            rm = (int) (3.0 / 30.0 * sl + 16);
        } else if (sl >= 250) {
            //250보다 크면 최소 40자 이상만 작성하도록한다.
            rm = 40;
        }

        //글쓴이의 grp_q에 따라서 rm을 조정한다..
        //TODO EtingModelValue emv = savedReply.getIncognito().getEtingModelValue();
        //int groupValue = emv.getValues().get(0);
        int groupValue = 0;
        if (groupValue == -1) {
            //짧은사람들이니까 기준도 짧게.
            rm = rm / 3;
        } else if (groupValue == 0) {
            //평범한 사람들 어떻게하지? 
            rm = rm * 2 / 3;
        } else if (groupValue == 1) {

        }

        if (sl < 6) {
            //10%
            //너무 짧은 글이다. 그냥 대충 넘어가자.	
            rm = 0;
        } else if (sl < 9) {
            //20%
            //이것도 역시 짧다...	
            rm = 0;

        } else if (sl < 12) {
            //30%
            //오십보 백보	
            rm = 0;

        } else if (sl < 15) {
            //40%
            //그냥 일상적인 말
            //여기까진 아무 답글이나 달려도 상관없다. 욕만 빼고?!
            //짧은글에 어느정도까지 답글을 허용할건지 결정해야한다.	
            rm /= 5;

        } else if (sl < 20) {
            //50%
            //슬슬 이야기가 만들어진다.
            rm /= 4;

        } else if (sl < 26) {
            //60%
            //질문글이 많아진다.
            //여기까지 중앙값의 1/3을 체크한다.
            rm /= 4;

        } else if (sl < 37) {
            //70%
            //조금씩 길어짐.
            rm /= 3;

        } else if (sl < 55) {
            //80%
            //여전히 쓰잘때기 없는 글도 많은뎅...
            //여기까진 중앙값의 1/2
            rm /= 3;

        } else if (sl < 92) {
            //90%
            //여기서부턴 집중관리대상.
            //지금부턴 2/3이상만 허용한다.
            rm = rm / 2;

        } else if (sl < 250) {
            //99%
            //가장 긴 글들.. 특별 관리대상.
            rm = rm * 2 / 3;
        } else {
            //rm = 40;
            rm = rm * 3 / 4;

        }

        //길이검사.
        if (rl < rm) {
            //조건보다 답글 길이가 짧으면.
            return false;
        } else {
            return true;
        }
    }

}