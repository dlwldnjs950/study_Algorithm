import java.util.*;
/*
순열
중복처리 => Set
문자열로 순열 만들어서 숫자로 변환하여 저장
자릿수가 있네!
소수인지도 확인해야해!
*/
class Solution {
    
    Set<Integer> result = new HashSet<>();
    
    public int solution(String numbers) {
        int answer = 0;
        boolean[] isSelected = new boolean[numbers.length()];
        makeNumber(0, numbers, isSelected, "");
        return result.size();
    }
    
    public void makeNumber(int cnt, String numbers, boolean[] isSelected, String midNumber){
        
        if(!midNumber.equals("")){
			int number = Integer.parseInt(midNumber);
			if(isPrime(number)) result.add(number);
		}
        
        if(cnt == numbers.length())
            return;
        
        for(int idx=0; idx< numbers.length(); idx++){
            if(isSelected[idx])
                continue;
            isSelected[idx]=true;
            makeNumber(cnt + 1, numbers, isSelected, midNumber + numbers.charAt(idx));
            isSelected[idx]=false;
            
        }
    }
    
    public boolean isPrime(int number){
        if(number < 2)
            return false;
        for(int num=2; num*num <= number; num++){
            if(number%num ==0 ) return false;
        }
        return true;
    }
}