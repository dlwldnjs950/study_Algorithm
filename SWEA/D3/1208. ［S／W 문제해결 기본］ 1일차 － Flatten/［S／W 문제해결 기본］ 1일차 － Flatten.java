import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 1208_Flatten
	 * 
	 * 	[설명]
	 * 평탄화 : 높은 곳의 상자를 낮은 곳에 옮기는 방식으로 최고점과 최저점 간격을 줄이는 작업
	 * 상자를 옮기는 횟수에 제한이 걸려 있을 때,
	 * 그 횟수만큼 평탄화 작업을 한 후 최고점과 최저점 차이를 반환해라
	 * 
	 * 가로 길이는 항상 100
	 * 주어진 덤프 횟수 이내에 평탄화가 완료되면 중간에 끝냄
	 * 
	 * 10개의 테스트 케이스
	 * 
	 * 	[입력]
	 * ===> 
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * 0. 덤프 횟수와 각 상자의 높이 입력받기
	 * 1. 최고점과 최저점 찾기
	 * 	1-1. 매번 반복문? 1000번 * 100번 * 테케 10개 = 100만번
	 * 	1-2. 우선순위 큐?
	 * 2. 찾은 최고점과 최저점에 대해 덤프
	 * 	2-1. 이 때, 차이가 0이거나 1이면 끝낸다
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int BOXWIDTH = 100;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = 10;

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 덤프 횟수
			int dumpCnt = Integer.parseInt(br.readLine().trim());
			
			// 각 칸의 박스 높이
			PriorityQueue<int[]> max = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					// 높이 기준 최대 힙
					return o2[1] - o1[1];
				}
			});
			PriorityQueue<int[]> min = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					// 높이 기준 최소 힙
					return o1[1] - o2[1];
				}
			});
			int[] boxHeight = new int[BOXWIDTH];
			st = new StringTokenizer(br.readLine().trim());
			for(int idx=0; idx<BOXWIDTH; idx++) {
				int tmpHeight = Integer.parseInt(st.nextToken());
				// 높이 기준으로 최대힙과 최소힙 구성 (0:박스 인덱스, 1: 박스 높이)
				max.offer(new int[] {idx,tmpHeight});
				min.offer(new int[] {idx,tmpHeight});
				boxHeight[idx] = tmpHeight;
			}
			
			for(int dump=0; dump<dumpCnt; dump++) {
				int[] maxBox = max.poll();
				int[] minBox = min.poll();
				int diff = maxBox[1] -minBox[1];
				if(diff == 1 || diff == 0)
					break;
				boxHeight[maxBox[0]]--;
				boxHeight[minBox[0]]++;
				max.offer(new int[] {maxBox[0],boxHeight[maxBox[0]]});
				min.offer(new int[] {minBox[0],boxHeight[minBox[0]]});
			}
			
			sb.append(max.peek()[1] - min.peek()[1]).append("\n");
		}
		System.out.println(sb);
	}

}
