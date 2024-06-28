function solution(numbers, k) {
    while(--k){
        numbers.push(numbers.shift());
        numbers.push(numbers.shift());
    }
    return numbers[0];
}