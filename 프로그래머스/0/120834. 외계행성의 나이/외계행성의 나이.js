function solution(age) {
    
    let toCharNum = 'a'.charCodeAt() - '0'.charCodeAt();
    console.log(toCharNum);
    
    var answer = [];
    for(a of String(age).split('')){
        let ascii = a.charCodeAt();
        answer.push(String.fromCharCode(ascii + toCharNum));
    }
    return answer.join('');
}