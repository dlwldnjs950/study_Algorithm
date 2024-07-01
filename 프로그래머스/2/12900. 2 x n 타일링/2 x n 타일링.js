function solution(n) {
    var answer = 0;
    let dp = [];
    dp[0] = 1;
    dp[1] = 2;
    for(let num = 2; num < n; num++){
        dp[num] = (dp[num-2] + dp[num-1]) % 1000000007;
    }
    return dp[n-1];
}
