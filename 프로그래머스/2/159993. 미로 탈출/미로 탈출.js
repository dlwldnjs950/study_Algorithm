function solution(maps) {
    
    let height = maps.length;
    let width = maps[0].length;
    
    // 시작지점 S
    let start = new Point(-1, -1);
    // 도착지점: 레버 L 또는 출구 E
    let end = Array.from({length:2}, ()=>new Point(-1,-1));
    
    for(let rIdx = 0; rIdx < height; rIdx++){
        for(let cIdx = 0; cIdx < width; cIdx++){
            if(maps[rIdx][cIdx] === 'S')
                start = new Point(rIdx, cIdx);
            else if(maps[rIdx][cIdx] === 'L')
                end[0] = new Point(rIdx, cIdx);
            else if(maps[rIdx][cIdx] === 'E')
                end[1] = new Point(rIdx, cIdx);            
        }
    }
    
    var answer = -1;
    
    // 방문지 관리
    let targetEnd = 0;  // 레버 올리고 나면 ++
    let queue = [];
    var isVisited = Array.from({length:height},()=>Array(width).fill(false));
    
    queue.push(start);
    isVisited[start.x][start.y] = true;
    
    let dx = [0,-1,0,1]; 
    let dy = [1,0,-1,0];

    while(queue.length !== 0){
        
        let size = queue.length;
        for(let i = 0; i < size; i++) {
            let current = queue.shift();
            
            // end에 도착
            if(current.compare(end[targetEnd])){
                // 레버를 올리는 거였다면
                if(maps[current.x][current.y] === 'L'){
                    targetEnd++;
                    isVisited = Array.from({length:height},()=>Array(width).fill(false));
                    queue = [];
                    queue.push(current);
                    isVisited[current.x][current.y] = true;
                    break;
                }
                // 출구라면
                if(maps[current.x][current.y] === 'E'){
                    return answer;
                }
            }
            
            for(let direction=0; direction<4; direction++){
                let nx = current.x + dx[direction];
                let ny = current.y + dy[direction];
                
                if(nx < 0 || ny < 0 || nx >= height || ny >= width)
                    continue;
                if(isVisited[nx][ny])
                    continue;
                
                // 벽이면 지나갈 수 없다
                if(maps[nx][ny] === 'X')
                    continue;
                
                queue.push(new Point(nx, ny));
                isVisited[nx][ny] = true;
            }
        }
        answer++;
    }
    
    return -1;
}

class Point{
    
    constructor(x, y){
        this.x = x;
        this.y = y;
    }
    
    compare(other){
        return this.x === other.x && this.y === other.y;
    }
}
