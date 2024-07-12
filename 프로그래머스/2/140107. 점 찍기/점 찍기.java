import java.util.*;
/*
k의 배수 위치에 찍는 거구나!
반원...
*/
class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        for(long x = 0; x <= d; x += (long) k){
            long y = (long) Math.sqrt((long)d*d - x*x);
            // System.out.println(x+"//"+y);
            // System.out.println((long) y/k);
            answer +=  y/(long)k +1;    // y 좌표가 0 일때도 포함해야 하니까 +1
        }
        return answer;
    }
}