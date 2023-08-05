import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * # 1966 숫자를 정렬하자
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			// 숫자의 개수 N
			Integer.parseInt(br.readLine().trim());
			// N개의 숫자 저장
			List<Integer> numbers = new ArrayList<>();
			st = new StringTokenizer(br.readLine().trim());
			while(st.hasMoreTokens()) {
				numbers.add(Integer.parseInt(st.nextToken()));
			}
			numbers.sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return o1 - o2;
				}
			});
			for(int n : numbers) {
				sb.append(n).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
