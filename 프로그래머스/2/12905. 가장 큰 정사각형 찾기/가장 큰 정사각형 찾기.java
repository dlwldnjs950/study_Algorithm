class Solution
{
    public int solution(int [][]board)
    {
        int answer = 0;

        int height = board.length;
        int width = board[0].length;
        int[][] dp = new int[height][width];
        
        for(int row = 0; row < height; row++){            
            for(int col = 0; col < width; col++){
                if(col == 0 || row == 0)
                    dp[row][col] = board[row][col];
                else if(board[row][col] == 1)  
                    dp[row][col] = Math.min(dp[row-1][col], Math.min(dp[row][col-1], dp[row-1][col-1])) +1;
                else dp[row][col] = 0;
                
                answer = Math.max(answer, dp[row][col]);                    
            }      
        }

        return (int) Math.pow(answer,2);
    }
}