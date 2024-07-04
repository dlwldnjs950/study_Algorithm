class Solution {
    int dgNum;      
    boolean[] visited;
    int answer = -1;
    
    public int solution(int k, int[][] dungeons) {
        dgNum = dungeons.length;
        visited = new boolean[dgNum];
        
        makeOrder(0, k, 0, dungeons);
        
        return answer;
    }
    
    // 던전 방문 순서 정하기 => 순열
    void makeOrder(int cnt, int tired, int visitCnt, int[][] dungeons){
        if(cnt == dgNum){
            answer = Math.max(answer, visitCnt);
            return;
        }
        for(int idx = 0; idx < dgNum; idx++){
            if(visited[idx])
                continue;
            visited[idx] = true;
            // 순서는 정했지만, 갈 수 있는지 확인
            if(dungeons[idx][0] <= tired){
                makeOrder(cnt + 1, tired - dungeons[idx][1], visitCnt + 1, dungeons);
            }else{
                makeOrder(cnt + 1, tired, visitCnt, dungeons);
            }
            
            visited[idx] = false;
        }
        
    }
}