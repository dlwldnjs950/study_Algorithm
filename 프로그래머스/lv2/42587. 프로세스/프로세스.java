import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
		Queue<Integer> originalQ = new LinkedList<>();

		for (int i : priorities) {
			q.offer(i);
		}

		for (int i = 0; i < priorities.length; i++) {
			originalQ.offer(i);
		}

		int result = 1;
		while (!originalQ.isEmpty()) {
			int originIdx = originalQ.poll();
			int originValue = priorities[originIdx];
			int maxValue = q.peek();
			if (originValue < maxValue) {
				originalQ.offer(originIdx);
			} else if (originValue == maxValue) {
				if (originIdx == location) {
					answer = result;
					break;
				} else {
					q.poll();
					result++;
				}
			}
		}
        return answer;
    }
}