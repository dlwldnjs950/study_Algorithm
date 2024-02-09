import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	// 그물의 길이 I : 이걸로 직사각형을 만들어 물고기 잡기
	// 어장은 NxN
	// 물고기는 M마리
	// 어장 밖으로 그물을 치지 않음
	// 가장 많은 물고기의 마릿수
	
	public static void main(String[] args) throws IOException {
	
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 어장크기 그물길이 물고기수
		st = new StringTokenizer(br.readLine().trim());
		int mapSize = Integer.parseInt(st.nextToken());
		int netLength = Integer.parseInt(st.nextToken());
		int fishNum = Integer.parseInt(st.nextToken());

		// 물고기 위치
		List<int[]> fishes = new ArrayList<int[]>();

		for (int loop = 0; loop < fishNum; loop++) {
			int cnt = 0;
			st = new StringTokenizer(br.readLine().trim());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			fishes.add(new int[] { row, col });
		}

		int maxCatch = 0;
		// 잡을 물고기 범위를 설정한다...
		// 어차피 모든 인덱스 순서로 확인할거라 minRow, maxCol 이런거 안해도됨...
		for (int firstFish = 0; firstFish < fishNum; firstFish++) {
			for (int secondFish = 0; secondFish < fishNum; secondFish++) {

				int row = fishes.get(firstFish)[0];
				int col = fishes.get(secondFish)[1];
				
				// 그물 크기
				for (int width = 1; width < netLength / 2; width++) {
					int catchCnt = 0;
					int height = netLength / 2 - width;

					// 잡을 수 있는 물고기인지 확인
					for (int idx = 0; idx < fishNum; idx++) {
						if (row <= fishes.get(idx)[0] && fishes.get(idx)[0] <= row + height
								&& col <= fishes.get(idx)[1] && fishes.get(idx)[1] <= col + width)
							catchCnt++;
					}
					maxCatch = Math.max(maxCatch, catchCnt);
				}
				
				
			}
		}

		
		sb.append(maxCatch);
		System.out.println(sb.toString());

	}

}
