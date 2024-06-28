function solution(balls, share) {
    var answer = factorial(balls) / factorial(balls - share) / factorial(share);
    return parseInt(answer.toFixed(0));
}

function factorial(num){
    if(num === 0)
        return 1;
    return num * factorial(num - 1);
}