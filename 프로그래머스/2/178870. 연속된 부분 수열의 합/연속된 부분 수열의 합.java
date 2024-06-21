import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        
        int[] answer = new int[2];
        
        //int left = 0; int right = 0;
        int midSum = 0;
        int length = sequence.length +1;
        for(int left = 0, right = 0; left < sequence.length; left++){
            for(; right < sequence.length && midSum < k;){
                midSum += sequence[right++];
            }
            
            if(midSum == k){
               // System.out.println((right - left + 1) + " " + length);
                if(right - left < length){
                    //System.out.println("갱신");
                    length = right - left;
                    answer[0] = left;
                    answer[1] = right -1;
                }
            }
            
            midSum -= sequence[left];
        }
        
        
        return answer;
    }
}