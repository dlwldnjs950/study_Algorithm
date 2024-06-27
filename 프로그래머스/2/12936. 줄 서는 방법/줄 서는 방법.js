function solution(n, k) {
    
    var numbers = Array.from({length:n}, (_,num)=>num + 1);
    var factorial = [];
    factorial[0] = 1;
    factorial[1] = 1;
    for(let num=2; num<n; num++){
        factorial[num] = factorial[num-1] * num;
    }
    
    var answer = [];
    k--;
    for(let idx = n-1; idx >=0; idx--){
         let target = Math.floor(k / factorial[idx]);
        answer.push(numbers[target]);
        numbers.splice(target, 1);
        //console.log(answer);
        k %= factorial[idx];
    }
    
    return answer;
}