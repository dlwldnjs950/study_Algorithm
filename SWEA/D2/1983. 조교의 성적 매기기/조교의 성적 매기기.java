import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Solution {

	/**
	 * # 1983 조교의 성적 매기기
	 * 	상대평가
	 * 	10개의 평점 : A+ A0 A- B+ B0 B- C+ C0 C- D0
	 * 	각각의 평점은 같은 비율로 부여
	 * 		N명의 학생이 있을 경우 N/10 명의 학생에게 동일 평점
	 * 	K번째 학생의 학점을 출력
	 * 
	 * 	학점 : 총점으로 줄을 세워서 N/10명씩 자른다
	 * 	총점은 35% 45% 20% 반영
	 * 	학생 번호 - 점수 저장해두고
	 * 	점수를 정렬한 다음에 
	 * 	점수에 해당하는 인덱스 번호...
	 * 
	 * 	N은 항상 10의 배수
	 * 	동일한 총점은 없음
	 * 
	 * 	
	 * */
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] grade = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};
		
		//테스트 케이스 T
		int T = sc.nextInt();
		
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			// 학생수 N, 알고싶은 학생 번호 K (공백 구분)
			int N = sc.nextInt();
			int K = sc.nextInt();

			// 총점 저장 배열
			Double[] totalGrade = new Double[N+1];
			double kNum = 0.0;
			// N 줄의 각 학생에 대한 중간, 기말, 과제 점수
			totalGrade[0]=0.0;
			for(int sIdx = 1; sIdx<=N ; sIdx++) {
				int midExam = sc.nextInt();
				int finalExam = sc.nextInt();
				int workshop = sc.nextInt();
				
				totalGrade[sIdx] = midExam*0.35 + finalExam * 0.45 + workshop * 0.2;
				if(sIdx == K) kNum = totalGrade[sIdx];
			}
			
			// k 번째 학생의 학점이 궁금하다
			Arrays.sort(totalGrade,Collections.reverseOrder());
			
			for(int sIdx = 1; sIdx<=N ; sIdx++) {
				if(kNum == totalGrade[sIdx]) {
					sb.append(grade[sIdx/(N/10)]).append("\n");
					break;
				}
			}
			
		}
		System.out.println(sb);
	}

}
