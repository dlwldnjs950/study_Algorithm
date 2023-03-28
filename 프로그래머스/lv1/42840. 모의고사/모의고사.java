import java.util.ArrayList;
class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        int[] cnt =new int[3];
		int one[] = { 1, 2, 3, 4, 5 };	//5
		int two[] = { 2, 1, 2, 3, 2, 4, 2, 5 };	//8
		int three[] = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };	// 10
		// 배열로 비교? 문자열로 비교? 정답 입력이 배열이니까 배열로 그냥 다 비교
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == one[i % 5])
				cnt[0]++;
			if (answers[i] == two[i % 8])
				cnt[1]++;
			if (answers[i] == three[i % 10])
				cnt[2]++;
		}
		int max=-1;
		for (int i = 0; i < 3; i++) {
			if(max<cnt[i])	max=cnt[i];
		}
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i <3; i++) {
			if(max<=cnt[i])	arr.add(i+1);
		}
		answer=new int[arr.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i]=arr.get(i);
		}
        return answer;
    }
}