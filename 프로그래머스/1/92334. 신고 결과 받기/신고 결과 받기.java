import java.util.*;
/*
불량 이용자 신고 후 처리 결과 메일 발송
    - 한번에 한명의 유저 신고
    - 여러번 신고해도 동일 유저에 대한 신고 횟수는 1회
        사용자 별 나를 신고한 사람들이 관리되어야함
        배열로 표시하는게 나을지도(맞는듯)
    - k번 이상 신고된 유저는 게시판 이용 정지
각 유저 별 처리결과 메일 받는 횟수
*/
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int userNum = id_list.length;
        Map<String, Integer> userIdx = new HashMap<>();
        // 나를 신고한 사람 리스트를 관리하자
        List<List<Integer>> userSingoList = new ArrayList<>();
        for(int idx = 0; idx<userNum; idx++){
            userIdx.put(id_list[idx], idx);
            userSingoList.add(new ArrayList<Integer>());
        }
        
        int[] singoCnt = new int[userNum];
        
        for(String re : report){
            String[] fromTo = re.split(" ");
            int from = userIdx.get(fromTo[0]);
            int to = userIdx.get(fromTo[1]);
            // 신고당한 횟수 카운트
            if(!userSingoList.get(to).contains(from)){
                userSingoList.get(to).add(from);
                singoCnt[to]++;
            }
        }
        System.out.println(Arrays.toString(singoCnt));
        
        // 각 사용자에 대해 정지 횟수에 해당하면        
        int[] answer = new int[userNum];
        for(int idx = 0; idx<userNum; idx++){
            String user = id_list[idx];
            if(singoCnt[idx] >= k){
                for(int u : userSingoList.get(idx)){
                    answer[u]++;
                }
            }
        }
        return answer;
    }
}