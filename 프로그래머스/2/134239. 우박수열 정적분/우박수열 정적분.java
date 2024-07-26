import java.util.*;
/*
1. 규칙에 따라 점을 구한다
2. 정적분 결과는 곧 넓이이므로
    넓이의 누적합을 저장한다.
*/
class Solution {
    public double[] solution(int k, int[][] ranges) {
        
        // 그래프의 y값 목록 (x 값 == 인덱스 값)
        List<Integer> y = new ArrayList<>();
        while(k > 1){
            y.add(k);
            if(k%2 == 0){
                k /=2;
            }else{
                k *= 3;
                k += 1;
            }
        }
        y.add(k);
        
        // 범위 n
        int xRange = y.size();
        
        double[] areas = new double[xRange];
        for(int x = 1; x < xRange; x++){
            int leftY = y.get(x);
            int rightY = y.get(x-1);
            areas[x] = areas[x-1] + calculateArea(Math.max(leftY,rightY), Math.min(leftY,rightY));
        }
        
        double[] answer = new double[ranges.length];
        
        for(int idx=0; idx<ranges.length; idx++){
            int left = ranges[idx][0];
            int right = ranges[idx][1];
            
            right = xRange + right;
            
            // 주어진 구간의 시작점이 끝점보다 크면 -1
            if(left >= right){
                answer[idx] = -1;
                continue;
            }
            
            answer[idx] = areas[right -1] - areas[left];
        }
        
        return answer;
    }
    
    // 해당 구간의 넓이
    double calculateArea(int big, int small){        
        return (double) big - (double)(big - small) / 2;
    }
}