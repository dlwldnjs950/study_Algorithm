class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int[] numbers = new int[101];
        int idx = 1;
        
        for(int number = 1; idx<=100; number++){                
            numbers[idx] = number;
            String nStr = number+"";
            String three = "3";
            if(nStr.contains(three) || number % 3 == 0)
                continue;
            idx++;
        }
        
        return numbers[n];
    }
}