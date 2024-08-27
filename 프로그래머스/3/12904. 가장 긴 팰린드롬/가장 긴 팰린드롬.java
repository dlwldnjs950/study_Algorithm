class Solution
{
    public int solution(String s)
    {
        
        char[] sChar = s.toCharArray();
        
        int answer = 0;
        int sLength = s.length();
        boolean[][] isPalind = new boolean[sLength][sLength];
        
        // 길이 1 팰린드롬
        for(int idx = 0; idx < sLength; idx++){
            isPalind[idx][idx] = true;
            answer = 1;
        }
        
        // 길이 2 팰린드롬
        for(int idx = 0; idx+1 < sLength; idx++){
            // 서로 같으면 팰린드롬
            if(sChar[idx] == sChar[idx+1]){
                isPalind[idx][idx +1] = true;
                answer = 2;
            }
        }
        
        // 길이 3 이상 팰린드롬
        // 길이 늘려가면서 확인
        for(int length = 3; length <= sLength; length++){
            
            for(int idx = 0; idx + length <= sLength; idx++){
                int endIdx = idx + length -1;
                
                // 시작-끝 값이 서로 같고, 시작-끝 사이 부분이 팰린드롬이었는지 확인
                if(sChar[idx] == sChar[endIdx] && isPalind[idx +1][endIdx -1]){
                    isPalind[idx][endIdx] = true;
                    answer = length;
                }
            }
        }

        return answer;
    }
}