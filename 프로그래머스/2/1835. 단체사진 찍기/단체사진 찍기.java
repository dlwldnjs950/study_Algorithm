import java.util.*;
class Solution {
    
    class Option{
        String name;
        String target;
        String condition;
        int length; // 양: 초과, 음: 미만, 0: 같음
        
        public Option(String name, String target,String condition, int length){
            this.name = name;
            this.target = target;
            this.condition = condition;
            this.length = length;
        }
    }
    
    int answer; // 정답
    Option[] options;   // 조건 배열
    boolean[] isSelected;
    
    char[] friends;
    
    Set<String> tmp;
    
    public int solution(int n, String[] data) {
        
        options = new Option[n];       
        for(int idx = 0; idx<n; idx++){
            options[idx] = parseOption(data[idx]);
        }
        
        friends = new char[]{'A','C','F','J','M','N','R','T'};
        answer = 0;         
        isSelected = new boolean[friends.length];        
        
        tmp = new HashSet<>();
        permutation(0, friends.length, "");
        
        return answer;
    }
    
    // 경우의 수 만들기
    void permutation(int cnt, int friendsNum, String tmpOrder){
        
        // 가지 치기?
        for(Option opt : options){
            // 조건을 만족하지 못하면 더이상 확인하지 X
            if(!isOk(tmpOrder, opt))
                return;
        }
        
        // 순서 다 정했을 때 카운트
        if(cnt == friendsNum){
            answer++;
            return;
        }
        
        for(int idx = 0; idx < friendsNum; idx++){
            if(isSelected[idx])
                continue;
            isSelected[idx] = true;
            permutation(cnt +1, friendsNum, tmpOrder+ friends[idx]);
            isSelected[idx] = false;
        }
        
    }
    
    // 옵션 만들어내기
    Option parseOption(String data){
        
        String name = String.valueOf(data.charAt(0));
        String target = String.valueOf(data.charAt(2));
        String condition = String.valueOf(data.charAt(3));
        
        int length = (int)(data.charAt(4) - '0');
                
        return new Option(name, target, condition, length);
        
    }
    
    // 조건 만족하는지 확인
    boolean isOk(String order, Option option){
        if(order.contains(option.name) && order.contains(option.target)){
        
            int indexName = order.indexOf(option.name);
            int indexTarget = order.indexOf(option.target);

            int diff = Math.abs(indexName - indexTarget) -1;

            switch(option.condition){
                case "=":
                    if(diff != option.length)
                        return false;
                    break;
                case "<":
                    if(diff >= option.length)
                        return false;
                    break;
                case ">":
                    if(diff <= option.length)
                        return false;
                    break;
            }
        }
        
        return true;
    }
}
