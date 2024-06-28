function solution(emergency) {
    // 각 응급도가 몇번째 우선 순위를 가지는지 정하라는 뜻!
    
    var sorted = emergency.slice(0,emergency.length).sort((a,b)=> b - a);
    
    emergency = emergency.map(e=> sorted.indexOf(e) +1);
    return emergency;
}