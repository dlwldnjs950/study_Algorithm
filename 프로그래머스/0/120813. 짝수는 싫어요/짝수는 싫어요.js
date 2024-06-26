function solution(n) {
    var answer = [];
    
    for(let start = 1;start <= n; start +=2){
        answer.push(start);
    }
    return answer;
}