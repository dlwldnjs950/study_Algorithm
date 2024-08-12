import java.util.*;
// 크루스칼
class Solution {
    
    class Edge implements Comparable<Edge>{ // 가중치가 작은 것 부터 선택해야하므로 정렬 가능하게
        
        int from, to, weight;
        
        Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge o){
            // 빼기 연산으로 해도 되지만 Integer의 compare을 사용하는게 더...좋다
            return Integer.compare(this.weight, o.weight);
        }
    }
    
    // 크루스칼은 간선의 선택을 union-find를 이용한다
    Edge[] edgeList; // 간선 목록
    int V, E; // 정점과 간선 개수
    // 정점간 연결 상태를 관리할 부모 배열
    int[] parents;
    
    // union-find 연산 메소드
    
    void make(){ // 부모 배열 초기화 (자기 자신)
        parents = new int[V];
        for(int idx = 0; idx < V; idx++){
            parents[idx] = idx;
        }
    }
    
    int find(int a){ // 부모 찾기
        if(parents[a] == a) return a;
        // 부모의 부모를 찾으면서 갱신도 같이해서
        // 부모 배열 정보를 정리
        return parents[a] = find(parents[a]);
    }
    
    boolean union(int a, int b){ // 두 정점을 연결
        int aRoot = find(a);
        int bRoot = find(b);
        
        if(aRoot == bRoot) return false;    // 이미 한 부모 아래에 있음
        
        // 같은 부모 아래에 놓음
        parents[bRoot] = aRoot;
        return true;
    }
    
    public int solution(int n, int[][] costs) {
        
        V = n;
        E = costs.length;
        
        edgeList = new Edge[E];
        for(int idx = 0; idx < E; idx++){
            int from = costs[idx][0];
            int to = costs[idx][1];
            int weight = costs[idx][2];
            edgeList[idx] = new Edge(from, to, weight);
        }
        
        // 가중치 기준으로 정렬
        Arrays.sort(edgeList);
        
        make();
        
        int answer = 0;
        int vCount = 0; // 연결된 간선 개수
        
        for(Edge edge : edgeList){
            if(union(edge.from, edge.to)){
                answer += edge.weight;
                if(++vCount == V-1) break;
            }
        }
        
        return answer;
    }
}