import java.util.*;
/*
학생 클래스를 만들어서
    번호, 점수, 등수
점수 기준으로 정렬해서 등수를 변경하고
    등수는 1부터 시작해서 
        앞 학생과 점수가 같으면 동일 등수..
다시 번호 기준으로 정렬해서 정답 출력
*/
class Solution {
    
    class Student{
        int number;
        double avg;
        int rank;
        
        Student(int number, int[] score){
            this.number = number;
            this.avg = (double)(score[0] + score[1])/2 ;
            this.rank = 0;
        }
        
        void setRank(int rank){
            this.rank = rank;
        }
    }
    
    public int[] solution(int[][] score) {
        int size = score.length;
        
        Student[] students = new Student[size];
        for(int idx = 0; idx < size; idx++){
            students[idx] = new Student(idx, score[idx]);
        }
        
        Arrays.sort(students, (a, b) -> Double.compare(b.avg, a.avg));
        
        int rank=1;
        students[0].setRank(1);
        for(int idx = 1; idx < size; idx++){
            if(students[idx].avg == students[idx-1].avg)
                students[idx].rank = students[idx-1].rank;
            else
                students[idx].rank = idx + 1;
        }
        
        Arrays.sort(students, (a, b) -> Double.compare(a.number, b.number));
        
        int[] answer = new int[size];
        for(int idx = 0; idx < size; idx++){
            answer[idx] = students[idx].rank;
        }
        return answer;
    }
}