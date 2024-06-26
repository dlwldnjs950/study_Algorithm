function solution(array) {
    var answer = [0,0];
    array.map((a,idx)=>{
        if(a > answer[0]){
            answer[0] = a;
            answer[1] = idx;
        }
    })
    return answer;
}