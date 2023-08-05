import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * # 1946 간단한 압축 풀기
	 * 
	 * 	너비 10인 여러줄의 문자열
	 * 	압축된 문서는 알파벳과 알파벳의 연속된 개수로 이루어진 쌍
	 * 	원본 문서를 만드시오
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
			sb.append("#").append(testCase).append("\n");
			
			// 문자열 구성 개수 N
			int N = Integer.parseInt(br.readLine().trim());
			
			// N개의 문자열 구성 입력받기
			int cnt=0;	// 길이 10일 때 줄바꿈하기위한...
			for(int loop=0; loop<N; loop++) {
				st = new StringTokenizer(br.readLine().trim());
				String character = st.nextToken();
				int cNum = Integer.parseInt(st.nextToken());
				for(int sbLoop=0; sbLoop<cNum; sbLoop++) {
					cnt++;
					sb.append(character);
					if(cnt%10==0) {
						sb.append("\n");
					}
				}
			}
			sb.append("\n");

		}
		System.out.println(sb);
	}

}
