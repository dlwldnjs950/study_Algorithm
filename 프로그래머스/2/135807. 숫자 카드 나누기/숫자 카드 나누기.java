import java.util.*;
/*
숫자가 적힌 카드를 절반씩 나눠가진 후
다음 두 조건 중 하나를 만족하는 
가장 큰 양의 정수 a를 구하라
    상대가 가진 카드에 적힌 모든 숫자를 나눌 수 있지만
    내가 가진 카드에 적힌 모든 숫자는 나눌 수 없는 양의 정수
나눠가진 카드로 주어지기 때문에!
혹시 모르니까 정렬을 하고
숫자들의 최대공약수를 찾아야함
각 사람의 최대공약수 구하고
그 공약수들의 최대공약수를 확인하고
0일 경우 둘 중에 큰 수를 정답으로...?
*/
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        
        int A = arrayA[0]; int B = arrayB[0];
        // arrayA의 최대공약수 찾기
        for(int idx = 1; idx<arrayA.length; idx++){
            A = gcd(A, arrayA[idx]);
            B = gcd(B, arrayB[idx]);
            //System.out.println(A+" "+B);
        }
        
        //System.out.println(A+" "+B);
        boolean ableA = (A!=1); boolean ableB = (B!=1);
        for(int idx = 0; idx<arrayA.length; idx++){
            if(B != 1 && arrayA[idx] % B == 0)
                ableB = false;
            if(A != 1 && arrayB[idx] % A == 0)
                ableA = false;
        }
        //System.out.println(ableA +" "+ ableB);
        
        if(!ableA && !ableB)
            return 0;
        else if(ableA && !ableB)
            return A;
        else if(!ableA && ableB)
            return B;
        else
            return Math.max(A,B);
    }
    
    // 최대 공약수 구하기
    public int gcd(int a, int b){
        while(b!=0){
            int c = a % b;
            a = b;
            b = c;
        }
        
        return a;
    }
}