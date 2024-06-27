function solution(array) {
    
    let arrMap = new Map();
    
    var answer = [];
    let maxCnt = 0;
    array.map(num => {
        arrMap.set(num, (arrMap.get(num) ?? 0) + 1);
        if(arrMap.get(num) > maxCnt){
            maxCnt = arrMap.get(num);
            answer = [];
            answer.push(num);
        }else if(arrMap.get(num) === maxCnt){
            answer.push(num);
        }
    })
    
    //console.log(answer);
    
    return answer.length===1 ? answer[0] : -1;
}