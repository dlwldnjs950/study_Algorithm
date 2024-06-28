function solution(my_str, n) {
    my_str = my_str.split('');
    var answer = [];
    while(my_str.length){
        answer.push(my_str.splice(0,n).join(''));
    }
    return answer;
}