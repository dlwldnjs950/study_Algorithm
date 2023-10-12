import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int A = 0, B = 1, NONE = 2;
	static int height, width, kijune, map[][], minDropCnt, drops[];

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine().trim());
		
		for(int testCase=1; testCase<=tc; testCase++) {
			
			// 두께, 가로크기, 합격기준
			st = new StringTokenizer(br.readLine().trim());
			
			height = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());
			kijune = Integer.parseInt(st.nextToken());
			
			// A : 0, B : 1
			map = new int[height][width];
			
			for(int rowIdx=0; rowIdx<height; rowIdx++) {
				
				st = new StringTokenizer(br.readLine().trim());
				
				for(int colIdx=0; colIdx<width; colIdx++) {
					map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 약품 뿌린 현황 표시 배열
			drops = new int[height];
			Arrays.fill(drops, NONE);
			
			minDropCnt = Integer.MAX_VALUE;
			drop(0,0);
			
			sb.append("#").append(testCase).append(" ").append(minDropCnt).append("\n");
		}
		System.out.println(sb);
	}

	// 지금 확인할 높이, 지금까지 약품 떨어트린 횟수
	private static void drop(int heightIdx, int dropCnt) {

		// 중간 횟수가 최소 횟수보다 작으면 더 확인하지 않는다
		if(minDropCnt <= dropCnt)
			return;
		
		// 마지막 높이까지 확인했으면
		if(heightIdx == height) {
			
			// 성능 통과 여부 확인
			if(isOk()) {
				//System.out.println(Arrays.toString(drops));
				minDropCnt = Math.min(minDropCnt, dropCnt);
			}
			return;
		}

		drops[heightIdx] = NONE;
		drop(heightIdx + 1, dropCnt);
		drops[heightIdx] = A;
		drop(heightIdx + 1, dropCnt + 1);
		drops[heightIdx] = B;
		drop(heightIdx + 1, dropCnt + 1);
	}

	private static boolean isOk() {

		// 너비만큼 확인
		for (int widthIdx = 0; widthIdx < width; widthIdx++) {
			int cnt = 1;

			for (int heightIdx = 0; heightIdx < height - 1; heightIdx++) {

				int a = map[heightIdx][widthIdx];
				int b = map[heightIdx + 1][widthIdx];
				
				// 약품을 뿌린 높이면 변경
				if(drops[heightIdx] != NONE) {
					a = drops[heightIdx];
				}

				// 약품을 뿌린 높이면 변경
				if(drops[heightIdx + 1] != NONE) {
					b = drops[heightIdx + 1];
				}
				
				if(a == b) {
					cnt++;
					// 이미 기준 만족했으니까 더 볼 필요없다
					// 밑에서 충족 안하면 표시 바뀜
					if(cnt >= kijune)
						break;
				}else
					cnt = 1;
			}
			
			// 해당 열이 기준을 만족하지 못했다면 그만 확인
			if(cnt < kijune)
				return false;
		}
		
		return true;
	}

}
