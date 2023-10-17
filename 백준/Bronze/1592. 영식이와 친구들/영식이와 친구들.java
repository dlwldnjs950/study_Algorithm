import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 
	 * 
	 * 	[설명]
	 * 공 받는 시작은 1번
	 * 받고 던지고 받고 던지고 반복
	 * 한 사람이 공을 M번 받았으면 게임은 끝난다
	 * 
	 * 던지는 대상 : 
	 * 		공받은 횟수 홀수 => 현재 위치에서 시계방향으로 L번째 있는 사람에게
	 * 				 짝수 => 			반시계방향으로
	 * 
	 * 공은 총 몇번 던지는가?
	 * 
	 * 	[입력]
	 * ===> 사람수 기준횟수 순번
	 * 
	 * 	[출력]
	 * 공을 던진 횟수
	 * 
	 * 	[풀이방법]
	 * 각 순번의 사람이 받은 횟수를 저장할 변수 : 배열
	 * 공 받을 사람 인덱스 계산
	 * 		인덱스 번호 : 0 ~ N-1
	 * 		반시계 : 해당 인덱스 - 순번 + 사람수
	 * 		 시계 : 해당 인덱스 + 순번 % 사람수  
	 * 
	 * 			0 1 2 3 4
	 * 			5명 0번 2번째 = 3 = 0 - 2 + 5
	 * 					   = 2 = (0 + 2) % 5
	 * 			5명 3번 3번째 = 1 = (3+3) % 5
	 * 			5명 2번 4번째 = 3 = 2 - 4 + 5
	 * 			5명 2번 1번째 = 2 - 1 + 5
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int personNum, limitCnt, order, eachCnt[];

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// N
		st = new StringTokenizer(br.readLine().trim());

		personNum = Integer.parseInt(st.nextToken());
		limitCnt = Integer.parseInt(st.nextToken());
		order = Integer.parseInt(st.nextToken());

		eachCnt = new int[personNum];
		sb.append(cntThrowball(0));
		
		System.out.println(sb);
	}

	private static int cntThrowball(int idx) {

		int cnt = 0;

		while (true) {

			// 1번 사람이 받으면서 시작
			eachCnt[idx]++;
			
			// 해당 사람이 제한 횟수와 같으면 그만
			if (eachCnt[idx] == limitCnt)
				break;

			// 다음 받을 사람 계산
			
			// 홀수
			if ((eachCnt[idx] & 1) == 1) {
				idx = (idx - order + personNum) % personNum;
			} else {
				idx = (idx + order) % personNum;
			}
			
			cnt++;

		}

		return cnt;
	}

}
