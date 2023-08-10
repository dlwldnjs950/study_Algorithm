import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 5215_햄버거 다이어트_부분집합
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
	 * 부분집합을 구해서
	 * 칼로리의 합 이하인지
	 * 이하면 그중에 맛 점수 가장 높은 것! 
	 * 공집합도 만들어지므로 주의!
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
	
	static int N, L, maxYam;
	static boolean[] isSelected;
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
			
			// 각 재료의 선택 여부
			isSelected = new boolean[N];
			
			// 각 재료 정보 저장하기
			ingre = new ingredient[N];
			for(int idx = 0; idx<N; idx++) {
				// 각 재료의 점수와 칼로리
				st = new StringTokenizer(br.readLine());
				ingre[idx] = new ingredient(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			maxYam = 0;
			// 재료 선택
			subset(0);
			sb.append(maxYam).append("\n");
		}
		System.out.println(sb);
	}

	// 부분 집합은 자리의 선택
	static void subset(int cnt) {
		int totalCal = 0;
		int totalYam = 0;
		if(cnt == N) {
			// 여기서 이제 선택된 재료들의 합을 구하자
			for(int idx = 0; idx<N; idx++) {
				if(isSelected[idx]) {
					totalCal+= ingre[idx].calorie;
					totalYam+= ingre[idx].yammy;
				}
			}

			// 제한 칼로리 이하면 맛점수 확인
            // 공집합은 제외
			if (totalCal <= L && totalCal != 0) {
				if (maxYam < totalYam)
					maxYam = totalYam;
			}
			return;
		}
		isSelected[cnt] = true;
		subset(cnt+1);
		isSelected[cnt] = false;
		subset(cnt+1);

	}
}
