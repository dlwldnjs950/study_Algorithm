import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });

        HashMap<String, String> hm = new HashMap<>();
        for (String str : phone_book) {
            if (hm.get(str) != null) {
                return false;
            }
            for (int i = 1, len = str.length() ; i <= len; i++) {
                hm.put(str.substring(0, i), "");
            }
        };
        return true;
    }
}
//효율성 3,4 실패 코드
/*Arrays.sort(phone_book, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length()-o2.length();
			}
		});
		for (int i = 0; i < phone_book.length && answer; i++) {
			for (int j = i; j < phone_book.length && answer; j++) {
				if (j!=i && phone_book[i].equals(phone_book[j].substring(0, phone_book[i].length()))) {
					answer = false;
				}
			}
		}*/
