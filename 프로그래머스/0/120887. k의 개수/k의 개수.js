function solution(i, j, k) {
    var answer = 0;
    for(let num = i; num <= j; num++){
        if(String(num).includes(k))
            answer += (String(num).split(k).length -1);
    }
    return answer;
}