function solution(numbers, direction) {
    if(direction === 'right'){
        let last = numbers.pop();
        numbers = [last, ...numbers];
    }else
        numbers.push(numbers.shift());
    return numbers;
}