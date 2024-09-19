import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		int truckNum = Integer.parseInt(st.nextToken());
		int bridgeLength = Integer.parseInt(st.nextToken());
		int limitWeight = Integer.parseInt(st.nextToken());

		int[] trucks = new int[truckNum];
		String[] tmpTrucks = br.readLine().trim().split(" ");
		for (int idx = 0; idx < truckNum; idx++) {
			trucks[idx] = Integer.parseInt(tmpTrucks[idx]);
		}

		// 풀이 시작
		Queue<Integer> bridge = new ArrayDeque<>();
		int time = 0;
		int onBridgeWeight = 0;
		for (int idx = 0; idx < truckNum; idx++) {
			int nowTruck = trucks[idx];
			
			while(true) {
				if(bridge.size() == bridgeLength) {
					onBridgeWeight -= bridge.poll();
				}else {
					time++;
					
					if(nowTruck + onBridgeWeight <= limitWeight) {
						onBridgeWeight += nowTruck;
						bridge.add(nowTruck);
						break;
					}else {
						bridge.add(0);
					}
				}
			}
		}

		time += bridgeLength;
		System.out.println(time);

	}

}
