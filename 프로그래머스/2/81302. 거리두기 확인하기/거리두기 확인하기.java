import java.util.*;
class Solution {
    public int[] solution(String[][] places) {
        int[] answer = {0,0,0,0,0};
        
        for(int idx = 0; idx < 5; idx++){
            if(okPlace(places[idx]))
                answer[idx] = 1;
        }
        return answer;
    }
    
    // 맨해튼 거리 함수
    int distance(int x1, int y1, int x2, int y2){
        return Math.abs(x1- x2) + Math.abs(y1 - y2);
    }
    
    // 해당 대기실 준수 여부
    boolean okPlace(String[] place){
        boolean[][] isVisited = new boolean[5][5];
        for(int rIdx=0; rIdx<5; rIdx++){
            for(int cIdx=0; cIdx<5; cIdx++){
                if(isVisited[rIdx][cIdx])
                    continue;
                // 응시자면 규칙 지키고 있는지 확인
                if(place[rIdx].charAt(cIdx) == 'P'){
                    if(!okSeat(rIdx, cIdx, place, isVisited))
                        return false;
                }
            }   
        }
        return true;
    }
    
    // 해당 자리 준수 여부?
    boolean okSeat(int x, int y, String[] place, boolean[][] isVisited){
        int dx[] = { 0, -1, 0, 1 };
        int dy[] = { 1, 0, -1, 0 };
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x,y});
        isVisited[x][y] = true;
        
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            
            for (int direction = 0; direction < 4; direction++) {
                int nr = current[0] + dx[direction];
                int nc = current[1] + dy[direction];
 
                if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5)
                    continue;
                if(isVisited[nr][nc])
                    continue;
 
                int distance = distance(x, y, nr, nc);
 
                if (place[nr].charAt(nc) == 'P' && distance <= 2)
                    return false;
                else if (place[nr].charAt(nc) == 'O' && distance < 2)
                    queue.offer(new int[] { nr, nc });
            }
        }
        return true;
    }
}