import java.util.*;
import java.io.*;
/*
자릿수마다 끊어 말하기
10~15는 대문자 출력
그냥 String에 다 붙인다음에 인덱스로 찾아서 answer에 붙이기
n진수 구하기만 잘하면 될듯
*/
class Solution {
    public String solution(int n, int t, int m, int p) {
        // 미리 구할 숫자의 개수가 반복문 돌리는 횟수가 되겠구나.....
        StringBuilder speakNum = new StringBuilder(0);
        
        int number = 0;
        while(speakNum.length()/m <= t){
            speakNum.append(Integer.toString(number++,n).toUpperCase());
            //speakNum.append(nBase(n,number++));
        }
        System.out.println(speakNum);
        String answer = "";
        for(int idx=p-1; idx<speakNum.length() && answer.length()<t; idx+=m){
            answer+=speakNum.charAt(idx);
        }
        return answer;
    }
    
    public String nBase(int base, int number){
        String result = "";
        while(number>0){
            int tmp = number%base;
            
            if(tmp >= 10){
                tmp += 'A' - 10;
            }else{
                tmp += '0';
            }
            result = (char)tmp + result;
            
            number/=base;
        }
        
        return result;
    }
}