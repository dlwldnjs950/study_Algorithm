import java.util.*;

class Solution {
    static class Point{
        int x;
        int y;
        int direction;
        
        Point(int x, int y){
            this.x = x;
            this.y = y;
            this.direction = -1;
        }
        
        Point(int x, int y, int direction){
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
        
        public String toString(){
            return "x : " + this.x + ", y : "+this.y;
        }
    }
    public int solution(String[] board) {
        int height = board.length;
        int width = board[0].length();
        
        // 시작점 찾기
        char[][] maps = new char[height][width];
        Point start = new Point(-1,-1);
        for(int rIdx=0; rIdx<height; rIdx++){
            for(int cIdx=0; cIdx<width; cIdx++){
                maps[rIdx][cIdx] = board[rIdx].charAt(cIdx);
                if(maps[rIdx][cIdx] == 'R')
                    start = new Point(rIdx, cIdx);
            }
        }
        
        // 우0 하1 좌2 상3
        int[] dx = {0,-1,0,1}, dy = {1,0,-1,0};
        int answer = 0;
        // 방향에 대한 방문 표시
        boolean[][][] visited = new boolean[height][width][4];
        Queue<Point> queue = new ArrayDeque<>();
        // 시작 지점에서 어느 방향으로 갈 수 있는지 다 표시
        for(int direction = 0; direction<4; direction++){
            int nx = start.x + dx[direction];
            int ny = start.y + dy[direction];
            if(nx<0 || ny<0 || nx>=height || ny>=width)
                continue;
            // 장애물이면 못지나감
            if(maps[nx][ny] == 'D')
                continue;
            // 해당지점 해당 방향 이동
            queue.add(new Point(start.x, start.y, direction));
            visited[start.x][start.y][direction] = true;
        }
        
        while(!queue.isEmpty()){
            // 각 단계별로 이동 횟수 확인이기 때문에
            int size = queue.size();
            
            for(int loop = 0; loop<size; loop++){
                Point current = queue.poll();
                
                // 해당 지점에서 해당 방향으로 미끄러진 뒤
                int nx = current.x;
                int ny = current.y;
                while(true){
                    int nnx = nx + dx[current.direction];
                    int nny = ny + dy[current.direction];

                    if(nnx<0 || nny<0 || nnx>=height || nny>=width)
                        break;
                    if(maps[nnx][nny] == 'D')
                        break;
                    
                    // 이동 가능할 때만 표시
                    nx = nnx;
                    ny = nny;
                }
                // 미끄러져 멈춘 지점이 출구라면 이동횟수 리턴하며 종료
                if(maps[nx][ny] == 'G')
                    return answer +1;
                
                // 다음 방향 찾기
                for(int direction = 0; direction<4; direction++){
                    int ndx = nx + dx[direction];
                    int ndy = ny + dy[direction];
                    if(ndx<0 || ndy<0 || ndx>=height || ndy>=width)
                        continue;
                    if(maps[ndx][ndy] == 'D')
                        continue;
                    if(visited[ndx][ndy][direction])
                        continue;
                    queue.add(new Point(ndx, ndy, direction));
                    visited[ndx][ndy][direction] = true;
                }
            }
            answer++;
        }
        
        // 중간에 리턴하지 못하고 밖으로 나왔다면 -1 리턴
        return -1;
    }
}