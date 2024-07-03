function solution(quiz) {
    var answer = [];
    quiz = quiz.map(q=>{
        q = q.split('=').map(tmp=>tmp.trim());
        let lefts = q[0].split(' ');
        if(lefts[1] === '+')
            lefts = parseInt(lefts[0]) + parseInt(lefts[2]);
        else
            lefts = parseInt(lefts[0]) - parseInt(lefts[2]);
        
        return lefts == q[1] ? 'O':'X';
    })
    return quiz;
}