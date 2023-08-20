import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 1206_View
	 * 
	 * 	[설명]
	 * 왼쪽과 오른쪽 모두 거리 2 이상의 공간이 확보될 때,
	 * 조망권이 확보된다고 말한다
	 * 
	 * 빌딩들에 대한 정보가 주어질 때, 조망권이 확보된 세대의 수를 반환해라
	 * 
	 * 맨왼쪽 2칸과 맨오른쪽 2칸은 건물이 지어지지 않는다
	 * 빌딩의 최대 높이는 255
	 * 
	 * 	[입력]
	 * (10개의 테스트 케이스)
	 * ===> 건물의 개수 N
	 * ===> N개의 건물 높이
	 * 
	 * 	[출력]
	 * 조망권 확보된 세대 수
	 * 
	 * 	[풀이방법]
	 * 건물의 높이 정보대로 2차원 배열을 구성해도 되고..
	 * 왼쪽2, 오른쪽2 값의 최대값을 찾아서
	 * 자기자신보다 작으면 자기자신 - 찾은 max값
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = 10;

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 건물의 개수
			int N = Integer.parseInt(br.readLine().trim());

			// 건물의 높이 입력받기
			String[] building = br.readLine().trim().split(" ");

			int viewCnt = 0;
			// 맨 왼쪽 2칸은 건물이 없으므로 3번째 건물부터 확인
			for (int idx = 2; idx < N-2; idx++) {
				// 왼쪽 2칸, 오른쪽 2칸 중에 최대 높이 찾기
				int tmpMax = Math.max(
						Math.max(Integer.parseInt(building[idx - 2]), Integer.parseInt(building[idx - 1])),
						Math.max(Integer.parseInt(building[idx + 2]), Integer.parseInt(building[idx + 1])));
				// 그 건물이 자기 자신보다 작았다면
				if(tmpMax<Integer.parseInt(building[idx]))
					viewCnt+=Integer.parseInt(building[idx]) - tmpMax;
			}
			sb.append(viewCnt).append("\n");
		}
		System.out.println(sb);
	}

}
