import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 4012_요리사
	 * 
	 * 	[설명]
	 * A음식과 B음식 맛의 차이 최소 되도록 재료 분배
	 * 
	 * N개의 식재료
	 * N/2개로 나누어 요리
	 * 음식의 맛은 시너지의 합
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 개수
	 * ===> 재료의 개수
	 * ===> 재료 간 시너지 정보
	 * 
	 * 	[출력]
	 * 맛 차이의 최소값
	 * 
	 * 	[풀이방법]
	 * 재료 선택 => 부분 집합
	 * 선택된 재료 or 미선택된 재료
	 * for문 돌때 한번에 값 더하기
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int ingreKind, ingreSynergy[][], minDiff;
	static boolean selected[];

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 재료 종류
			ingreKind = Integer.parseInt(br.readLine().trim());

			// 재료 시너지 정보
			ingreSynergy = new int[ingreKind][ingreKind];

			for (int rowIdx = 0; rowIdx < ingreKind; rowIdx++) {

				st = new StringTokenizer(br.readLine().trim());
				for (int colIdx = 0; colIdx < ingreKind; colIdx++) {

					ingreSynergy[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				}
			}

			// 재료 선택하기
			selected = new boolean[ingreKind];
			minDiff = Integer.MAX_VALUE;
			powerSet(0);		
			sb.append(minDiff).append("\n");
		}
		System.out.println(sb);
	}

	private static void powerSet(int cnt) {
		
		if(cnt == ingreKind) {
			
			// 각 요리의 맛을 구하고
			int mealA = 0;
			int mealB = 0;
			for (int standardIdx = 0; standardIdx < ingreKind; standardIdx++) {
				for (int idx = 0; idx < ingreKind; idx++) {
					if (selected[standardIdx] && selected[idx])
						mealA += ingreSynergy[standardIdx][idx];
					else if (!selected[standardIdx] && !selected[idx])
						mealB += ingreSynergy[standardIdx][idx];
				}
			}

			minDiff = Math.min(minDiff, Math.abs((mealA - mealB)));
			// 그 맛의 차이 최소값을 찾는다
			return;
		}
		
		selected[cnt] = true;
		powerSet(cnt + 1);
		selected[cnt] = false;
		powerSet(cnt + 1);
		
	}

}
