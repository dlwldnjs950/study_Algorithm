import java.util.*;
/*

*/
class Solution {
    
    public long solution(String expression) {
        long answer = -1;
        
        String[] numbers = expression.split("[+,\\-,*]");
        String[] symbols = expression.split("[0-9]+");
        symbols = Arrays.copyOfRange(symbols,1,symbols.length);
        
        // 연산자 우선순위 정하고
        String priority[][] = { { "+", "-", "*" }, { "+", "*", "-" }, { "-", "*", "+" }, 
                         { "-", "+", "*" }, { "*", "-", "+" }, { "*", "+", "-" } };
        
        // 계산하고        
        // 절댓값으로 max 판단
        for(int pIdx=0; pIdx<priority.length; pIdx++){
            List<String> numList = new ArrayList<>(Arrays.asList(numbers));
            List<String> symList = new ArrayList<>(Arrays.asList(symbols));
            
            for(int psymIdx=0; psymIdx<3; psymIdx++){
                for(int sIdx=0; sIdx<symList.size(); sIdx++){
                    if(priority[pIdx][psymIdx].equals(symList.get(sIdx))){
                        // 연산자
                        String operation = symList.remove(sIdx);
                        // 뒷숫자
                        String backNum = numList.remove(sIdx+1);
                        // 앞숫자
                        String frontNum = numList.get(sIdx);
                        // System.out.println(calc(frontNum, operation, backNum));
                        numList.set(sIdx, calc(frontNum, operation, backNum));
                        sIdx--;
                    }
                }
            }
            // System.out.println(numList.get(0));
            answer = Math.max(answer,Math.abs(Long.parseLong(numList.get(0))));
        }
        
        return answer;
    }
    private String calc(String num1, String op, String num2) {
        long n1 = Long.parseLong(num1);
        long n2 = Long.parseLong(num2);
 
        if (op.equals("+"))
            return String.valueOf(n1 + n2);
        else if (op.equals("-"))
            return String.valueOf(n1 - n2);
 
        return String.valueOf(n1 * n2);
    }
}