import java.util.*;

class Solution {
    public long solution(int r1, int r2) {
        // 직선상 개수
        long answer1 = (long) (r2 - r1 + 1) * 4;
        
        // 사분면상 개수
        long answer2 = 0;
        long y = (long) r2;
        for(long x = 1; x<r2; x++){
            while(x*x + y*y > (long) r2*r2){
                y--;
            }
            answer2 +=y;
        }
        //System.out.println(answer1+"//"+answer2);
        y = (long) r1;
        for(long x = 1; x<r1; x++){
            while(x*x + y*y >= (long) r1*r1){
                y--;
            }
            answer2 -=y;
        }
        answer2 *= 4;
        return answer1 + answer2;
    }
}