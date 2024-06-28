function solution(bin1, bin2) {
    var answer = '';
    let num = 1;
    console.log();
    return (decimal(bin1)+decimal(bin2)).toString(2);
}

function decimal(bin){
    let result = 0;
    let num=1;
    for(let idx=bin.length-1; idx>=0; idx--){
        result += (num * parseInt(bin[idx]));
        num *=2;
    }
    return result;
}