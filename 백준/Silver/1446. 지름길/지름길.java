import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 지름길 개수 N
	 * 고속도로 길이 D
	 * 지름길 시작위치 도착위치 지름길길이
	 * 
	 * 세준이가 운전해야하는 거리의 최솟값
	 * */

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	public static class Path{
		int start;
		int distance;
		Path(int start, int distance){
			this.start = start;
			this.distance = distance;
		}
	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

		List<List<Path>> graph = new ArrayList<>();
		for (int idx = 0; idx < 10001; idx++) {
			graph.add(new ArrayList<>());
		}

		for (int loop = 0; loop < N; loop++) {
			st = new StringTokenizer(br.readLine().trim());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());

			graph.get(end).add(new Path(start, distance));
		}

		int distances[] = new int[10001];
		Arrays.fill(distances, Integer.MAX_VALUE);
		distances[0] = 0;
		for (int dist = 1; dist <= D; dist++) {
			if (graph.get(dist).size() > 0) {
				for (Path p : graph.get(dist)) {
					if (distances[p.start] + p.distance < distances[dist]) {
						distances[dist] = Math.min(distances[dist - 1] + 1, distances[p.start] + p.distance);
					}
				}
			}else {
				distances[dist] = distances[dist-1]+1;
			}
		}

		System.out.println(distances[D]);

	}

}
