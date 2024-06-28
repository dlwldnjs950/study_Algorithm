function solution(n) {
    let tmp = 1;
    let num = 1;
    for(; tmp*num <= n; num++){
        tmp *= num;
        // console.log(num, tmp)
    }
    return num -1;
}