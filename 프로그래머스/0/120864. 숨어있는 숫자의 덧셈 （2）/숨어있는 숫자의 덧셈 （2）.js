function solution(my_string) {
    let new_str = my_string.split('').map(s =>{
        if(s.match('[0-9]'))
            return s
        else
            return '#'
    }).join('');
    
    return new_str.split(/#+/).reduce((prev,cur)=>prev+ +cur, 0);
}