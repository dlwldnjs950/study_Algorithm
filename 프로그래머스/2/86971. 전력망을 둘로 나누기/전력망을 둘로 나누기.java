import java.util.*;
class Solution {
    public int solution(int n, int[][] wires) {
        List<ArrayList<Integer>> graph = new ArrayList<>();
        
        for(int loop = 0; loop<n; loop++){
            graph.add(new ArrayList<Integer>());
        }
        
        for(int[] wire : wires){
            int from = wire[0] -1;
            int to = wire[1] -1;
            
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        
        int answer = Integer.MAX_VALUE;
        for(int[] wire : wires){
            int cnt = 0;
            int start = wire[0] -1;
            int except = wire[1] -1;
            
            // 각 노드를 한번씩 제외하면서 한쪽 카운트
            boolean[] visited = new boolean[n];
            Queue<Integer> q = new ArrayDeque<>();
            q.add(start);
            visited[start] = true;
            
            while(!q.isEmpty()){
                cnt++;
                int current = q.poll();
                for(int next : graph.get(current)){
                    if(visited[next])
                        continue;
                    if(next == except)
                        continue;
                    q.add(next);
                    visited[next] = true;
                }
            }
            
            int diff = Math.abs(cnt - (n-cnt));
            answer = Math.min(answer, diff);
        }
        
        return answer;
    }
}