class Solution {
    public int solution(int chicken) {
        int answer = 0;
        int extra = 0;
        while(chicken > 0){
            answer += chicken / 10;
            extra += chicken % 10;
            chicken /= 10;
            if(chicken == 0){
                if(extra < 10)
                    break;
                chicken = extra;
                extra = 0;
            }
        }
        
        return answer;
    }
}