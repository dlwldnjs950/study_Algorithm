import java.util.ArrayList;
import java.util.List;
class Solution {
    public int[] solution(int[] arr) {
        List<Integer> arr2 = new ArrayList<>();
		int min = 0;
		for (int i = 0; i < arr.length; i++) {
			arr2.add(arr[i]);
			if (arr[i] < arr[min])
				min = i;
		}
		arr2.remove(min);
		int[] answer = {};
		if (arr2.isEmpty()) {
			answer = new int[1];
			answer[0] = -1;
		} else {
			answer = new int[arr2.size()];
			for (int i = 0; i < arr2.size(); i++) {
				answer[i] = arr2.get(i);
			}
		}
        return answer;
    }
}