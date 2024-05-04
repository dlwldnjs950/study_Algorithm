import java.util.*;
/*
지워지는 블럭 덩어리 찾기
지우고 블럭 내리기
*/
class Solution {
    // m : height, n : width
    public int solution(int m, int n, String[] board) {
        // 각 반복에서 블럭이 지워진 적 있는지 확인
        // 각 블럭 확인 다 했는데 지워진 적 없으면 반복 멈춤
        char[][] boards = new char[m+2][n];
        Arrays.fill(boards[0],' ');
        Arrays.fill(boards[1],' ');
        for(int rIdx = 0; rIdx <m; rIdx++){
            String b = board[rIdx];
            for(int cIdx = 0; cIdx < n ; cIdx++){
                boards[rIdx+2][cIdx] = b.charAt(cIdx);
            }
            //System.out.println(Arrays.toString(boards[rIdx+2]));
        }
        int answer=0;
        boolean isRemoved = false;
        while(true){
            isRemoved=false;
            // 지울 블럭 찾기
            boolean[][] isSelected = new boolean[m+2][n];
            for(int rIdx = m+1; rIdx > 2; rIdx--){
                for(int cIdx = 0; cIdx < n -1 ; cIdx++){
                    char one = boards[rIdx][cIdx];
                    char two = boards[rIdx][cIdx +1];
                    char three = boards[rIdx-1][cIdx];
                    char four = boards[rIdx-1][cIdx +1];
                    if(one==' ' || two==' ' || three==' ' || four==' ')
                        continue;
                    if(one== two && two==three && three==four){
                        isRemoved=true;
                    //System.out.println(rIdx+", "+cIdx+" : "+one+","+two+","+three+","+four);
                        isSelected[rIdx][cIdx] = true;
                        isSelected[rIdx][cIdx +1] = true;
                        isSelected[rIdx-1][cIdx] = true;
                        isSelected[rIdx-1][cIdx +1] = true;
                    }
                }
            }
            
            // 블럭 지우고 
            for(int rIdx = 2; rIdx < m+2; rIdx++){
                for(int cIdx = 0; cIdx < n ; cIdx++){
                    if(boards[rIdx][cIdx]==' ' || !isSelected[rIdx][cIdx])
                        continue;
                    // 위에꺼 다 끌어 내려!
                    int idx=0;
                    while(true){
                        if(boards[rIdx-idx][cIdx] == ' ')
                            break;
                        boards[rIdx-idx][cIdx] = boards[rIdx-idx-1][cIdx]; 
                        idx++;
                    }
                    answer++;
                }
            }
            //System.out.println("#");
            // for(char[] arr:boards){
            //     System.out.println(Arrays.toString(arr));
            // }
            
            if(!isRemoved)
                break;
        }
        return answer;
    }
}