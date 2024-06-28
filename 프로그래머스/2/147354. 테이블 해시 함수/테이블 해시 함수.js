function solution(data, col, row_begin, row_end) {
    
    data.sort((a,b)=>{
        let A = a[col-1];
        let B = b[col-1];
        
        if(A === B)
            return b[0] - a[0];
        else
            return A - B;
    })
    //console.log();
    var answer = data.slice(row_begin -1, row_end).reduce((prev, now, idx)=>{
        //console.log(now)
        let s_i = now.reduce((p,n) => p + (n % (idx+row_begin)), 0)
        //console.log(s_i);
        return prev ^ s_i;
    }, 0);
    
    return answer;
}