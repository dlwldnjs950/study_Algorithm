function solution(sides) {
    sides.sort((a,b)=>a-b);
    var answer = 0;
    
    // 나머지 한 변이 가장 긴변 : 차이 +1 ~ 긴변
    let case1 =  (sides[0] + sides[1] - 1) - (sides[1] + 1) + 1;
    
    // 둘중에 긴변이 가장 긴 변 : 긴변 +1 ~ 합 -1
    let case2 =  sides[1] - (sides[1] - sides[0] + 1) +1;
    console.log(case1,case2);
    return case1 + case2;
}