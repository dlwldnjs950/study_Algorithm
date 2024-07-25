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
                
        PriorityQueue<Work> queue = new PriorityQueue<>((a,b)->a.start - b.start);
        Stack<Work> stack = new Stack<>();
        for(String[] plan : plans){
            queue.add(new Work(plan[0], toMinute(plan[1]), Integer.parseInt(plan[2])));
        }
        
        List<String> answer = new ArrayList<>();
        
        
        while(!queue.isEmpty()){
            Work current = queue.poll();
            
            int nowTime = current.start;            
            // 다음 과제가 있으면
            if(!queue.isEmpty()){
                
                Work next = queue.peek();
                
                // 다음 과제까지 시간이 남아있으면
                if(current.start + current.playtime < next.start){
                    answer.add(current.name);
                    nowTime += current.playtime;
                    
                    // 미뤄둔 과제하기
                    while(!stack.isEmpty()){
                        
                        Work remain = stack.pop();
                        
                        // 다음 과제 전에 끝낼 수 있으면
                        if(nowTime + remain.playtime <= next.start){
                            nowTime += remain.playtime;
                            answer.add(remain.name);
                        }else{
                            // 수행한 만큼 차감
                            remain.playtime -= next.start - nowTime;
                            stack.add(remain);
                            break;
                        }
                        
                    }
                }
                
                // 과제 종료 시간과 동일하면 완료 후 다음 과제
                else if(current.start + current.playtime == next.start){
                    answer.add(current.name);
                    continue;
                    
                }
                
                // 다음 과제 먼저 해야할 경우
                else{
                    current.playtime -= next.start - current.start;
                    stack.push(current);
                }
            }
            
            // 다음 과제가 없으면 미뤄둔 과제
            else{
                
                // 남은 과제가 없으면
                if(stack.isEmpty()){
                    // 최근 과제 수행하기
                    answer.add(current.name);
                }
                
                // 남은 과제가 있으면
                else{
                    // 최근 과제부터 수행하고
                    answer.add(current.name);
                    
                    while(!stack.isEmpty()){
                        answer.add(stack.pop().name);
                    }
                }
            }
            
        }
        
        return answer;
    }
}