function solution(before, after) {
    var answer = 0;
    before = before.split('').sort().join();
    after = after.split('').sort().join();
    
    return Number(before === after);
}