import java.util.HashMap;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String,Integer> clothesMap = new HashMap<>();
		for(String[] cloth : clothes) {
			clothesMap.put(cloth[1], clothesMap.getOrDefault(cloth[1],0)+1);		
		}
		for(String key : clothesMap.keySet()) {
			answer*=(clothesMap.get(key)+1);
		}
		answer--;
        return answer;
    }
}