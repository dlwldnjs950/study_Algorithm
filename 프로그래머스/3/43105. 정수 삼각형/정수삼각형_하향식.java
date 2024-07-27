import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        
        int size = triangle.length;
        
        int[][] memo = new int[size][size];
        for(int idx=0; idx<size; idx++){
            Arrays.fill(memo[idx], -1);
        }
        
        // 하향식 접근 => 재귀!
        
        return makeSum(triangle, memo, 0, 0);
    }
    
    int makeSum(int[][] triangle, int[][] memo, int row, int col){
        
        // 마지막 열
        if(row == triangle.length -1){
            return triangle[row][col];
        }
        
        // 이미 해당 위치 값 계산 했으면
        if(memo[row][col] != -1){
            return memo[row][col];
        }
        
        int leftSum = makeSum(triangle, memo, row + 1, col);
        int rightSum = makeSum(triangle, memo, row + 1, col + 1);
        
        memo[row][col] = triangle[row][col] + Math.max(leftSum, rightSum);
        
        return memo[row][col];
        
    }
}
