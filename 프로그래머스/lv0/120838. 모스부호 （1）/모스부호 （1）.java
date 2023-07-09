import java.util.HashMap;
import java.util.StringTokenizer;
class Solution {
    public String solution(String letter) {
       String morse = "'.-':'a','-...':'b','-.-.':'c','-..':'d','.':'e','..-.':'f',"
				+ "'--.':'g','....':'h','..':'i','.---':'j','-.-':'k','.-..':'l',"
				+ "'--':'m','-.':'n','---':'o','.--.':'p','--.-':'q','.-.':'r',"
				+ "'...':'s','-':'t','..-':'u','...-':'v','.--':'w','-..-':'x',"
				+ "'-.--':'y','--..':'z'";
		morse = morse.replaceAll("'", "");
		String[] morse2 = morse.split(",");
		HashMap<String, String> m = new HashMap<>();
		for (String str : morse2) {
			String[] tmp = str.split(":");
			m.put(tmp[0], tmp[1]);
		}

		String answer = "";
		StringTokenizer st = new StringTokenizer(letter, " ");
		while (st.hasMoreTokens()) {
			answer += m.get(st.nextToken());
		}
        return answer;
    }
}