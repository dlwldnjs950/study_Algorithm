import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 15649_N과M
	 * 
	 * 	[설명]
	 * 자연수 N과 M이 주어졌을 때,
	 * 	1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
	 * 을 만족하는 길이 M인 수열을 모두 구하여라
	 * 
	 * 	[입력]
	 * ===> 자연수 범위 N과 숫자 고를 개수 M이 주어진다
	 * 
	 * 	[출력]
	 * 사전 순으로 증가하는 순서로 출력한다
	 * 
	 * 	[풀이방법]
	 * 수열 구하는 알고리즘으로 푼다
	 * 	# 수열을 구하는 함수
	 * 매개 변수 : 현재까지 선택한 숫자 개수
	 * 전역 변수 : 현재 선택된 숫자 저장 배열, 해당 숫자가 선택되었는지 표시할 배열
	 * 기저 조건 : 선택할 개수 M만큼 선택했으면 수열 완성
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int limitNum, howMany;
	static int numbers[];
	static boolean selected[];
	
	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 선택할 숫자 범위 N과 숫자 고를 개수 M
		st = new StringTokenizer(br.readLine().trim());
		limitNum = Integer.parseInt(st.nextToken());
		howMany = Integer.parseInt(st.nextToken());
		
		numbers = new int[howMany];
		// 숫자가 1부터 시작하므로 +1
		selected = new boolean[limitNum+1];
		perm(0);
		
		System.out.println(sb);
	}

	private static void perm(int cnt) {	// 현재까지 고른 숫자 개수(선택된 숫자 배열의 인덱스값)
		
		if (cnt == howMany) {
			for(int idx=0; idx<howMany; idx++) {
				sb.append(numbers[idx]).append(" ");
			}
			sb.append("\n");
		} else { // 해당 숫자 선택을 해제하고 다른 숫자를 선택하는 경우를 살펴봐야해서 else

			for (int number = 1; number <= limitNum; number++) {
				// 이미 선택된 숫자라면 넘어가기
				if (selected[number])
					continue;
				// 선택된 숫자 배열에 값을 넣어주고
				numbers[cnt] = number;
				// 선택 표시
				selected[number] = true;
				perm(cnt + 1);
				// 재귀 돌고 와서 다시 선택 풀어주기
				selected[number] = false;
			}
		}
	}

}
