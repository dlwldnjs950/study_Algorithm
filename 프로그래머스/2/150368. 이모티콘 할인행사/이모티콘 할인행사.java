import java.util.*;
class Solution {
    public int maxJoin=-1, maxSale=-1, minPercent=50;
    public int[] percents = {};
    public int[] solution(int[][] users, int[] emoticons) {
        
        // 이모티콘 할인률 결정
        percents = new int[emoticons.length];
        
        // 확인 횟수 줄이기 위한 최소 할인율 결정
        
        for(int[] user:users){
            minPercent = Math.min(minPercent,user[0]);
        }
        minPercent = (int)(Math.ceil((double)minPercent/10)*10);
        
        setSalePercent(0, emoticons.length, users, emoticons);
        
        int[] answer = {maxJoin, maxSale};
        return answer;
    }
    
    // 지금까지선택한할인율, 할인율
    public void setSalePercent(int cnt, int emoticonCnt, int[][] users, int[] emoticons){
        
        if(cnt==emoticonCnt){
            // 계산하고
            calculate(users, emoticons);
            //System.out.println(Arrays.toString(percents));
            return;
        }
        
        for(int percent=minPercent; percent<=40; percent+=10){
            percents[cnt] = percent;
            setSalePercent(cnt+1, emoticonCnt, users, emoticons);
        }
    }
    
    public void calculate(int[][] users, int[] emoticons){
        int join = 0; 
        int sale = 0;
        
        for(int[] user: users){
            int tmpSum=0;
            
            for (int idx = 0; idx < emoticons.length; idx++) {
                if (percents[idx] < user[0]) continue;
                tmpSum += sale(emoticons[idx], percents[idx]);
            }
            
            if (user[1] <= tmpSum) join++;
            else sale += tmpSum;
        }
        
        if (join > maxJoin) {
            maxJoin = join;
            maxSale = sale;
        } else if (join == maxJoin && sale > maxSale) {
            maxSale = sale;
        }
        
    }
    
    // 할인가
    public int sale(int price, int percent) {
        return (price / 100) * (100 - percent);
    }
}