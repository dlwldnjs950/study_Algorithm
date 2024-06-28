function solution(M, N) {
    var answer = 0;
    let max = Math.max(M,N);
    let min = Math.min(M,N);
    
    
    return (max-1) + max * (min -1);
}