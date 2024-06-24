import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });

        HashSet<String> hm = new HashSet<>();
        for (String str : phone_book) {
            if (hm.contains(str)) {
                return false;
            }
            for (int i = 1, len = str.length() ; i <= len; i++) {
                hm.add(str.substring(0, i));
            }
        };
        return true;
    }
}