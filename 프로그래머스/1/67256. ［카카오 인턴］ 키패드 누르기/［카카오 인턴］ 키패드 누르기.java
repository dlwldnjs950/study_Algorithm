import java.util.*;
/*
좌우 엄지의 위치 저장
..거리계산 어케하지 (2,5,8,0만 하면 됨)
행*3+열+1 = 숫자
행 = (숫자 - (열+1))/3
열 = 숫자 - 1 - 행*3
0 : 행=3, 열=1
*/
class Solution {
    public static final String RIGHT = "R";
    public static final String LEFT = "L";
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        
        int leftF = 10;
        int rightF = 12;
        for(int number : numbers){
            // System.out.println(leftF+", "+rightF+", "+number);
            if(number==1 || number==4 || number==7){
                sb.append(LEFT);
                leftF = number;
            }else if(number==3 || number==6 || number==9){
                sb.append(RIGHT);
                rightF = number;
            }else{
                // 가까이 있는 손가락 찾기
                int leftDistance = distance(number, leftF);
                int rightDistance = distance(number, rightF);
            System.out.println(" => "+leftDistance+", "+rightDistance);
                
                if(leftDistance == rightDistance){
                    if("right".equals(hand)){
                        sb.append(RIGHT);
                        rightF = number;
                    }else if("left".equals(hand)){
                        sb.append(LEFT);
                        leftF = number;
                    }
                }else if(leftDistance > rightDistance){
                        sb.append(RIGHT);
                        rightF = number;
                    
                }else if(leftDistance < rightDistance){
                        sb.append(LEFT);
                        leftF = number;
                }
            }
            
        }
        
        return sb.toString();
    }
    
    public int[] numToRowCol(int num){
        if(num == 0){
            num=11;
        }
        
        return new int[]{(num-1)/3, (num-1)%3};
    }
    
    public int distance(int number, int finger){
        int[] nPoint = numToRowCol(number);
        int[] fPoint = numToRowCol(finger);
        
        //if(number== 2 && finger==3){
            // System.out.println(Arrays.toString(nPoint));
            // System.out.println(Arrays.toString(fPoint));
        //}
        return Math.abs(nPoint[0]-fPoint[0]) + Math.abs(nPoint[1]-fPoint[1]);
    }
}