import java.util.*;

class Solution {
    public int solution(String[] board) {
        int answer = -1;
        
        int oCnt = 0;
        int xCnt = 0;
        for(int rIdx = 0; rIdx<3; rIdx++){
            String rBoard = board[rIdx];
            for(int cIdx = 0; cIdx<3; cIdx++){
                if(rBoard.charAt(cIdx) == 'O')
                    oCnt++;
                if(rBoard.charAt(cIdx) == 'X')
                    xCnt++;
            }
        }
        
        // o와 x의 개수가 같거나 o가 1개 더 많아야 한다
        if(!(oCnt == xCnt || oCnt == xCnt + 1))
            return 0;
        
        boolean oWin = canWin(board, 'O');
        boolean xWin = canWin(board, 'X');
        
        if(oWin && xWin)
            return 0;
        
        if (oWin && oCnt != xCnt + 1) {
            return 0;
        }
        
        if (xWin && oCnt != xCnt) {
            return 0;
        }
        
        return 1;
    }
    
    boolean canWin(String[] board, char player){
        // 가로와 세로 확인
        for (int i = 0; i < 3; i++) {
            if ((board[i].charAt(0) == player && board[i].charAt(1) == player && board[i].charAt(2) == player) ||
                (board[0].charAt(i) == player && board[1].charAt(i) == player && board[2].charAt(i) == player)) {
                return true;
            }
        }
        // 대각선 확인
        if ((board[0].charAt(0) == player && board[1].charAt(1) == player && board[2].charAt(2) == player) ||
            (board[0].charAt(2) == player && board[1].charAt(1) == player && board[2].charAt(0) == player)) {
            return true;
        }
        return false;
    }
}