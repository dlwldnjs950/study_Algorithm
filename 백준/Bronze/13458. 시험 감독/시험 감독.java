import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/**
	 * N개의 시험장
	 * 각 시험장에 Ai명
	 * 
	 * 총감독관 한 시험장에서 B명 감시 가능
	 * 부감독관 한 시험장에서 C명 감시
	 * 
	 * 총감독관은 1명, 부감독관은 여러명 가능
	 * 
	 * 각 시험장 마다 응시생을 모두 감독하기 위해 필요한 감독관의 최소 수?
	 * 
	 * 총감독관수 : 1명 고정
	 * 부감독관수 : 그 나머지 부감독관으로
	 */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int classNum = Integer.parseInt(br.readLine().trim());

		int[] classCnt = new int[classNum];

		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < classNum; idx++) {
			classCnt[idx] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine().trim());
		int mainLimit = Integer.parseInt(st.nextToken());
		int subLimit = Integer.parseInt(st.nextToken());
		
		long result = 0;
		for(int tmp : classCnt) {
			result++;
			tmp -=mainLimit;
			if(tmp<0)
				tmp = 0;
			result += Math.ceil(tmp/(float) subLimit);
		}
		System.out.println(result);
	}
}
