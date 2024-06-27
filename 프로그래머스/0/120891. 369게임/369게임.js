function solution(order) {
    return String(order).split('').filter(n=>n.match('[369]')).length;
}