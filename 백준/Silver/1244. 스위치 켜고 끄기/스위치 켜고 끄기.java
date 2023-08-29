import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 1244_스위치켜고끄기
	 * 
	 * 	[설명]
	 * 스위치의 번호는 1번부터 시작
	 * N개의 스위치
	 * 스위치는 켜져있거나(1) 꺼져있다(0)
	 * 
	 * 	# 남학생(1) : 스위치 번호가 자기가 받은 수 배수 번호 스위치 모두 상태 바꿈
	 * 	# 여학생(2) : 자기가 받은 수 중심으로 좌우 대칭 구간 모두 상태 바꿈
	 * 
	 * 	[입력]
	 * ===> 스위치 개수
	 * ===> 스위치 상태
	 * ===> 학생 수
	 * ===> 각 학생의 성별과 번호
	 * 
	 * 	[출력]
	 * 한 줄에 20개 씩 출력한다
	 * 
	 * 	[풀이방법]
	 * 1. 스위치 상태를 변경해야하므로 배열에 저장
	 * 		배열 아니구 비트 마스킹...도 되나?
	 * 	1-1. 스위치 상태는 ^1연산으로 변경
	 * 2. 남학생인 경우, 인덱스 번호를 자신의 번호로 시작;스위치 개수보다 작을동안;배수로 커짐
	 * 	   여학생인 경우, 좌우로 살펴볼 인덱스; 왼쪽이 0보다 크고, 오른쪽은 스위치 개수보다 작을동안; 1씩 커짐
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int switchCnt, status[];
	static final int ENTERCNT=20;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 스위치 개수
		switchCnt = Integer.parseInt(br.readLine().trim());
		// 스위치 상태
		// 스위치 번호는 1부터 시작
		status = new int[switchCnt + 1];
		st = new StringTokenizer(br.readLine().trim());
		for(int sIdx = 1; sIdx<=switchCnt; sIdx++) {
			status[sIdx] = Integer.parseInt(st.nextToken());
		}
		
		// 학생 수
		int studentCnt = Integer.parseInt(br.readLine().trim());
		for (int sIdx = 0; sIdx < studentCnt; sIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			int gender = Integer.parseInt(st.nextToken());
			int sNumber = Integer.parseInt(st.nextToken());
			if (gender == 1) {// 남학생이면 boy 호출
				boy(sNumber);
			} else if (gender == 2) {// 여학생이면 girl 호출
				girl(sNumber);
			}
			//System.out.println(Arrays.toString(status));
		}
		for(int idx=1; idx<=switchCnt; idx++) {
			sb.append(status[idx]).append(" ");
			// 한 줄에 20개 씩 출력해야한다
			if(idx%ENTERCNT==0)
				sb.append("\n");
		}
		System.out.println(sb);
	}

	// 인덱스 번호를 자신의 번호로 시작;스위치 개수보다 작을동안;배수로 커짐
	static void boy(int number) {
		for (int idx = number; idx <= switchCnt; idx += number) {
			status[idx] ^= 1;
		}
	}

	// 좌우로 살펴볼 인덱스; 왼쪽이 0보다 크고, 오른쪽은 스위치 개수보다 작을동안; 1씩 커짐
	static void girl(int number) {
		status[number] ^= 1;
		for (int idx = 1; number - idx > 0 && number + idx <= switchCnt; idx++) {
			// 양쪽이 서로 같은 경우에 스위치 상태 변경
			if (status[number - idx] == status[number + idx]) {
				status[number - idx] ^= 1;
				status[number + idx] ^= 1;
			}else {
				// 살펴보다가 양쪽이 서로 달라지면 그만 살펴본다
				break;
			}
		}
	}

}
