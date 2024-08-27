import java.util.*;
class Solution
{
    public int solution(String s)
    {

        char[] sChar = s.toCharArray();
        int length = s.length();
        outer : while(length > 0){
            for(int startIdx = 0; startIdx + length<= s.length(); startIdx++){
                
                int start = startIdx;
                int end = startIdx + length -1;
                boolean isPalind = true;

                while(start <= end){
                    if(sChar[start] != sChar[end]){
                        isPalind = false;
                        break;
                    }

                    start ++;
                    end --;
                }
                if(isPalind)
                    break outer;
            }
            length--;
        }
        
        return length;
    }
}