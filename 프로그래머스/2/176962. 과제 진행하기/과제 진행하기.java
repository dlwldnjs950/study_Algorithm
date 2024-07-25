import java.util.*;

class Solution {
    
    class Work{
        String name;
        int start;
        int playtime;
        
        Work(String name, int start, int playtime){
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
    }
    
    int toMinute(String time){
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);
        
        return hour * 60 + minute;
    }
    
    public List<String> solution(String[][] plans) {
                
        // 시작 시간을 기준으로 정렬되야 함
        PriorityQueue<Work> queue = new PriorityQueue<>((a,b)->a.start - b.start);
        // 중단된 과제 목록
        Stack<Work> stack = new Stack<>();
        for(String[] plan : plans){
            queue.add(new Work(plan[0], toMinute(plan[1]), Integer.parseInt(plan[2])));
        }
        
        // 정답
        List<String> answer = new ArrayList<>();
        
        while(!queue.isEmpty()){
            // 최근 과제
            Work current = queue.poll();
            
            // 다음 과제가 남아있다면
            // 왜냐면 다음으로 새 과제를 해야하는 지 미뤄둔 과제를 해야하는지 결정해야 함
            if(!queue.isEmpty()){

                Work next = queue.peek();
                
                // 다음 과제까지 시간이 남아있다면
                if(current.start + current.playtime < next.start){
                    
                    // 최근 과제 수행하고
                    answer.add(current.name);
                    
                    // 미뤄둔 과제
                    int currentTime = current.start + current.playtime;
                    while(!stack.isEmpty()){
                        
                        Work remain = stack.pop();
                        
                        if(currentTime + remain.playtime <= next.start){
                            currentTime += remain.playtime;
                            answer.add(remain.name);
                        }else{
                            remain.playtime -= next.start - currentTime;
                            stack.push(remain);
                            break;
                        }
                    }
                }
                
                // 최근 과제 종료와 동시에 시작
                else if(current.start + current.playtime == next.start){
                    answer.add(current.name);
                }
                
                // 다음 과제부터 해야한다면
                else{
                    current.playtime -= next.start - current.start;
                    stack.push(current);
                }
            }
            
            // 다음 과제가 없다면
            else{
                // 일단 최근 과제를 하고
                answer.add(current.name);
                
                // 미뤄둔 과제를 한다
                while(!stack.isEmpty()){
                    answer.add(stack.pop().name);
                }
            }
        }
        
        
        
        return answer;
    }
}