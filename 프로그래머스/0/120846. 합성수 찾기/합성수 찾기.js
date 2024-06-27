function solution(n) {
    var answer = new Set();    
    for(let number=2; number<=n; number++){
        if(answer.has(number))
            continue;
        for(let num = 2; number * num <= n; num++){
            answer.add(number * num);
        }
    }
    return answer.size;
}