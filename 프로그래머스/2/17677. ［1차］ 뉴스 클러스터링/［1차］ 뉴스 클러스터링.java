import java.util.*;
import java.io.*;
/*
자카드 유사도 : 두 집합의 교집합의 크기를 두 집합의 합집합의 크기로 나눈 값 (교/합)
    두 집합이 공집합인 경우 1
자카드 유사도외 다중 집합
    중복 원소에 대해서 교집합 : 최소개수, 합집합 : 최대개수 로 해서 계산
문자열 유사도에 이용
    문자열을 두글자씩 끊어서 다중집합을 만들어 계산한다.
    
    정규식을 또 쓰는군...
*/
class Solution {
    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        // 다중집합 구성하기
        List<String> multiset1 = makeMultiset(str1);
        int length1 = multiset1.size();
        List<String> multiset2 = makeMultiset(str2);
        int length2 = multiset2.size();
        
        // Map이 아니라 집합 배열을 리턴해서 
        // 자카드 지수를 구 하 자
        // 교집합
        List<String> interset = new ArrayList<>();
        for(String tmp : multiset1){
            if(multiset2.remove(tmp))
                interset.add(tmp);
        }
        int interCnt = interset.size();
        
        // 합집합 : 수 + 수 -교
        int unionCnt = length1 + length2 - interCnt;
        
        int num = 65536;
        
        if(interCnt==0 && unionCnt==0)
            return num;
        
        float jaccard = (float)interCnt/(float)unionCnt;
        
        int answer = (int)(num * jaccard);
        return answer;
    }
    
    public List<String> makeMultiset(String str){
        List<String> multiset = new ArrayList<>();
        
        for(int idx=2; idx<=str.length(); idx++){
            String sub = str.substring(idx-2, idx);
            // 소문자만 포함된 경우에만
            if(sub.matches("[a-z]{2}"))
                multiset.add(sub);
        }
        
        return multiset;
    }
}