import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] sAnswers = new String[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			sAnswers[i] = String.valueOf(numbers[i]);
		}

		Arrays.sort(sAnswers, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				
				return (o2 + o1).compareTo(o1 + o2);
			}
		});
        
		if (sAnswers[0].equals("0")) return "0";
        
		for (int i = 0; i < sAnswers.length; i++) {
			answer+=sAnswers[i];
		}
        return answer;
    }
}