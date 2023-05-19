class Solution {
	static boolean[] visited;
    static int answer = 0;
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
		
		dfs(begin, target, words, 0);
        return answer;
    }

	public static void dfs(String begin, String target, String[] words, int cnt) {
        if (begin.equals(target)) {
            answer = cnt;
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (visited[i]) {
                continue;
            }

            int k = compare(begin,words[i]);

            if (k == 1) {
                visited[i] = true;
                dfs(words[i], target, words, cnt + 1);
                visited[i] = false;
            }
        }
    }
	
	static int compare(String s1, String s2) {
        int n = 0;
        for (int i = 0; i < s1.length(); i++) 
            if(s1.charAt(i) != s2.charAt(i))
                n++;

        return n;
    }
}