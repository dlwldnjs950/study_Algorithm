import java.util.*;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        
        Arrays.sort(timetable);
        int tLength = timetable.length;
        // 대기열
        int[] timeTable = new int[tLength];
        for(int idx = 0; idx < tLength; idx++){
            timeTable[idx] = parseMinute(timetable[idx]);
        }
        
        int remainRound = n;
        int currentTime = 60 * 9;   // t씩 증가
        int answer = 0;
        
        // 임시 탑승자
        Queue<Integer> tempBoard = new ArrayDeque<>();
        
        int idx = 0;
        while(remainRound > 0 && idx < tLength){
            answer = currentTime;
            // 셔틀 비우기
            tempBoard.clear();
            
            // 지금 타임 탑승자
            // m명만 탈 수 있다.
            for(int loop = 0; idx < tLength && loop < m ; loop++){
                if(timeTable[idx] <= currentTime){
                    tempBoard.add(timeTable[idx++]);
                }
            }
            //System.out.println(tempBoard.toString());
            currentTime += t;
            remainRound--;
            
            // 아직 못탔는데 회차가 끝나려고 하면 탈 수 있는 시간 찾기
            int targetIdx = idx - 1;
            if(remainRound == 0 && tempBoard.size() == m){
                //System.out.println(targetIdx);
                // 한명만 제쳐도 될수도 있으니까...
                while(targetIdx > 0){
                    if(timeTable[targetIdx - 1] < timeTable[idx -1])
                        break;
                    targetIdx--;
                }
                // System.out.println(Arrays.toString(timeTable));
                // System.out.println(targetIdx);
                // System.out.println(parseString(timeTable[targetIdx]));
                answer = timeTable[targetIdx] -1;
            }
            
        }        
        
        return parseString(answer);
    }
    
    int parseMinute(String time){
        String[] times = time.split(":");
        
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);
        
        return 60 * hour + minute;
    }
    
    String parseString(int time){
        int hour = time / 60;
        int minute = time % 60;
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(hour < 10 ? 0 : "").append(hour)
            .append(":")
            .append(minute < 10 ? 0 : "").append(minute);
        
        return sb.toString();
    }
}