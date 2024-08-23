import java.util.*;

class Solution {
    
    class Task implements Comparable<Task>{
        int request;
        int taken;
        
        Task(int request, int taken){
            this.request = request;
            this.taken = taken;
        }
        
        public int compareTo(Task other){
            if(this.taken != other.taken)
                return this.taken - other.taken;
            else
                return this.request - other.request;
        }
    }
    public int solution(int[][] jobs) {
        int totalTakenTime = 0;
        
        Arrays.sort(jobs, (a,b)-> a[0] - b[0]);
        Queue<Task> queue = new ArrayDeque<>();
        PriorityQueue<Task> heap = new PriorityQueue<>();
        
        for(int[] job : jobs){
            queue.add(new Task(job[0],job[1]));
        }
        
        int currentTime = 0;
        while(!queue.isEmpty() || !heap.isEmpty()){
            
            while(!queue.isEmpty() && queue.peek().request <= currentTime){
                heap.add(queue.poll());
            }
            
            if(!heap.isEmpty()){
                Task current = heap.poll();
                currentTime += current.taken;
                totalTakenTime += currentTime - current.request;
            }else if(!queue.isEmpty()){
                currentTime = queue.peek().request;
            }
        }
        
        return totalTakenTime / jobs.length;
    }
}