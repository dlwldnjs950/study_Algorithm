import java.util.*;
/*
두가지 방법 1. 3차원 배열 2. 중복 제거를 위한 HashSet
*/
class Solution {
    public int solution(String dirs) {
        
        HashSet<String> visited = new HashSet<>();
        
        int currentX=0;
        int currentY=0;
        for(int idx = 0; idx<dirs.length(); idx++){
            int nextX = currentX;
            int nextY = currentY;
            StringBuilder visitStr = new StringBuilder();
            
            char order = dirs.charAt(idx);
            
            if('U' == order){
                nextY++;
                visitStr.append(currentX).append(currentY).append(nextX).append(nextY);
            }else if('D' == order){
                nextY--;
                visitStr.append(nextX).append(nextY).append(currentX).append(currentY);
            }else if('R' == order){
                nextX++;
                visitStr.append(currentX).append(currentY).append(nextX).append(nextY);
            }else if('L' == order){
                nextX--;
                visitStr.append(nextX).append(nextY).append(currentX).append(currentY);
            }
            
            if (nextX < -5 || nextY < -5 || nextX > 5 || nextY > 5) {
                continue;
            }
            visited.add(visitStr.toString());
 
            currentX = nextX;
            currentY = nextY;
        }
        
        int answer = visited.size();
        return answer;
    }
}