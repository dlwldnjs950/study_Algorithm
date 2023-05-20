import java.util.ArrayList;
import java.util.List;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {

        boolean[] complete = new boolean[progresses.length];
		List<Integer> cnt = new ArrayList<>();
		for(int i=0; i<progresses.length && !complete[i] ;) {
			for(int j=0; j<progresses.length;j++) {
				progresses[j]+=speeds[j];
				if(progresses[j]>=100) {
					complete[j]=true;
				}
			}
			int count=0;
			if(complete[i]) {
				for(int k=i;k<complete.length && complete[k];k++) {
					count++;
					i++;
				}
				cnt.add(count);
			}
		}
		int[] answer = new int[cnt.size()];
		for(int i=0; i<answer.length;i++) {
			answer[i]=cnt.get(i);
		}
        return answer;
    }
}