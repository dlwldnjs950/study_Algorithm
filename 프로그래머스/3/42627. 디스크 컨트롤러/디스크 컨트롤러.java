import java.util.*;

class Solution {
    class Work implements Comparable<Work> {
        int request;
        int taken;

        Work(int request, int taken) {
            this.request = request;
            this.taken = taken;
        }

        public int compareTo(Work o) {
            return this.taken - o.taken;
        }
    }

    public int solution(int[][] jobs) {
        // jobs를 요청 시간 기준으로 정렬
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        Queue<Work> queue = new LinkedList<>(); // 요청 큐
        PriorityQueue<Work> heap = new PriorityQueue<>(); // 대기 힙 (작업 소요 시간 기준으로 정렬)

        // 요청 큐에 넣기
        for (int[] job : jobs) {
            queue.add(new Work(job[0], job[1]));
        }

        int currentTime = 0;
        int totalWaitingTime = 0;
        int count = jobs.length;

        while (!queue.isEmpty() || !heap.isEmpty()) {
            // 현재 시점에서 처리 가능한 모든 작업을 대기 힙에 추가
            // 요청 시간이 같은 작업이 여러개일수도 있으니까
            // 이것도 힙에 넣어서 순서를 정해서 작업해야함
            while (!queue.isEmpty() && queue.peek().request <= currentTime) {
                heap.add(queue.poll());
            }

            // 대기 힙에서 작업을 처리
            if (!heap.isEmpty()) {
                Work current = heap.poll();
                currentTime += current.taken;
                totalWaitingTime += currentTime - current.request;
            } else if (!queue.isEmpty()) {
                // 대기 힙이 비어있다면, 다음 작업의 요청 시간으로 이동
                currentTime = queue.peek().request;
            }
        }

        return totalWaitingTime / count;
    }
}
