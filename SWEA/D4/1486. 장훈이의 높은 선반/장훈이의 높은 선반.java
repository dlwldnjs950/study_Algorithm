import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 1486_장훈이의 높은 선반
	 * 
	 * 	[설명]
	 * 높이 B의 선반에 올라가기 위한
	 * 점원 N명 (각 점원의 키 H)으로 쌓은 탑
	 * 높이 B이상인 탑 중에서 높이가 가장 낮은 탑?
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스
	 * ===> 점원수 선반높이
	 * ===> 점원들의 키
	 * 
	 * 	[출력]
	 * 선반과 최소탑의 높이 차
	 * 
	 * 	[풀이방법]
	 * 재귀
	 * 해당 점원 선택 하고 안하고
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int employeeNum, shelfHeight, employeeHeigth[], minHeight;
	static boolean selected[];

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 점원 수와 선반 높이
			st = new StringTokenizer(br.readLine().trim());
			employeeNum = Integer.parseInt(st.nextToken());
			shelfHeight = Integer.parseInt(st.nextToken());
			
			// 점원 키
			st = new StringTokenizer(br.readLine().trim());
			employeeHeigth = new int[employeeNum];
			for(int idx = 0; idx<employeeNum; idx++) {
				employeeHeigth[idx] = Integer.parseInt(st.nextToken());
			}
			
			// 작은 키 부터 합쳐나가기 위한 정렬
			Arrays.sort(employeeHeigth);
			
			// 키 합 구해보기
			selected = new boolean[employeeNum];
			minHeight = Integer.MAX_VALUE;
			makeTower(0, 0);
			
			sb.append(Math.abs(shelfHeight - minHeight)).append("\n");

		}
		System.out.println(sb);
	}

	private static void makeTower(int cnt, int tmpSum) {

		// 점원탑 높이가 선반 이상이 되면 비교
		if(tmpSum >= shelfHeight) {
			minHeight = Math.min(minHeight, tmpSum);
		}
		
		// 점원 수 까지만 확인
		if(cnt == employeeNum)
			return;
				
		// 해당 점원 선택
		//selected[cnt] = true;
		makeTower(cnt +1, tmpSum + employeeHeigth[cnt]);
		
		// 해당 점원 비선택
		//selected[cnt] = false;
		makeTower(cnt +1, tmpSum);
	}

}
