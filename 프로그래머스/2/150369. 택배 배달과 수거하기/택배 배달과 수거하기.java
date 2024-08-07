class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
       long answer = 0;

        int deliverRemain = 0;
        int pickupRemain = 0;

        // 먼것부터 처리
        for (int i = n - 1; i >= 0; i--) {
            // 배달/수거 처리할 양
            // 이전 집을 수거할 때 -값이 되었다 = 같이 처리할 수 있었따
            deliverRemain += deliveries[i];
            pickupRemain += pickups[i];
            
            // 처리할 것이 있는 경우에만 이동
            while (deliverRemain > 0 || pickupRemain > 0) {
                // 트럭 용량만큼 처리
                // - 값이 나오기도 함
                deliverRemain -= cap;
                pickupRemain -= cap;
                answer += (i + 1) * 2;
            }
            
            // System.out.println(answer-tmp);
        }

        return answer;
    }
}