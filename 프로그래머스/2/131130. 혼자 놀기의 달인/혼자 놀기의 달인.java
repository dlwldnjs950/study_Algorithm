import java.util.*;
class Solution {
    public int solution(int[] cards) {
        
        int cLength = cards.length;
        
        // 카드 상자 열었는지 여부
        boolean[] isOpened = new boolean[cLength + 1];
        
        // 카드 그룹 크기 목록
        List<Integer> sizes = new ArrayList<>();
        
        for(int idx = 1; idx <=cLength; idx++){
            
            if(!isOpened[idx]){
                
                List<Integer> group = new ArrayList<>();
                
                int currentIdx = idx;
                while(!isOpened[currentIdx]){
                    group.add(currentIdx);
                    isOpened[currentIdx] = true;
                    
                    currentIdx = cards[currentIdx -1];
                }
                
                sizes.add(group.size());
            }
        }
        
        if(sizes.size() <=1)
            return 0;
        
        Collections.sort(sizes);
        
        int size = sizes.size();
        return sizes.get(size -1) * sizes.get(size -2);
    }
}