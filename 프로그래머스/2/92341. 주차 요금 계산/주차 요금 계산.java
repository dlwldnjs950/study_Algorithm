import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<Integer, Integer> cars = new HashMap<>();
        List<Integer> carNum = new ArrayList<>();
        for(String record : records){
            String[] re = record.split(" ");
            int car = Integer.parseInt(re[1]);
            int time = timeToMinute(re[0]);
            if(cars.containsKey(car)){
                // 입출차 구분
                int defaultTime = cars.get(car);
                if("OUT".equals(re[2])){
                    cars.put(car, defaultTime - time);
                }else if("IN".equals(re[2])){
                    cars.put(car, defaultTime + time);                    
                }
            }else{
                carNum.add(car);
                cars.put(car,time);
            }
        }
        int[] answer = new int[carNum.size()];
        Collections.sort(carNum);
        int idx=0;
        for(int num:carNum){
            answer[idx++]=calculateFee(cars.get(num), fees);
        }
        return answer;
    }
    
    public int calculateFee(int times, int[] fees){
        int minute = 23*60+59;
        if(times >= 0){
            times -= minute;
        }
        
        minute = -1 * times;
        System.out.println(minute);
        
        if(minute<=fees[0])
            return fees[1];
        else{
            return fees[1] + (int)Math.ceil((float)(minute-fees[0])/fees[2])*fees[3];
        }
    }
    
    public int timeToMinute(String time){
        String[] times = time.split(":");
        
        return Integer.parseInt(times[0])*60 + Integer.parseInt(times[1]);
    }
}