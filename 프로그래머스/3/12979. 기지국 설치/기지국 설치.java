import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        List<int[]> panels = new ArrayList<>();
        List<int[]> lengths = new ArrayList<>();
        
        // 맨 앞 구간
        int left = 1, right = stations[0] - w -1;
        if(left <= right){
            //panels.add(new int[]{left, right});
            answer += count(left, right, w);
        }
        
        // 중간 구간
        for(int idx = 1; idx< stations.length; idx++){
            left = stations[idx -1] + w + 1;
            right = stations[idx] - w -1;
            
            if(left <= right){
                //panels.add(new int[]{left, right});
                answer += count(left, right, w);
            }
        }
        
        // 맨 뒤 구간
        left = stations[stations.length -1] + w + 1;
        right = n;
        if(left <= right){
            //panels.add(new int[]{left, right});
            answer += count(left, right, w);
        }
        
        // for(int[] p : panels){
        //     //System.out.println(p[0] +" // "+p[1]);
        //     int tmp = p[1] - p[0] + 1;
        //     int div = w * 2 + 1;
        //     //System.out.println(">> " + tmp +" / "+div+" = "+(int)Math.ceil((double)tmp/div));
        //     answer +=(int)Math.ceil((double)tmp/div);
        // }

        return answer;
    }
    
    int count(int left, int right, int w){
        int tmp = right - left + 1;
        int div = w * 2 + 1;
        
         return (int)Math.ceil((double)tmp/div);
    }
}