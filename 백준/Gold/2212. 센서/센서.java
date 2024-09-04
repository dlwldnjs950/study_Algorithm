import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));

		int sensorNum = Integer.parseInt(br.readLine().trim());
		int centerNum = Integer.parseInt(br.readLine().trim());
		
		int sensors[] = new int[sensorNum];
		st = new StringTokenizer(br.readLine().trim());
		for(int idx=0; idx<sensorNum; idx++) {
			sensors[idx] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(sensors);
		
		// 긴 거리를 수신 하지 않도록 끊어주는 느낌?
		PriorityQueue<Integer> distances = new PriorityQueue<>(Collections.reverseOrder());
		
		for (int idx = 1; idx < sensorNum; idx++) {
			int distance = sensors[idx] - sensors[idx - 1];
			if (distance != 0)
				distances.add(distance);
		}
		
		// 집중국 -1 만큼 거리 뺴내기
		for(int loop=0; loop< centerNum-1; loop++) {
			distances.poll();
		}
		
		int answer = 0;
		while(!distances.isEmpty()) {
			answer+= distances.poll();
		}

		System.out.println(answer);
		
		
	}
}
