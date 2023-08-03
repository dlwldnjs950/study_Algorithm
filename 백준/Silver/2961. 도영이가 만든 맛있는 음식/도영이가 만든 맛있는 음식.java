import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/**
	 * # 2961 도영이가 만든 맛있는 음식
	 * 	재료 N개
	 * 
	 * 	각 재료의 
	 * 	신맛 : S, 곱
	 * 	쓴맛 : B, 합
	 * 
	 * 	요리의 신맛과 쓴맛의 차이를 작게
	 * 	재료는 적어도 하나 사용
	 * 
	 * 	신맛과 쓴맛의 차이가 가장 작은 요리의 차이 출력
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	static int N;	// 재료의 개수
	static int[][] taste;	// 각 재료의 맛 수치 저장 배열
	static boolean[] isSelected;	// 재료 선택 여부
	static int diff = Integer.MAX_VALUE;	// 신맛과 쓴맛의 최소 차이를 저장할 변수
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 재료의 개수 N
		N = Integer.parseInt(br.readLine().trim());
		
		// 각 재료의 신/쓴맛 저장 배열
		taste = new int[N][2];
		isSelected = new boolean[N];
		for (int tIdx = 0; tIdx < N; tIdx++) {
			// N개의 줄에 신맛 쓴맛 공백으로 주어짐
			st = new StringTokenizer(br.readLine().trim());
			taste[tIdx][0] = Integer.parseInt(st.nextToken());
			taste[tIdx][1] = Integer.parseInt(st.nextToken());
		}		
		
		// 각 재료로 만들수 있는 요리(부분집합)을 만들어 
		// 각 경우의 쓴/신맛을 계산, 최소값을 찾는다
		generateFood(0, 0, 1, 0);	// 신맛은 곱이기 때문에 1을 넣어줘야한다
		sb.append(diff);
		System.out.println(sb);
	}
	
	// 사용된 재료의 개수, 쓴맛과 신맛의 합이 궁금 (어떤 재료가 쓰였는지는 X)
	private static void generateFood(int cnt, int chosenCnt, int sourSum, int bitterSum) {
		if(cnt==N) {
			if(chosenCnt!=0) {
				if(diff > Math.abs(sourSum - bitterSum)) {
					diff = Math.abs(sourSum - bitterSum);
				}
			}
			return;
		}
		isSelected[cnt] = true;
		generateFood(cnt+1, chosenCnt+1, sourSum * taste[cnt][0], bitterSum + taste[cnt][1]);
		isSelected[cnt] = false;
		generateFood(cnt+1, chosenCnt, sourSum, bitterSum);
	}

}
