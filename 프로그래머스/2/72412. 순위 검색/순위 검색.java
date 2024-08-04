import java.util.*;

class Solution {
    
    // 쿼리문과 해당하는 사용자 점수 목록
    Map<String, List<Integer>> queryMap;
    
    public List<Integer> solution(String[] info, String[] query) {
        
        queryMap = new HashMap<>();
        List<Integer> answer = new ArrayList<>();
        
        // queryScore부터 채우기
        for(String i : info){
            String[] infos  = i.split(" ");
            makeQuery(infos, 0, "");
        }
        
        // 점수 목록 정렬
        for(String key : queryMap.keySet()){
            Collections.sort(queryMap.get(key));
        }
        
        for(String qr : query){
            String[] qrs = qr.replace(" and ","").split(" ");
            int targetScore = Integer.parseInt(qrs[1]);
            
            int cnt = queryMap.containsKey(qrs[0]) ? binarySearch(queryMap.get(qrs[0]), targetScore) : 0;
            answer.add(cnt);
        }
        
        return answer;
    }
    
    void makeQuery(String[] infos, int idx, String query){
        
        if(idx == 4){
            if(!queryMap.containsKey(query)){
                queryMap.put(query, new ArrayList<>());
            }
            queryMap.get(query).add(Integer.parseInt(infos[4]));
            return;
        }
        makeQuery(infos, idx + 1, query+"-");
        makeQuery(infos, idx + 1, query+infos[idx]);
    }
    
    int binarySearch(List<Integer> scores, int targetScore){
        int start = 0; int end = scores.size() -1;
                
        while(start <= end){
            int mid = (start + end)/2;
            
            if(scores.get(mid) < targetScore)
                start = mid +1;
            else
                end = mid -1;
        }
        
        return scores.size() - start;
    }
}