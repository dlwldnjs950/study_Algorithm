import java.util.*;
/*
코스요리 구성
가장 많이 함께 주문한 단품끼리 코스로 구성
최소 두가지 이상 단품메뉴
최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합
코스 요리 메뉴 구성
*/
class Solution {
    
    public Map<String, Integer> courseCnt = new HashMap<>();
    public Map<Integer, Integer> maxCnt = new HashMap<>();
    public List<String> solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        
        for(String order:orders){
            char[] charOrder = order.toCharArray();
            Arrays.sort(charOrder);
            String sorted = new String(charOrder);
            //System.out.println(sorted);
            for(int co : course){
                combination(sorted,new boolean[order.length()], 0, order.length(), co,co, "");
            }
        }
                
        for(String str:courseCnt.keySet()){
            //System.out.println(str +" "+courseCnt.get(str));
            // 2회이상 주문했고, 최대 주문 수와 같다면
            if(courseCnt.get(str) >= 2 && courseCnt.get(str) == maxCnt.get(str.length()))
                answer.add(str);
        }
        Collections.sort(answer);
        return answer;
    }
    
    // n개중에 r개 뽑기
    public void combination(String order, boolean[] visited, int start, int n, int r,int rr, String midStr) {
        if(r == 0) {
            courseCnt.put(midStr, courseCnt.getOrDefault(midStr,0)+1);
            // 각 단품메뉴구성 개수의 최대값 갱신하기
            if(maxCnt.getOrDefault(rr,0) < courseCnt.get(midStr)){
                maxCnt.put(rr,courseCnt.get(midStr));
            }
            return;
        } 

        for(int i=start; i<n; i++) {
            visited[i] = true;
            combination(order, visited, i + 1, n, r - 1,rr, midStr+order.charAt(i));
            visited[i] = false;
        }
    }
}