
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
		Map<String, Integer> people = new HashMap<String, Integer>();
		for (String string : participant) {
			people.put(string, people.getOrDefault(string, 0)+1);
		}
		for (String string : completion) {
			people.put(string, people.get(string)-1);
		}
		
		Iterator<Map.Entry<String, Integer>> iter = people.entrySet().iterator();
		
		while(iter.hasNext()) {
			Map.Entry<String, Integer> entry = iter.next();
			if(entry.getValue()!=0) {
				answer=entry.getKey();
				break;
			}
		}
        return answer;
    }
}