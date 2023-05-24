import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> onBridge = new LinkedList<>();
		int sum = 0; // 다리위 무게

		for (int i = 0; i < truck_weights.length; i++) {
			int truck = truck_weights[i];
			while (true) {
				if (onBridge.isEmpty()) {
					onBridge.add(truck);
					sum += truck;
					answer++;
					break;
				} else if (onBridge.size() == bridge_length) {
					sum -= onBridge.poll();
				} else {
					if (sum + truck <= weight) {
						onBridge.add(truck);
						sum += truck;
						answer++;
						break;
					}else {
						onBridge.add(0);
						answer++;
					}
				}
			}
		}
		answer+=bridge_length;
        return answer;
    }
}