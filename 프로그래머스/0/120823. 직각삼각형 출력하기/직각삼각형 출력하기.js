const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let input = [];

rl.on('line', function (line) {
    input = line.split(' ');
}).on('close', function () {
    let length = Number(input[0]) ;
    let print = [];
    for(let num = 1; num <= length; num++){
        for(let loop = 1; loop <= num; loop++){
            print.push('*');
        }   
        print.push('\n');
    }
    console.log(print.join(''));
});