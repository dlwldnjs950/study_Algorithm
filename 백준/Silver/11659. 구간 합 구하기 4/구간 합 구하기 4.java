import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/**
	 * # 구간 합 구하기
	 * 주어지는 수 N개, i번째 부터 j번째 수까지 합을 구하시오
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		// 전역변수 객체 초기화
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 수의 개수 N, 구해야하는 횟수 M
		String[] NM = br.readLine().trim().split(" ");
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);
		
		// N개의 수가 주어진다(공백 구분)
		st = new StringTokenizer(br.readLine().trim());
		int[] num = new int[N+1];	// i,j가 1부터 시작되는 값이므로 그대로 인덱스 값으로 사용하기 위해 +1 (값을 넣고 볼때 1부터 시작한다)
		for (int numIdx = 1; numIdx <= N; numIdx++) {
			// 누적합을 저장한다
			num[numIdx] = Integer.parseInt(st.nextToken()) + num[numIdx - 1];
		}
		// 합을 구하는 구간 i, j가 주어진다 (M 줄)
		for(int loop = 0; loop < M; loop++) {
			String[] ij = br.readLine().trim().split(" ");
			int i = Integer.parseInt(ij[0]);
			int j = Integer.parseInt(ij[1]);
			// 반복문으로 합계를 구하면 시간 초과
			// 배열에 저장할 때 누적합을 저장하여 구간합을  두 누적합에 대한 마이너스 연산으로 구할 수 있도록 한다.
			sb.append(num[j] - num[i - 1]).append("\n");
		}
		System.out.println(sb);
	}

}
