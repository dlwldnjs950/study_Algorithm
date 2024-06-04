import java.util.*;
/*
y에서 x를 만들기...
또는 dp지 뭐...
배열에 해당 숫자를 만들기까지 해온 연산의 개수를 저장
*/
class Solution {
    public int solution(int x, int y, int n) {
        int answer = 0;
        
        int[] dp = new int[y +1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[x] = 0;
        for(int number=x; number <= y; number++){
            // x에서 출발한 가지가 없다는 뜻이므로
            if(dp[number] == Integer.MAX_VALUE)
                continue;
            if(number + n <= y){
                dp[number + n] = Math.min(dp[number + n], dp[number] +1);
            }
            if(number * 2 <= y){
                dp[number * 2] = Math.min(dp[number * 2], dp[number] +1);
            }
            if(number * 3 <= y){
                dp[number * 3] = Math.min(dp[number * 3], dp[number] +1);
            }
        }
        
        return dp[y]==Integer.MAX_VALUE?-1:dp[y];
    }
}