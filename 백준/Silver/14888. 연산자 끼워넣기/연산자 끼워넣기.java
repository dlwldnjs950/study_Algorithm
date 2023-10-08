import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/**
	 * 
	 */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int maxResult, minResult;
	static int numbers[], numberNum, operCnt[];

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		numberNum = Integer.parseInt(br.readLine().trim());

		// 숫자들
		numbers = new int[numberNum];

		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < numberNum; idx++) {
			numbers[idx] = Integer.parseInt(st.nextToken());
		}

		// 각 연산자 개수 ( + - x / )
		operCnt = new int[4];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < 4; idx++) {
			operCnt[idx] = Integer.parseInt(st.nextToken());
		}
		
		maxResult = Integer.MIN_VALUE;
		minResult = Integer.MAX_VALUE;
		calculate(0,numbers[0]);
		
		sb.append(maxResult).append("\n").append(minResult);
		
		System.out.println(sb);
	}

	private static void calculate(int depth, int midResult) {

		if (depth == numberNum - 1) {
			//System.out.println(midResult);
			maxResult = Math.max(maxResult, midResult);
			minResult = Math.min(minResult, midResult);
			return;
		}

		// 각 연산자 중복 순열
		for (int oper = 0; oper < 4; oper++) {
			if (operCnt[oper] == 0)
				continue;
			operCnt[oper]--;
			calculate(depth + 1, oper(midResult, oper, numbers[depth+1]));
			operCnt[oper]++;
		}

	}

	private static int oper(int midResult, int oper, int number) {
		switch (oper) {
		case 0:
			midResult +=number;
			break;
		case 1:
			midResult -=number;
			break;
		case 2:
			midResult *=number;
			break;
		case 3:
			midResult /=number;
			break;
		}
		return midResult;
	}
}
