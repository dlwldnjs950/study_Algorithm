class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        for(int idx=0; idx < numbers.length; idx++){
            long number = numbers[idx];
            // 짝수
            if((number & 1) == 0){
                answer[idx] = number +1;
            // 홀수
            }else{
                long tmp = number | number + 1; // 가장 오른쪽 0을 1로 바꿈
                // 바뀐 가장 오른쪽 0 자리 찾기
                long change = number ^ tmp;
                // 그 다음 자리로 옮기기
                change = change >>> 1;
                // 0으로 바꾸기 위해 비트 반전
                change = ~change;
                // & 연산을 통해 해당 자리 0으로 바꿈
                tmp = tmp & change;
                
                answer[idx] = tmp;
            }
        }
        
        return answer;
    }
}