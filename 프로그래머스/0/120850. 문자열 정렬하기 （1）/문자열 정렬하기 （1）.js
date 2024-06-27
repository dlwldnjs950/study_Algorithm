function solution(my_string) {
    var answer = [];
    [...my_string].map((s)=>{
        if(s.match('[0-9]'))
            answer.push(+s);
    })
    return answer.sort();
}