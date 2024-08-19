import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        
        List<List<Integer>> graph = new ArrayList<>();
        
        for(int loop=0; loop<n; loop++){
            graph.add(new ArrayList<>());
        }
        
        for(int[] e : edge){
            int from = e[0] -1;
            int to = e[1] -1;
            
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        
        queue.add(0);
        visited[0] = true;
        int answer = 0;
        while(!queue.isEmpty()){
            
            // 같은 거리의 노드 개수 저장
            int size = queue.size();
            answer = size;
            
            // 같은 거리 노드 개수 저장을 위해 반복문 이용
            for(int loop=0; loop<size; loop++){
                int current = queue.poll();
                
                for(int next : graph.get(current)){
                    if(visited[next])
                        continue;
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
        
        return answer;
    }
}