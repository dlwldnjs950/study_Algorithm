import java.util.*;
/*
다트판에 다트 세차례 던져 점수 합계
점수
S, D(제곱), T(세제곱)
스타상 : 해당 점수와 바로 전에 얻은 점수 2배
아차상 : 해당 점수 마이너스
상 점수는 중첩 가능

다트를 3번만 던지니까 배열

*/
class Solution {
    public int solution(String dartResult) {
        char[] bonuses = new char[]{'S','D','T'};
        int[] numbers = new int[3];
        int numIdx = 0;
        for(int idx = 0; idx<dartResult.length(); idx++){
            char tmp = dartResult.charAt(idx);
            // 숫자면
            if(tmp>='0' && tmp<='9'){
                numbers[numIdx] = tmp-'0';
                // 10인지 확인
                if(tmp=='1' && idx<dartResult.length()-1 && dartResult.charAt(idx+1)=='0'){
                    numbers[numIdx] *=10;
                    idx++;
                }
                numIdx++;
            }else if(tmp == 'S' ||tmp == 'D' ||tmp == 'T'){
                if(tmp == 'D')
                    numbers[numIdx-1] = (int)Math.pow(numbers[numIdx-1],2);
                else if(tmp == 'T')
                    numbers[numIdx-1] = (int)Math.pow(numbers[numIdx-1],3);
            }else if(tmp == '*' ||tmp == '#'){
                if(tmp == '*'){
                    if(numIdx!=1){
                        numbers[numIdx-2] *= 2;
                    }
                    numbers[numIdx-1] *= 2;
                }else if(tmp == '#')
                    numbers[numIdx-1] = -1*numbers[numIdx-1];
            }
        }
        int answer = 0;
        for(int tmp : numbers){
            answer+=tmp;
        }
        return answer;
    }
}