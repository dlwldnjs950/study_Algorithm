import java.util.*;
/*

*/
class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        int wLimit = board[0]/2;
        int hLimit = board[1]/2;
        
        int[] answer = new int[2];
        
        for(String key : keyinput){
            int nx = answer[0];
            int ny = answer[1];
            switch(key){
                case "left":
                    nx--;
                    break;
                case "right":
                    nx++;
                    break;
                case "up":
                    ny++;
                    break;
                case "down":
                    ny--;
                    break;
            }
            
            if(Math.abs(nx) > wLimit || Math.abs(ny) > hLimit)
                continue;
            answer[0] = nx;
            answer[1] = ny;
        }
        
        return answer;
    }
}