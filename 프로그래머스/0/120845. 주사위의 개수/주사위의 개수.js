function solution(box, n) {
    var answer = 1;
    box.map(num=>{
        answer *= Math.floor(num/n);
    });
    return answer;
}