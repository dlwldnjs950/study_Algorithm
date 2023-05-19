class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {0,0};
		int x=0; int y=0;
		for(y = 1; y<(brown-4)/2;y++) {
			x=(brown-4)/2-y;
			if(x*y==yellow) {
				answer[0]=x+2;
				answer[1]=y+2;
				break;
			}
		}
        return answer;
    }
}