import java.util.*;
/*
answer은 전체 배열 크기에서 시작
델타배열 만들어서 탐색
확인한 부분은 2로 표시(위험지역)
    지뢰가 없는 지역일 경우에만
마이너스 되는 경우 
    지뢰
    위험지역(단, 2로 표시한 부분이면 넘어감)
*/
class Solution {
    public int solution(int[][] board) {
        int length = board.length;
        int answer = length * length;
        
        int[] dx = {1,1,0,-1,-1,-1,0,1}, dy = {0,1,1,1,0,-1,-1,-1};
        
        for(int rIdx = 0; rIdx < length; rIdx++){
            for(int cIdx = 0; cIdx < length; cIdx++){
                if(board[rIdx][cIdx] == 1){
                    
                    answer--;
                    
                    // 위험지역 탐색
                    for(int direction = 0; direction < dx.length; direction++){
                        int nx = rIdx + dx[direction];
                        int ny = cIdx + dy[direction];
                        
                        if(nx < 0 || ny < 0 || nx >= length || ny >= length)
                            continue;
                        
                        if(board[nx][ny] == 0){
                            board[nx][ny] = 2;
                            answer--;
                        }
                    }
                }
            }
        }
        return answer;
    }
}