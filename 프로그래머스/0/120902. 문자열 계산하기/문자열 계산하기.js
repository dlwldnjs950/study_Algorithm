function solution(my_string) {
    let numbers = my_string.split(/[+|-]/);
    let opers = my_string.split(/[0-9]+/);
    var answer = +numbers[0];
    for(let idx = 1; idx < opers.length -1; idx++){
        if(opers[idx].trim() === '+')
            answer += +numbers[idx];
        else
            answer -= +numbers[idx];
    }
    return answer;
}