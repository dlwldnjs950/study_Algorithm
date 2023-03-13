import java.util.Arrays;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer=new int[commands.length];
		
		for (int i = 0; i < commands.length; i++) {
			int[] js = array.clone();
			Arrays.sort(js,commands[i][0]-1,commands[i][1]);
			answer[i]=js[commands[i][0]-1+commands[i][2]-1];			
		}
        return answer;
    }
} 