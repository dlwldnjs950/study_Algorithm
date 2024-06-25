import java.util.*;
/*
바다(X)와 무인도(숫자)
상하좌우로 연결되면 무인도
숫자 = 식량
각 섬에서 최대 며칠씩 머무를 수 있는지
배열에 오름차순으로 담기
지낼 수 있는 무인도가 없다면 -1 담은 배열
*/
class Solution {
    public int[] solution(String[] maps) {
        
        int height = maps.length;
        int width = maps[0].length();
        
        int[][] map = new int[height][width];
        for(int rIdx=0; rIdx< height; rIdx++){
            for(int cIdx=0; cIdx< width; cIdx++){
                char target = maps[rIdx].charAt(cIdx);
                map[rIdx][cIdx] = target == 'X' ? (-1) : target - '0';
            }
           // System.out.println(Arrays.toString(map[rIdx]));
        }
        
        int[] dr = {0,1,0,-1}, dc = {1,0,-1,0};
        Queue<int[]> queue = new ArrayDeque<>();
        List<Integer> foods = new ArrayList<>();
        for(int rIdx=0; rIdx< height; rIdx++){
            for(int cIdx=0; cIdx< width; cIdx++){
                if(map[rIdx][cIdx] == -1)
                    continue;
                queue.add(new int[]{rIdx, cIdx});
                //System.out.println(rIdx+" "+ cIdx);
                int sum = map[rIdx][cIdx];
                map[rIdx][cIdx] = -1;
                while(!queue.isEmpty()){
                    int[] current = queue.poll();
                    //System.out.println(Arrays.toString(current)+" : "+map[current[0]][current[1]]);
                    
                    for(int direction = 0; direction < 4; direction++){
                        int nr = current[0] + dr[direction];
                        int nc = current[1] + dc[direction];
                        if(nr < 0 || nc < 0 || nr >= height || nc >= width)
                            continue;
                        if(map[nr][nc] == -1)
                            continue;
                        queue.add(new int[]{nr, nc});
                        sum += map[nr][nc];
                        map[nr][nc] = -1;
                    }
                }
                //System.out.println("합계: "+sum);
                foods.add(sum);
            }
        }
        
        if(foods.isEmpty())
            return new int[]{-1};
        
        Collections.sort(foods);
        
        return foods.stream().mapToInt(i->i).toArray();
    }
}