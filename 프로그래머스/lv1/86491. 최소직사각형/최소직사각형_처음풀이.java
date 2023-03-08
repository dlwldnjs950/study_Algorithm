class Solution {
    public int solution(int[][] sizes) {
        int max0 = 0, max1 = 0;
        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i][0] < sizes[i][1]) {    //큰수-작은수 순서로
                int temp = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = temp;
            }
            if (max0 < sizes[i][0])
                max0 = sizes[i][0];
            if (max1 < sizes[i][1])
                max1 = sizes[i][1];
        }

        return max0*max1;
    }
}
