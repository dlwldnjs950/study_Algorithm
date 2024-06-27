function solution(n) {
    const piece = 6;
    // n 과 piece의 공배수
    return n / gcd(n, piece);
}

function gcd(a,b){
    while(b != 0){
        let c = a % b;
        a = b;
        b = c;        
    }
    return a;
}