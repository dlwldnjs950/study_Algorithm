import java.util.*;
/*
' + '을 기준으로 split하고
반복문을 돌려서
계수 배열
x를 포함하는지 아닌지
x를 포함하면 길이로 구분하잣
*/
class Solution {
    public String solution(String polynomial) {
        String[] polys = polynomial.split(" \\+ ");
        System.out.println(Arrays.toString(polys));
        int[] coeffis = new int[2]; //0 : x의 계수, 1 : 상수항
        
        for(String poly : polys){
            if(poly.contains("x")){
                if(poly.length() == 1){
                    coeffis[0]++;
                }else{
                    coeffis[0] += Integer.parseInt(poly.substring(0,poly.length()-1));
                }
            }else{
                coeffis[1] += Integer.parseInt(poly);
            }
        }
        StringBuilder answer = new StringBuilder();
        if(coeffis[0] > 0){
            if(coeffis[0] >1)
                answer.append(coeffis[0]);
            answer.append("x");
        }
        if(coeffis[1] > 0){
            if(coeffis[0] > 0)
                answer.append(" + ");
            answer.append(coeffis[1]);
        }
        return answer.toString();
    }
}