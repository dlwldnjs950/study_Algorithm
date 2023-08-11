import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 15686_치킨 배달
	 * 
	 * 	[설명]
	 * NxN 도시가 있다
	 * 도시의 각 칸은 빈칸/치킨집/집
	 * 도시의 행열은 1부터 시작
	 * 
	 * 	# 치킨 거리란?
	 * 집과 가장 가까운 치킨집 사이의 거리
	 * 각자의 집은 치킨 거리를 가지고 있다
	 * 	|r1-r2| + |c1-c2|
	 * 
	 * 	# 도시의 치킨 거리란?
	 * 모든 집의 치킨 거리의 합
	 * 
	 * 치킨집은 최대 M개만 남기고 폐업시키기로함
	 * 어떻게 고르면 도시의 치킨 거리가 가장 작게되는가?
	 * 
	 * 	[입력]
	 * ===> 도시 크기 결정하는 N과 치킨집 최대개수 M (공백 구분)
	 * (N줄 입력, N열(공백구분)
	 * ===> 도시 정보 N개 (공백구분)
	 * 		0은 빈칸, 1은 집, 2는 치킨집
	 * 
	 * 	[출력]
	 * 도시의 치킨 거리 최솟값
	 * 
	 * 	[풀이방법]
	 * 남길 M개의 가게를 고르고 치킨 거리를 구해보자
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N, M, cityMap[][], surviveList[], cityDistance;
	static List<int[]> chickenList, houseList;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		//N과 M 입력받기
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 치킨집과 집 좌표 저장 리스트
		chickenList = new ArrayList<int[]>();
		houseList = new ArrayList<int[]>();

		// 도시 정보 입력 받기
		cityMap = new int[N+1][N+1];
		for (int rIdx = 1; rIdx <= N; rIdx++) {
			// 한 줄 입력받기 (공백 구분)
			st = new StringTokenizer(br.readLine().trim());
			for (int cIdx = 1; cIdx <= N; cIdx++) {
				cityMap[rIdx][cIdx] = Integer.parseInt(st.nextToken());
				if(cityMap[rIdx][cIdx] == 1) {
					houseList.add(new int[] {rIdx, cIdx});
				}else if(cityMap[rIdx][cIdx] == 2) {
					chickenList.add(new int[] {rIdx, cIdx});
				}
			}
		}
		
		surviveList = new int[M];
		cityDistance=Integer.MAX_VALUE;
		combination(0, 0);
		
		sb.append(cityDistance);
		System.out.println(sb);
	}
	
	// 남길 가게 고르기. 가게 리스트 : chickenList
	private static void combination(int cnt, int start) {
		if(cnt == M) {
			// M개의 가게를 모두 골랐으니 도시의 치킨 거리를 구해보자
			// 각 집에 대해 선택된 치킨집과의 거리를 구해서 최소값을...찾고
			// 각 집의 치킨 거리의 합이 도시의 치킨 거리
			int tmp = 0;
			for(int idx=0; idx<houseList.size(); idx++) {
				tmp+=minDistance(idx);
			}
			cityDistance = Math.min(cityDistance, tmp);
			return;
		}
		for(int i=start; i<chickenList.size(); i++) {
			surviveList[cnt] = i;	// 선택된 치킨 리스트의 인덱스 번호 저장
			combination(cnt+1, i+1);
		}
	}
	
	// 각 집에 대해 선택된 치킨집과의 치킨 거리 구하기
	private static int minDistance(int houseIdx) {
		int min = Integer.MAX_VALUE;
		for(int idx = 0; idx<M; idx++) {
			int tmp = distance(houseList.get(houseIdx)[0], houseList.get(houseIdx)[1], chickenList.get(surviveList[idx])[0], chickenList.get(surviveList[idx])[1]);
			min = Math.min(tmp, min);
		}
		return min;
	}
	
	// 치킨 거리 구하는 함수
	private static int distance(int r1, int c1, int r2, int c2) {
		return Math.abs(r1-r2) + Math.abs(c1-c2);
	}
	
	

}
