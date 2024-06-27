function solution(n, k) {
    var answer = [];
    var numbers = Array.from({ length: n }, (_, i) => i + 1);
    // var fact = Array.from({ length: n }, (_, i) => factorial(i));
    var fact=[];
    fact[0] = 1;
    fact[1] = 1;
    for(let num = 2; num <= n; num++){
        fact.push(fact[num-1] * num);
    }

    k--;  // k를 0-based 인덱스로 변경

    for (var i = n - 1; i >= 0; i--) {
        var idx = Math.floor(k / fact[i]);
        answer.push(numbers[idx]);
        numbers.splice(idx, 1);  // 사용한 숫자 제거
        k %= fact[i];
    }

    return answer;
}

function factorial(n) {
    var result = 1;
    for (var i = 2; i <= n; i++) {
        result *= i;
    }
    return result;
}
