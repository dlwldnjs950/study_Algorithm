import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 2115_벌꿀 채취
	 * 
	 * 	[설명]
	 * 두명의 일꾼
	 * 각자 M개의 벌통 채취 가능
	 * 각자 채취 가능 꿀 최대 양 C
	 * 각 꿀의 수익은 꿀 양의 제곱
	 * 
	 * 	[입력]
	 * ===> 테스트케이스 개수
	 * ===> 벌통크기 채집가능벌통개수 채집가능꿀양
	 * ===> 벌통 정보
	 * 
	 * 	[출력]
	 * 최대 수익
	 * 
	 * 	[풀이방법]
	 * 1번 인부 각 벌통 수집할말
	 * 2번 인부 1번 인부 피해서 벌통 수집 할말
	 * 수집한 꿀 양이 C를 넘으면 안됨
	 * 
	 * 수집할 벌통 결정
	 * 그 벌통묶음으로 최대 수익
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int mapSize, limitNum, limitAmount, map[][], maxIncome, tmpIncome[];
	static int startIdx[];
	static boolean selected[][];

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 벌통크기 채집가능벌통개수 채집가능꿀양
			st = new StringTokenizer(br.readLine().trim());
			mapSize = Integer.parseInt(st.nextToken());
			limitNum = Integer.parseInt(st.nextToken());
			limitAmount = Integer.parseInt(st.nextToken());
			
			// 벌통 정보
			map = new int[mapSize][mapSize];
			selected = new boolean[mapSize][mapSize];
			for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int colIdx = 0; colIdx < mapSize; colIdx++) {
					map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 두 일꾼의 벌통 정하기
			// 좌표가 아닌 일렬로 해두고 idx 값으로 생각
			// 벌통 시작 idx
			startIdx = new int[2];
			maxIncome = Integer.MIN_VALUE;
			selectStart(0,0);

			sb.append(maxIncome).append("\n");
		}
		System.out.println(sb);
	}

	// 선택한 개수, 선택 시작 인덱스
	private static void selectStart(int cnt, int start) {

		// 시작 인덱스 선택 완료
		if(cnt == 2) {
			
			// 시작 인덱스 기준으로 벌꿀 채집하기
			tmpIncome = new int[2];
			// 일꾼 1
			collectHoney(0, startIdx[0], 0, 0);
			// 일꾼 2
			collectHoney(1, startIdx[1], 0, 0);
			
			maxIncome = Math.max(maxIncome, tmpIncome[0] + tmpIncome[1]);
			
			return;
		}
		
		if(start > mapSize*mapSize - limitNum)
			return;
		
		if(start % mapSize <= mapSize - limitNum) {
			startIdx[cnt] = start;
			selectStart(cnt + 1, start + limitNum);
		}
		selectStart(cnt, start + 1);
	}

	// 확인할 벌통, 채집한 꿀의 양, 현재까지 수익
	private static void collectHoney(int num, int idx, int sumHoney, int sumIncome) {
		if(sumHoney > limitAmount)
			return;
		
		if(idx - startIdx[num] == limitNum) {
			tmpIncome[num] = Math.max(tmpIncome[num], sumIncome);
			return;
		}

		int row = idx / mapSize;
		int col = idx % mapSize;
		
		collectHoney(num, idx + 1, sumHoney + map[row][col], sumIncome + map[row][col]*map[row][col]);
		collectHoney(num, idx + 1, sumHoney, sumIncome);
	}

	

}
