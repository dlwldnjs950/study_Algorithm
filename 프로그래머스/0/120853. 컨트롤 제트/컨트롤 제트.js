function solution(s) {
    var answer = 0;
    var str = s.split(' ');
    let plus = 0;
    //console.log(str)
    for(let idx = 0; idx < str.length; idx++){
        if(!isNaN(str[idx])){
            answer += parseInt(str[idx]);
            plus = parseInt(str[idx]);
        }else
            answer -= parseInt(plus);
        // console.log(str[idx], answer, plus);
    }
    return answer;
}