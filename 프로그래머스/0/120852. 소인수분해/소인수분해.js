function solution(n) {
    var answer = [];
    let numSet = new Set();
    
    for(let num=2; num * num <= n; num++){
        while(n % num === 0){
            numSet.add(num);
            n = Math.trunc(n/num);
        }
    }
    if(n != 1){
        numSet.add(n);
    }
    
    return Array.from(numSet);
}