import java.util.*;
/*
progresses : 해당 기능의 진도
speeds : 작업 속도
배포되어야 하는 순서대로 작업의 진도가 주어진다
뒤에있는 기능이 앞에있는 기능보다 먼저 개발될 수 있지만
앞에 기능이 배포될 때 함께 배포
*/
class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        
        Queue<Integer> progressesQ = new ArrayDeque<>();
        Queue<Integer> speedsQ = new ArrayDeque<>();
        
        for(int idx=0; idx<speeds.length; idx++){
            progressesQ.add(progresses[idx]);
            speedsQ.add(speeds[idx]);
        }
        
        List<Integer> answer = new ArrayList<>();
        
        while(!progressesQ.isEmpty()){
            int cnt = 0;
            int size = progressesQ.size();
            
            // 작업하기
            for(int loop=0; loop<size; loop++){
                int progress = progressesQ.poll();
                int speed = speedsQ.poll();
                
                progressesQ.add(progress + speed);
                speedsQ.add(speed);
            }
            
            // 하루의 끝에 배포가능한 지 확인
            boolean isPublished = false;
            while(progressesQ.peek() >= 100){
                isPublished = true;
                cnt++;
                progressesQ.poll();
                speedsQ.poll();
                
                // 모두 배포되었다면 그만 확인
                if(progressesQ.isEmpty())
                    break;
            }
            if(isPublished){
                answer.add(cnt);
            }
        }
        
        return answer;
    }
}