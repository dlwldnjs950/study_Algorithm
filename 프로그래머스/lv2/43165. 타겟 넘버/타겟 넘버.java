class Solution {
    int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
		dfs(0, 0, numbers, target);
		return answer;
    }
    public void dfs(int n, int sum, int[] numbers, int target) {
						// n은 numbers 배열의 index값
		if (n == numbers.length) { // numbers의 마지막 요소까지 확인했으면
			if (sum == target) // 타켓값일 때
				answer++;
			return;
		}

		dfs(n + 1, sum + numbers[n], numbers, target);	//다음 요소에 대해서 확인 : n+1, 더하는 경우
		dfs(n + 1, sum - numbers[n], numbers, target);	//빼는 경우
	}
}