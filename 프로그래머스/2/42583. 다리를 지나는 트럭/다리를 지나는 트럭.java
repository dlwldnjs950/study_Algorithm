import java.util.*;
/*
모든 트럭이 다리를 건너려면 최소 몇초?
다리에 최대 bridge_length대의 트럭
다리는 weight 이하까지 버팀
다리에 완전히 오르지 않은 트럭의 무게 무시
트럭 길이는 다 1이구나
*/

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new ArrayDeque<>();
        
        int time = 0;
        int onWeight = 0;
        for(int idx = 0; idx<truck_weights.length; idx++){
            int nowTruck = truck_weights[idx];
            // 시간이 지나는 걸 재야함
            while(true){
                // 다리가 꽉차있으면 한칸 빼기
                if(bridge.size()==bridge_length){
                    onWeight -= bridge.poll();
                }else{
                    time++;
                    // 무게로 비교
                    // 해당 트럭이 올라갈 수 있는지 봐야함
                    if(onWeight + nowTruck > weight){
                        bridge.add(0);
                    }else{
                        bridge.add(nowTruck);
                        onWeight+=nowTruck;
                        break;
                    }
                }
            }
        }
        // 마지막 트럭까지 빠져나와야함
        time+=bridge_length;
        
        return time;
    }
}