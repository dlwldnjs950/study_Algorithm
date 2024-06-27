class Solution {
    public int[][] solution(int[] num_list, int n) {
        int m = num_list.length / n;
		int[][] answer = new int[m][n];
		int i = 0;
		for (int j = 0; j < m; j++) {
			for (int k = 0; k < n; k++) {
				answer[j][k] = num_list[i];
				i++;
			}
		}
        return answer;
    }
}