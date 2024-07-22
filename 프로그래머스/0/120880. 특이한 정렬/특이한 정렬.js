function solution(numlist, n) {
    var answer = [];
    numlist.sort((a,b)=>{
        let diff = Math.abs(a-n) - Math.abs(b-n);
        if(diff === 0)
            return b-a;
        else return diff;
    });
    return numlist;
}