import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        
        int size = triangle.length;
        
        int[][] dp = new int[size][size];
        
        dp[size -1] = Arrays.copyOf(triangle[size -1], size);
        for(int row = size - 2; row >=0; row--){
            for(int col = 0; col< triangle[row].length; col++){
                dp[row][col]  = triangle[row][col] + Math.max(dp[row + 1][col], dp[row + 1][col + 1]);
            }
        }
        
        return dp[0][0];
    }
}