import java.util.*;

class Solution {
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        boolean[][] visited = new boolean[m][n];
        // 지나간 영역은 -1로 표시
        for(int rIdx=0; rIdx < m; rIdx++){
            for(int cIdx=0; cIdx < n; cIdx++){
                
                // 이미 확인하지 않은 부분이면 영역 표시
                if(!visited[rIdx][cIdx] && picture[rIdx][cIdx] != 0){
                    
                    numberOfArea++;
                    
                    Queue<int[]> queue = new ArrayDeque<>();
                    int[] dr = {0,1,0,-1}, dc={1,0,-1,0};
                    
                    int tmpSize = 0;
                    visited[rIdx][cIdx] = true;
                    queue.add(new int[]{rIdx, cIdx});
                    while(!queue.isEmpty()){
                        
                        int[] current = queue.poll();
                        tmpSize++;
                        
                        for(int direction=0; direction<4; direction++){
                            int nr = current[0] + dr[direction];
                            int nc = current[1] + dc[direction];
                            
                            // 영역 내
                            if(nr<0 || nc<0 || nr>=m || nc>=n)
                                continue;
                            // 이미 방문했음 넘어가
                            if(visited[nr][nc])
                                 continue;
                            // 다른 숫자면 넘어가
                            if(picture[nr][nc] != picture[current[0]][current[1]])
                                continue;
                            visited[nr][nc] = true;
                            queue.add(new int[]{nr,nc});
                        }
                    }
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, tmpSize);
                }
            }    
        }
                
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}