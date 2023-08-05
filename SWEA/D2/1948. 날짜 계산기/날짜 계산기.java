import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * # 1948 날짜 계산기
	 * 
	 * '월 일'로 이루어진 날짜 2개를 입력받아
	 * 	두번째 날짜가 첫번쨰 날짜의 며칠쨰 인지 구하여라
	 * 	
	 * 	각 달의 마지막 날짜 : 
	 * 		1/31, 2/28, 3/31, 4/30, 5/31, 6/30, 7/31, 8/31, 9/30, 10/31, 11/30, 12/31
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static Map<Integer, Integer> lastDay = new HashMap<>();

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 각 월의 마지막 날짜 Map 구성하기
		makeLastDayMap();

		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			// 날짜 2개 입력받기
			st = new StringTokenizer(br.readLine().trim());
			int startMonth = Integer.parseInt(st.nextToken());
			int startDay = Integer.parseInt(st.nextToken());
			int endMonth = Integer.parseInt(st.nextToken());
			int endDay = Integer.parseInt(st.nextToken());
			
			// 시작월의 마지막날 - 시작월의 시작 일 + 1 + 사이에 지나가는 월들 + 마지막월의 마지막 일
			int dayCount=0;
			for(int sMonth = startMonth; sMonth<= endMonth; sMonth++) {
				if(sMonth == startMonth) {
					dayCount += lastDay.get(sMonth) - startDay +1;
					continue;
				}
				if(sMonth == endMonth) {
					dayCount += endDay;
					continue;
				}
				dayCount+=lastDay.get(sMonth);
			}
			sb.append(dayCount).append("\n");

		}
		System.out.println(sb);
	}

	private static void makeLastDayMap() {
		String[] lastDays = new String("1/31, 2/28, 3/31, 4/30, 5/31, 6/30, 7/31, 8/31, 9/30, 10/31, 11/30, 12/31").split(", ");
		for(String tmp : lastDays) {
			String[] monthDay = tmp.split("/");
			lastDay.put(Integer.parseInt(monthDay[0]), Integer.parseInt(monthDay[1]));
		}
	}
}
