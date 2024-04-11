import java.util.*;
import java.io.*;

/*
	 * 길이 3 이상, 15자 이하
	 * 알파벳 소문자, 숫자, -, _, .
	 * .은 중간에만, 연속 사용 불가
	 * 
	 * 7단계 처리
	 * 1. 소문자 치환
	 * 2. 소문자, 숫자, -, _, . 제외 문자 제거
	 * 3. 연속 . 단일 .으로 치환
	 * 4. 처음/끝에 존재하는 . 제거
	 * 5. 빈문자열 -> a 대입
	 * 6. 길이 15까지만 남김 (이때, . 이 마지막이라면 제거
	 * 7. 길이 2 이하라면 마지막 문자를 3이 될때까지 붙임
	 * */
class Solution {
    static final int LIMIT = 15;
    public String solution(String new_id) {
        String answer = "";
        
        // 소문자로 변환
        new_id = new_id.toLowerCase();
        
        // 소문자, 숫자, -, _, .만 가능
        // \W : 알파벳대소문자+숫자+_의 부정
        new_id = new_id.replaceAll("[^\\w\\-\\.]","");
        
        // 단일 . 치환
        new_id = new_id.replaceAll("\\.{2,}",".");
        
        // 시작/끝 . 제거
        new_id = new_id.replaceAll("^\\.","");        
        new_id = new_id.replaceAll("\\.$","");
        
        // 빈문자열 -> a
        if(new_id.isBlank())
            new_id = "a";
        
        // 길이 15
        if(new_id.length()>=LIMIT+1){
            new_id = new_id.substring(0,LIMIT);
        }
        // (마지막 . 제거)     
        new_id = new_id.replaceAll("\\.$","");
        
        // 길이 2 이하면, 마지막 문자 반복
        if(new_id.length()<=2){
            char at = new_id.charAt(new_id.length()-1);
            while(new_id.length()<3){
                new_id+=at;
            }
        }
        
        answer = new_id;
        return answer;
    }
    
}