function solution(num_list, n) {
    var answer = [];
    let tmp = [];
    for(num of num_list){
        tmp.push(num);
        if(tmp.length === n){
            answer.push(tmp);
            tmp = [];
        }
    }
    return answer;
}