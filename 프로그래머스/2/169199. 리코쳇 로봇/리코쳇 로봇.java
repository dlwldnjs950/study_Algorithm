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
        boolean[][][] visited = new boolean[height][width][4];
        Queue<Point> queue = new ArrayDeque<>();
        for(int direction = 0; direction<4; direction++){
            int nx = start.x + dx[direction];
            int ny = start.y + dy[direction];
            if(nx<0 || ny<0 || nx>=height || ny>=width)
                continue;
            if(maps[nx][ny] == 'D')
                continue;
            queue.add(new Point(start.x, start.y, direction));
            visited[start.x][start.y][direction] = true;
        }
        
        while(!queue.isEmpty()){
            //System.out.println(answer+"---------------------");
            int size = queue.size();
            
            for(int loop = 0; loop<size; loop++){
                Point current = queue.poll();
                //System.out.println(current.toString()+" / "+maps[current.x][current.y]);
                
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
                    
                    nx = nnx;
                    ny = nny;
                }
                // 다음 방향 찾기
                if(maps[nx][ny] == 'G')
                    return answer +1;
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
        
        
        return -1;
    }
}