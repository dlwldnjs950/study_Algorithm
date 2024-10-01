import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		int numCnt = Integer.parseInt(st.nextToken());
		int limitDiff = Integer.parseInt(st.nextToken());
		
		int[] numbers = new int[numCnt];
		
		for(int idx = 0; idx<numCnt; idx++) {
			numbers[idx] = Integer.parseInt(br.readLine().trim());
		}
		
		Arrays.sort(numbers);

		int frontIdx = 0;
		int backIdx = 0;
		
		int minDiff = Integer.MAX_VALUE;
		
		while(backIdx < numCnt && frontIdx < numCnt) {
			int diff = numbers[backIdx] - numbers[frontIdx];
			
			if(diff < limitDiff)
				backIdx++;
			else {
				minDiff = Math.min(minDiff, diff);
				frontIdx++;
			}
		}

		System.out.println(minDiff);

	}

}
