class Solution {
    // 완전탐색 - 최소직사각형
/* 다른 풀이
 * foreach를 사용한다
 * 두 수의 자리를 바꿀 필요 없이 max와 min으로 큰수-작은수로 고려할 수 있다.
 * */
    public int solution(int[][] sizes) {
        int max0 = 0, max1 = 0;
		for (int[] card : sizes) {
			max0 = Math.max(max0, Math.max(card[0], card[1]));
			max1 = Math.max(max1, Math.min(card[0], card[1]));
        }
        
        return max0*max1;
    }
}