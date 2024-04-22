function solution(s){
    var answer = true;

    let cnt_p = (s.match(/p/gi)||[]).length;
    let cnt_y = (s.match(/y/gi)||[]).length;

    return cnt_p == cnt_y;
}