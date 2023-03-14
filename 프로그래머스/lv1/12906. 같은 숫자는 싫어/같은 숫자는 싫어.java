import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Stack<Integer> st = new Stack<Integer>();
		st.push(arr[0]);
		for (int i : arr) {
			if (st.peek() != i)
				st.push(i);
		}
		int[] answer = new int[st.size()];
		for (int i = st.size() - 1; i >= 0; i--) {
			answer[i] = st.pop();
		}

        return answer;
    }
}