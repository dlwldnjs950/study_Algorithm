class Solution {
    public int solution(int n) {
        // n이 홀수인 경우 바로 0을 반환
        if (n % 2 != 0) {
            return 0;
        }

        // 모듈러 연산을 위한 값
        int mod = 1000000007;
        
        // dp 배열 초기화 (n/2 만큼의 크기)
        long[] dp = new long[n / 2 + 1];
        dp[1] = 3;  // 3가지

        // 동적 프로그래밍을 통해 점화식 구현
        for (int i = 2; i <= n / 2; i++) {
            dp[i] = dp[i - 1] * 3;
            for (int j = 1; j < i - 1; j++) {
                dp[i] += dp[j] * 2; // 양쪽 모서리에 세로 타일 놓고 가운데에 i-2 만큼을 채우는거 * (위  모서리 or 아래 모서리)
            }
            dp[i] += 2;
            dp[i] %= mod; // 모듈러 연산으로 값이 너무 커지는 것을 방지
        }

        return (int)dp[n / 2];
    }
}