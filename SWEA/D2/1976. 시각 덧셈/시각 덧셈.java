import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	/**
	 * # 1976 시각 덧셈
	 * 
	 * 	시 분으로 이루어진 시각을 2개 입력받아 
	 * 	더한 값을 시 분으로 출력
	 * 	시각은 12시간제
	 * 	시 : 1 이상 12 이하
	 * 	분 : 0 이상 59 이하
	 * */
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());
		
		// 각 테스트 케이스에 4개의 수
		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			String[] times = br.readLine().trim().split(" ");
			// 분 먼저 계산해서 시로 넘길 값있는지 봐야함
			int minute = Integer.parseInt(times[1]) + Integer.parseInt(times[3]);
			int hour = Integer.parseInt(times[0]) + Integer.parseInt(times[2]);
			hour += minute/60;	// 60분 이상은 시로 넘김
			minute %= 60;		// 60 미만 값으로 변경
			hour = hour%12;	// 12시간제로 변경
			if(hour==0)		//0이면 12시를 의미함
				hour = 12;
			
			sb.append(hour).append(" ").append(minute).append("\n");
		}
		System.out.println(sb);
	}

}
