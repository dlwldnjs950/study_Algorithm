import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N, maxResult;
	static int[] numbers;
	static char[] opers;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// N
		N = Integer.parseInt(br.readLine().trim());

		numbers = new int[N/2 +1];
		opers = new char[N/2];
		
		String str = br.readLine().trim();
		for(int idx =0; idx <N; idx++) {
			// 홀수면 연산자
			if((idx & 1) == 1)
				opers[idx/2] = str.charAt(idx);
			else
				numbers[idx/2] = str.charAt(idx) - '0';
		}
		
		maxResult = Integer.MIN_VALUE;
		selectOper(0,numbers[0]);
		sb.append(maxResult);
		
		System.out.println(sb);
	}

	// 앞에서부터 해당 연산자를 괄호로 묶어 연산할지 안할지
	// 현재 확인할 연산자 인덱스, 지금까지 연산 결과
	private static void selectOper(int idx, int result) {
		
		// 연산자 전부 확인 했으면 결과값 갱신하기
		if(idx >= N/2) {
			maxResult = Math.max(result, maxResult);
			return;
		}

		// 괄호 안묶기
		// 지금까지 결과와 연산자 그 다음 숫자
		int tmpResult = calculate(result, opers[idx], numbers[idx + 1]);
		selectOper(idx + 1, tmpResult);

		// 괄호로 묶을 연산자가 더 없으면 끝내기
		if (idx + 1 >= N / 2) {
			return;
		}

		// 괄호 묶기
		int bracketresult = calculate(numbers[idx + 1], opers[idx + 1], numbers[idx + 2]);
		tmpResult = calculate(result, opers[idx], bracketresult);
		selectOper(idx + 2, tmpResult);
	}

	private static int calculate(int num1, char operation, int num2) {
		int tmp = 0;
		
		switch (operation) {
		case '+':
			tmp = num1 + num2;
			break;
		case '-':
			tmp = num1 - num2;
			break;
		case '*':
			tmp = num1 * num2;
			break;
		}
		
		return tmp;
	}
}
