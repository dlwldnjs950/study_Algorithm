import java.util.*;

/*
시소는 중심부터 2,3,4 에 좌석이 있다
탑승한 사람의 무게와 시소축과 좌석간의 거리 곱이 양쪽이 같다면 시소 짝꿍
주어진 몸무게로 조합
    거리 별 짝꿍값 동일하면 시소 짝꿍으로 OK
조합은 2명밖에 안되니까 반복문으로 확인한다. => weight 길이때매 10억번되서 안된데...
*/

class Solution {
    
    public long solution(int[] weights) {
        long answer = 0;
        
        Map<Integer, Integer> wCnt = new HashMap<>();
        for(int weight : weights){
            wCnt.put(weight, wCnt.getOrDefault(weight,0) +1);
        }
        
        int[][] distances = {{2,3}, {2,4}, {3,4}};
        List<Integer>  weightList = new ArrayList<>();
        for(int weight : wCnt.keySet()){
            if(weightList.isEmpty()){
                weightList.add(weight);
                continue;
            }
            if(!weightList.contains(weight))
                weightList.add(weight);
        }
        
        
        for(int idx1=0; idx1<weightList.size(); idx1++){   
            int weight1 = weightList.get(idx1);
            if(wCnt.get(weight1) > 1){
                        answer+= ((long)(wCnt.get(weight1) -1) * wCnt.get(weight1) / 2);
                        //System.out.println(weight1 +" (" +((wCnt.get(weight1) -1) * wCnt.get(weight1) / 2)+")");
                    }
            for(int idx2=idx1+1; idx2<weightList.size(); idx2++){
                
                int weight2 = weightList.get(idx2);

                int leftW = Math.min(weight1, weight2);
                int rightW = Math.max(weight1, weight2);
                for(int[] distance : distances){
                    int leftD = distance[0];
                    int rightD = distance[1];
                    if(leftW * rightD == rightW * leftD){
                        //System.out.println(leftW +" "+ rightD +" "+ rightW +" "+ leftD +" ("+(wCnt.get(weight1) * wCnt.get(weight2))+")");
                        answer += ((long)wCnt.get(weight1) * wCnt.get(weight2));
                        break;
                    }
                }
            }
        }
        return answer;
    }
}