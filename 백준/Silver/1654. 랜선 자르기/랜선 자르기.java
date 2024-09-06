import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Some {
	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		int lanNum = Integer.parseInt(st.nextToken());
		int targetNum = Integer.parseInt(st.nextToken());
		
		long lans[] = new long[lanNum];
		long min = 1;
		long max = 1;
		for (int idx = 0; idx < lanNum; idx++) {
			lans[idx] = Long.parseLong(br.readLine().trim());
			max = Math.max(max, lans[idx]);
		}
		
		while(min <= max) {
			long mid = (min + max) /2;
			
			int cnt = 0;
			for(long lan : lans) {
				cnt += (lan / mid);
				
				if(cnt > targetNum)
					break;
			}
			
			// 목표치보다 적게 만들어지면 더 짧게 만들어야지
			if(cnt < targetNum)
				max = mid - 1;
			else
				min = mid + 1;
		}

		System.out.println(max);

	}

}
