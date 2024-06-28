function solution(array, n) {
    array.sort()
    var answer = 0;
    let diff = 101;
    for(let idx = 0; idx < array.length; idx++){
        let tmpDiff = Math.abs(n - array[idx]);
        if(tmpDiff < diff){
            answer = array[idx];
            diff = tmpDiff;
        }
    }
    return answer;
}