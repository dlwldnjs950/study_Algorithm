import java.io.*;
import java.util.*;

class Solution {
    // data : 데이타
    // ext : 기준 데이터 (문자열) ("code", "date", "maximum", "remain")
    // val_ext : 기준 값 (정수)
    // sort_by : 정렬 기준 데이터 (문자열) (코드번호, 날짜, 최대수량, 현재수량)
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        
        // 기준 데이터의 기준 값에 해당하는 데이터만 가져와서        
        // 정렬
        
        int standardIdx = standardIdx(ext);
        int sortStandardIdx = standardIdx(sort_by);
        
        
        // 조건에 해당하는 행 걸러내기
        List<int[]> satisfiedList = new ArrayList<>();
        
        for(int idx=0; idx<data.length; idx++){
            if(data[idx][standardIdx] < val_ext)
                satisfiedList.add(data[idx]);
        }
        
        
        // 행 정렬하기
        satisfiedList.sort(new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                return o1[sortStandardIdx] - o2[sortStandardIdx];
            }
        });
        
        int[][] answer = new int[satisfiedList.size()][4];
        
        for(int idx=0; idx<satisfiedList.size(); idx++){
            answer[idx] = satisfiedList.get(idx).clone();
        }
        
        return answer;
    }
    
    public int standardIdx(String standard){
        switch(standard){
            case "code":
                return 0;
            case "date":
                return 1;
            case "maximum":
                return 2;
            case "remain":
                return 3;
            default :
                return -1;
        }
    }
}