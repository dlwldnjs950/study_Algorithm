import java.util.*;
/*
한번 사용한 객실은 퇴실 시간을 기준으로 10분간 청소를 하고 다음 손님이 사용
하루를 넘기는 예약 시간은 없고
종료시간 + 10분
시간 문자열을 분으로 바꿔...보자!
종료 시간을...기준으로 정렬하고...
자료구조의 길이...를 리턴했는데

*/
class Solution {
    class Time implements Comparable<Time>{
        long start;
        long end;
        
        Time(long start, long end){
            this.start = start;
            this.end = end;
        }
        
        //@Override
        public int compareTo(Time time){
            return (int)(this.start - time.start);
        }
    }
    public int solution(String[][] book_time) {
        
        Time[] bookTimes = new Time[book_time.length];
        for(int idx = 0; idx < book_time.length; idx++){
            bookTimes[idx] = new Time(returnMinute(book_time[idx][0]), returnMinute(book_time[idx][1])+10);
            //System.out.println(bookTimes[idx].start+" "+bookTimes[idx].end);
        }
        
        Arrays.sort(bookTimes);
        
        PriorityQueue<Long> rooms = new PriorityQueue<>();
        for(Time bookTime : bookTimes){
            //System.out.println(bookTime.start + " "+ bookTime.end);
            if(!rooms.isEmpty()){
                if(rooms.peek() <= bookTime.start)
                    rooms.poll();
            }
                
            rooms.add(bookTime.end);
        }
        
        return rooms.size();
    }
    
    public long returnMinute(String time){
        String[] timeArr = time.split(":");
        long hour = Integer.parseInt(timeArr[0]);
        long minute = Integer.parseInt(timeArr[1]);
        
        long realTime = hour * 60 + minute;
        
        return realTime;
    }
}