function solution(s) {
    s = s.split('').sort().join('');
    var answer = [];
    let count = 1;
    for(let idx = 1; idx <= s.length; idx++){
        if(s[idx] === s[idx-1])
            count++;
        else{
            if(count === 1)
                answer.push(s[idx-1])
            count = 1;
        }
    }
    return answer.join('');
}