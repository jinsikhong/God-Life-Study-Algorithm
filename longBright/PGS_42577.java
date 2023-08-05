package week1;

import java.util.*;

/**
 * 30분
 */
public class PGS_42577_전화번호목록 {

    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i+1].startsWith(phone_book[i])) {
                return false;
            }
        }
        return true;
    }
}
