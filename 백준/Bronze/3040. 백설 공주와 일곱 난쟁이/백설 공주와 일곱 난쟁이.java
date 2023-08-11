import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 3040_백설공주와 난쟁이
	 * 
	 * 	[설명]
	 * 일곱 난쟁이가 아홉 난쟁이가 되어 돌아왔다
	 * 일곱 난쟁이의 모자에 적힌 숫자의 합이 100이 된다
	 * 일곱 난쟁이를 찾아라 
	 * (9개의 수 중에 합 100을 만드는 숫자7조합을 찾아라)
	 * 
	 * 	[입력]
	 * (9줄의 입력)
	 * ===> 숫자
	 * 
	 * 	[출력]
	 * 난쟁이의 모자에 적힌 숫자 한 줄에 하나씩 출력
	 * 
	 * 	[풀이방법]
	 * 7개의 조합을 구해도 되지만 
	 * 9개 숫자의 합을 구해두고
	 * 백설공주네 난쟁이가 아닌 2명을 골라서 그 값을 빼줘도 된다.
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int COMBACKPERSON = 9;
	static final int REALPERSON = 7;
	static final int REALSUM = 100;
	static int[] selectedNum;
	static int[] hatNum;
	static int combackSum;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 아홉 난쟁이의 모자 숫자 입력받기
		hatNum = new int[COMBACKPERSON];
		combackSum = 0;
		for (int idx = 0; idx < COMBACKPERSON; idx++) {
			hatNum[idx] = Integer.parseInt(br.readLine().trim());
			combackSum += hatNum[idx];
		}

		selectedNum = new int[COMBACKPERSON - REALPERSON];
		combination(0, 0);

		System.out.println(sb);
	}

	static void combination(int cnt, int start) {
		if (cnt == COMBACKPERSON - REALPERSON) { // 2개를 다 골랐으면
			// 그 둘을 뺀 모자 숫자 합이 100이면 sb에 붙여주기
			if ((combackSum - selectedNum[0] - selectedNum[1]) == 100) {
				for (int h : hatNum) {
					if (h != selectedNum[0] && h != selectedNum[1])
						sb.append(h).append("\n");
				}
			}
			return;
		}
		for (int idx = start; idx < COMBACKPERSON; idx++) {
			selectedNum[cnt] = hatNum[idx];
			combination(cnt + 1, idx + 1);
		}
	}

}
