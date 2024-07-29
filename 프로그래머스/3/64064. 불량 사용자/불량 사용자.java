import java.util.*;
/*
*을 .로 바꾸어 정규표현식으로 활용한다
각 banned_id에 해당하는 아이디 개수를 저장한다
이미 카운팅 된 아이디는 제외하기 위해 chk 배열을 만든다
=> 안됨...

가능한 아이디 리스트를 만들어서
완탐으로 종류를 카운트해야할듯...은 중복 경우 처리는 인덱스 붙여서 문자열로 만들어 Set으로...
[2, 1]
[2, 2, 2]
[2, 2, 2, 2]
*/
class Solution {
    
    
    boolean[] isSelected;
    int[] tmp_id_list;
    Set<String> banned_list = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        for(int idx = 0; idx < banned_id.length; idx++){
            banned_id[idx] = banned_id[idx].replace("*", ".");
        }
        
        // 각 banned_id별 제재 아이디
        List<List<Integer>> idx_list = new ArrayList<>();
        for(int loop = 0; loop < banned_id.length; loop++){
            idx_list.add(new ArrayList<>());
        }
        
        for(int bIdx = 0; bIdx<banned_id.length; bIdx++){
            for(int uIdx = 0; uIdx < user_id.length; uIdx++){
                if(user_id[uIdx].matches(banned_id[bIdx])){
                    idx_list.get(bIdx).add(uIdx);
                }
            }
            //System.out.println(idx_list.get(bIdx).toString());
        }
        
        isSelected = new boolean[user_id.length];
        tmp_id_list = new int[banned_id.length];
        makeList(0, idx_list);
        
        return banned_list.size();
    }
    
    void makeList(int cnt, List<List<Integer>> idx_list){
        
        if(cnt == idx_list.size()){
            // 함부로 정렬하지 말것...
             int[] sorted_tmp_id_list = tmp_id_list.clone();
            Arrays.sort(sorted_tmp_id_list);
            banned_list.add(Arrays.toString(sorted_tmp_id_list));
            return;
        }
        
        for(int idx : idx_list.get(cnt)){
            if(isSelected[idx])
                continue;
            tmp_id_list[cnt] = idx;
            isSelected[idx] = true;
            makeList(cnt + 1, idx_list);
            isSelected[idx] = false;
        }
    }
}