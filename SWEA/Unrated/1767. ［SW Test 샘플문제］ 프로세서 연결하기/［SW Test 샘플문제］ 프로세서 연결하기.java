import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 1767_프로세서 연결하기
	 * 
	 * 	[설명]
	 * 최대한 많은 코어에 전원을 연결했을 경우, 전선 길이의 합 구하기
	 * (여러가지 방법이 있을 경우 전선 길이 합 최소)
	 * 	[입력]
	 * ===> 테스트 케이스 수
	 * ===> 멕시노드 크기 N
	 * ===> 멕시노드 정보
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * 멕시 노드 정보 입력 받으며 전선 연결해야할 코어 리스트 구성하기
	 * 각 리스트에 대해 전선을 연결해보고 아니면 다른 방향의 전선 연결해보기
	 * 
	 * 프로세서를 다 연결했을 때, 최소 전선 길이 비교
	 * 해당 전선 연결이 최소가 아니거나, 연결될 수 없는 경우는 넘어가
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static class Point{
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}		
	}
	
	static int N, map[][], minlineSum, maxCoreCnt;
	static List<Point> coreList;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			N = Integer.parseInt(br.readLine().trim());
			
			// 멕시 노드 정보 입력받기
			map = new int[N][N];
			// 전원 연결할 코어 리스트
			coreList = new ArrayList<>();
			for(int rIdx = 0; rIdx<N; rIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int cIdx = 0; cIdx<N; cIdx++) {
					map[rIdx][cIdx] = Integer.parseInt(st.nextToken());
					// 1이면 코어가 있다는 뜻
					if(map[rIdx][cIdx] == 1) {
						// 가장자리에 위치해 이미 전원에 연결된 코어는 제외
						if (rIdx == 0 || cIdx == 0 || rIdx == N - 1 || cIdx == N - 1)
							continue;
						coreList.add(new Point(rIdx,cIdx));
					}
				}
			}

			// 각 코어에 대해 전선을 연결해본다
			minlineSum = Integer.MAX_VALUE;
			maxCoreCnt = Integer.MIN_VALUE;
			connectPower(0, 0, 0);
			
			sb.append(minlineSum).append("\n");
			
		}// 테케 반복 끝		
		System.out.println(sb);
	}

	static int[] dr = { 0, 0, -1, 1 }, dc = { 1, -1, 0, 0 };

	// 최대한 많은 코어니까 연결된 코어 개수도 넘겨줘야한다
	public static void connectPower(int coreIdx, int lineSum, int coreSum) {

		// 최대 개수가 바꼈을 때, 전선 길이는 최소
		// 코어 리스트 전체를 살펴봤을 때, 길이 비교
		if (coreIdx == coreList.size()) {
			// 최대 코어 연결 개수 갱신
			if(coreSum > maxCoreCnt) {
//				System.out.println("누적 전선 길이 : " + lineSum);
//				System.out.println("연결 코어 : " + coreSum);
//				System.out.println("현재 최저 길이 : " + minlineSum);
//				for (int[] m : map) {
//					System.out.println(Arrays.toString(m));
//				}
				// 최대 코어 개수 변경
				maxCoreCnt = coreSum;
				minlineSum = lineSum;
			}else if(coreSum == maxCoreCnt) {
				minlineSum = Math.min(minlineSum, lineSum);
			}
			
			return;
		}
		for(int dIdx = 0; dIdx<dr.length; dIdx++) {
			// 각 코어에 대해 전선 확인하기
			//System.out.println(coreList.get(coreIdx));
			int nr = coreList.get(coreIdx).r;
			int nc = coreList.get(coreIdx).c;
			int cnt = 0; // 전선의 길이
			while (true) {
				nr += dr[dIdx];
				nc += dc[dIdx];
				
				// 범위를 넘어가면 끝내기
				if(nr<0 || nc<0 || nr>=N || nc>=N) {
					break;
				}
				// 다른 코어가 있으면 안됨
				if(map[nr][nc] == 1) {
					// 전선을 연결할 수 없는 경우이므로 cnt를 0으로 초기화
					cnt=0;
					break;
				}
				
				// 이동할 수 있으면 전선 길이 ++
				cnt++;
			}
			//System.out.println(coreIdx +"의 전선 길이 : "+cnt);
			// 전선 그리기
			for(int loop = 1; loop<=cnt; loop++) {
				int lineR = coreList.get(coreIdx).r + dr[dIdx]*loop;
				int lineC = coreList.get(coreIdx).c + dc[dIdx]*loop;
				map[lineR][lineC] = 1;
			}
			// 그 다음 코어 확인 - 전선 연결 되었을 때만, 선택된 코어개수 +1
			if (cnt != 0)
				connectPower(coreIdx + 1, lineSum + cnt, coreSum + 1);
			else
				connectPower(coreIdx + 1, lineSum + cnt, coreSum);

			// 전선 그리기 해제
			for (int loop = 1; loop <= cnt; loop++) {
				int lineR = coreList.get(coreIdx).r + dr[dIdx] * loop;
				int lineC = coreList.get(coreIdx).c + dc[dIdx] * loop;
				map[lineR][lineC] = 0;
			}
//			System.out.println("후");
//			for (int[] m : map) {
//				System.out.println(Arrays.toString(m));
//			}
		}
	}

}
