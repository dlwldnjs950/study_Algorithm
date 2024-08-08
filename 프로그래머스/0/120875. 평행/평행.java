// 기울기 비교!
class Solution {
    
    public int solution(int[][] dots) {        
        
        int[][] idxList = {{0,1,2,3}, {0,2,1,3}, {0,3,1,2}};
        for(int idx = 0; idx<3; idx++){
            double gradient1 = gradient(dots[idxList[idx][0]], dots[idxList[idx][1]]);
            double gradient2 = gradient(dots[idxList[idx][2]], dots[idxList[idx][3]]);
            
            if(Double.compare(gradient1, gradient2) == 0)
                return 1;
        }
        return 0;
    }
    
    double gradient(int[] spot1, int[] spot2){
        return (double)(spot2[1] - spot1[1]) / (spot2[0] - spot1[0]);
    }
    
}