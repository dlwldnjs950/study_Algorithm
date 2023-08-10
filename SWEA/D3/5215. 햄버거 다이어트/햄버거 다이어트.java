import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 5215_햄버거 다이어트_조합
	 * 
	 * 	[설명]
	 * 햄버거의 맛은 유지하면서 정해진 칼로리 넘지 않는 햄버거 주문하기
	 * 재료를 잘라주지는 않는다
	 * 재료마다 맛에 대한 점수
	 * 
	 * 햄버거 재료에 대한 점수와 칼로리가 주어짐
	 * 선호도 : 재료들의 맛의 점수 합 (여러번 사용 높)
	 * 칼로리 : 재료들의 칼로리 합
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 수 T
	 * (각 테스트 케이스)
	 * ===> 재료의 수 N 과 제한 칼로리 L (공백 구분)
	 * (N 줄의 입력)
	 * ===> 각 재료의 점수와 칼로리 (공백 구분)
	 * 
	 * 	[출력]
	 * 칼로리 이하의 조합 중에서 맛에 대한 점수가 가장 높은 것 출력
	 * 
	 * 	[풀이방법]
	 * 인덱스 번호를 고르자
	 * 조합으로 풀면
	 * 선택하는 개수에 따라 각각 조합을 구하고
	 * 칼로리의 합 이하인지
	 * 맛 점수는 이상인지 확인
	 * 
	 * */
	
	static class ingredient{
		int yammy;
		int calorie;
		
		public ingredient() {}
		
		public ingredient(int y, int c) {
			this.yammy = y;
			this.calorie = c;
		}
	}
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N, L, selectedIngreIdx[], maxYam;
	static ingredient[] ingre;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 재료의 수 N 과 제한 칼로리 L
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			// 각 재료 정보 저장하기
			ingre = new ingredient[N];
			for(int idx = 0; idx<N; idx++) {
				// 각 재료의 점수와 칼로리
				st = new StringTokenizer(br.readLine());
				ingre[idx] = new ingredient(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			maxYam = 0;
			// 재료 1개 ~ N개 선택
			// 부분집합 코드를 활용하거나 조합의 코드를 사용하거나
			for (int howMany = 1; howMany <= N; howMany++) {
				// 선택한 재료 인덱스 담을 배열
				selectedIngreIdx = new int[howMany];
				// 재료 선택하기
				combination(0, 0, howMany);
			}
			sb.append(maxYam).append("\n");
		}
		System.out.println(sb);
	}
	
	// 조합의 코드를 적어보자
	
	static void combination(int cnt, int start, int howMany) {
		if(cnt == howMany) {
			// 재료 다 선택했으면
			// 선택한 재료의 조건 만족 여부 확인
			int totalCal = 0;
			int totalYam = 0;
			for(int idx=0; idx<howMany; idx++) {
				totalCal += ingre[selectedIngreIdx[idx]].calorie;
				totalYam += ingre[selectedIngreIdx[idx]].yammy;
			}
			
			// 제한 칼로리 이하면 맛점수 확인
			if (totalCal <= L) {
				if (maxYam < totalYam)
					maxYam = totalYam;
			}
			return;
		}
		for(int idx = start; idx<N; idx++) {
			selectedIngreIdx[cnt] = idx;
			combination(cnt+1, idx+1, howMany);
		}
	}

}
