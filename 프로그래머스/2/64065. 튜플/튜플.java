import java.util.*;
import java.io.*;

/*
튜플 : 셀 수 있는 순서있는 열거 또는 어떤 순서를 따르는 요소들의 모임
n개의 요소를 가진 튜플 = n-튜플

    중복된 원소 가능
    원소 정해진 순서 있음 (순서 다르면 서로 다른 튜플)
    튜플의 원소 개수 유한
    
튜플을 표현하는 집합 : 그냥 부분집합이 아니라 순서대로 원소 하나씩 추가해서 만드는 집합

집합이 주어질 때, 해당 집합이 표현하는 튜플을 찾아라!

튜플에는 순서가 있다.
문자열은 ,를 기준으로 split
집합에서 원소를 빼내는 함수를 하나 만들고 => 정렬해버리자!
원소 개수가 작은 것 부터 하나씩 붙여나감...
*/

class Solution {
    public List<Integer> solution(String s) {
        
        // 각 집합을 분리할 때는 },{를 기준으로 해야한다
        String[] numberSets = s.substring(1,s.length()-1).split("\\},\\{");
        List<List<Integer>> numbersList = new ArrayList<>();
        for(String numberSet : numberSets){
            List<Integer> numbers = removeBracket(numberSet);
            numbersList.add(numbers);
        }
        Collections.sort(numbersList, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> s1, List<Integer> s2) {
                return s1.size() - s2.size();
            }
        });

        
        // 각 집합을 하나씩...어케 넣쥬?
        // 넣을 때 마다 이미 있는 원소를 remove한 다음에..해도 되나?
        // 원소를 맵으로 관리?
        List<Integer> answer = new ArrayList<>();
        for(List<Integer> numbers : numbersList){
            numbers.removeAll(answer);
            int num = numbers.get(0);
            answer.add(num);
        }
        return answer;
    }
    
    public List<Integer> removeBracket(String numberSet){
        // 괄호가 포함된 친구들이 들어올 수 있음
        String[] numberStrs = numberSet.replaceAll("\\{","").replaceAll("\\}","").split(",");
        List<Integer> numbers = new ArrayList<>();
        // 숫자로 변환
        for(int idx=0; idx<numberStrs.length; idx++){
            numbers.add(Integer.parseInt(numberStrs[idx]));
        }
        
        return numbers;
    }
}