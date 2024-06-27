function solution(numbers) {
    numbers.sort((a,b) => +a - +b);
    
    let length = numbers.length;
    var answer = Math.max(numbers[0]*numbers[1], numbers[length-1]*numbers[length-2]);    
        
    return answer;
}