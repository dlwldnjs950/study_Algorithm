import java.io.*;
import java.util.*;

public class Main {

	/*
	 * 공유기의 거리로 가능한 값을 추리자
	 * 해당 거리로 공유기가 모든 집을 커버할 수 있는지 확인한다.
	 * */

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int houseNum, routerNum, houses[];

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		houseNum = Integer.parseInt(st.nextToken());
		routerNum = Integer.parseInt(st.nextToken());
		
		houses = new int[houseNum];
		for (int idx = 0; idx < houseNum; idx++) {
			houses[idx] = Integer.parseInt(br.readLine().trim());
		}
		
		Arrays.sort(houses);

		int max = houses[houseNum -1] - houses[0];
		int min = 1;
		// 굳이 안해도 되는 동작
//		for(int idx=1; idx<houseNum; idx++) {
//			int distance = houses[idx] - houses[idx-1];
//			min = Math.min(min, distance);
//		}
		
		int answer = 0;
		while(min <= max) {
			int mid = (min + max) /2;
			
			// mid를 최대로 C개의 공유기 설치가 가능한지 확인
			if(canInstall(mid)) {
				answer = mid;
				min = mid +1;
			}else {
				max = mid -1;
			}
		}

		System.out.println(answer);

	}

	private static boolean canInstall(int distance) {
		int cnt = 1;
		
		int lastInstalled = houses[0];
		
		for(int idx=1; idx<houseNum; idx++) {
			
			// 정해둔 공유기 간 최소 거리를 만족하는 경우에만 설치
			if(houses[idx] - lastInstalled >= distance) {
				cnt++;
				lastInstalled = houses[idx];
			}
		}
		
		return cnt >= routerNum;
	}

}
