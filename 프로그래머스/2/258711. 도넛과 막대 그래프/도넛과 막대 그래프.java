import java.util.*;

/*
도넛, 막대, 8자 모양 그래프

그래프들과 무관한 정점을 하나 생성한 뒤
각 그래프와 간선으로 연결
각 정점에 번호

간선 정보가 주어지면 생성한 정점의 번호와
정점 생성하기 전 도넛, 막대, 8자 모양 그래프 수를 구하여라
*/

class Solution {
    public int[] solution(int[][] edges) {
        
        Map<Integer, int[]> nodes = new HashMap<>();
        
        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            
            if(!nodes.containsKey(from)){
    			nodes.put(from, new int[] {0, 0});
    		}
            if(!nodes.containsKey(to)){
    			nodes.put(to, new int[] {0, 0});
    		}
            
            nodes.get(from)[0] +=1;
            nodes.get(to)[1] +=1;
        }
        
        int[] answer = new int[4];
        
        for(int node : nodes.keySet()){
            int[] fromTo = nodes.get(node);
            
            if(fromTo[0] >= 2 && fromTo[1] == 0)
                answer[0] = node;
            else if(fromTo[0] == 0 && fromTo[1] > 0)
                answer[2]++;
            else if(fromTo[0] >= 2 && fromTo[1] >= 2)
                answer[3]++;
        }
        
        answer[1] = nodes.get(answer[0])[0] - answer[2] - answer[3];
        return answer;
    }
}