class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] check = new boolean[n];
		for (int i = 0; i < n; i++) {
			// 각 i 지점에 대해 dfs 진행
			if (!check[i]) {
				dfs(computers, i, check);
				answer++;
			}
		}
        return answer;
    }
    
    public static boolean[] dfs(int[][] computers, int v, boolean[] check) {
		check[v] = true;
		for (int i = 0; i < computers.length; i++) {
			if (v != i && computers[v][i] == 1 && check[i] == false) {	// 자기 자신 아니고, 연결되어있고, 방문하지 않았으면
				check = dfs(computers, i, check);
			}
		}
		return check;
	}
}