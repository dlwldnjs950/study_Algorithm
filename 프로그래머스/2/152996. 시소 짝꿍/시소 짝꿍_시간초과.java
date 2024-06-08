import java.util.*;

/*
시소는 중심부터 2,3,4 에 좌석이 있다
탑승한 사람의 무게와 시소축과 좌석간의 거리 곱이 양쪽이 같다면 시소 짝꿍
주어진 몸무게로 조합
    거리 별 짝꿍값 동일하면 시소 짝꿍으로 OK
조합은 2명밖에 안되니까 반복문으로 확인한다. => weight의 길이 때문에 10억번이 넘어서 안됨

다른 풀이!
해당 무게의 상대방이 될 수 있는 무게가 존재하는지 파악하는 코드로도 가능하다!
*/

class Solution {
    
    public long solution(int[] weights) {
        long answer = 0;
        
        // 오름차순으로 정렬하여 오른쪽에 앉은 사람이 무겁도록
        Arrays.sort(weights);
        
        int[][] distances = {{2,2}, {2,3}, {2,4}, {3,4}};
        
        for(int left = 0; left<weights.length; left++){
            for(int right = left+1; right<weights.length; right++){
                if(left == right)
                    continue;
                
                int leftW = weights[left];
                int rightW = weights[right];
                for(int[] distance : distances){
                    int leftD = distance[0];
                    int rightD = distance[1];
                    
                    if(leftW * rightD == rightW * leftD){
                        answer++;
                        break;
                    }
                }
                
            }   
        }
        return answer;
    }
}
