import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

	/*
	 * 수업 시작-끝 시간 주어질 때, 필요한 최소 강의실 수
	 * 시작 시간 기준 가장 빨리 끝나는 활동부터 배정...
	 * 강의실 리스트 : 끝나는 시간
	 * */

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Class implements Comparable<Class>{
		int start;
		int end;
		
		Class(int start, int end){
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Class o) {
			return this.start - o.start;
		}
	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int classNum = Integer.parseInt(br.readLine().trim());
		Class[] classTime = new Class[classNum];
		for(int idx=0; idx<classNum; idx++){
			st = new StringTokenizer(br.readLine().trim());
			classTime[idx] = new Class(Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(classTime);
		
		PriorityQueue<Integer> rooms = new PriorityQueue<>();
		for(Class c:classTime){
			if(rooms.isEmpty()){
				rooms.offer(c.end);
				continue;
			}
			// 종료 시간보다 시작 시간이 작으면 새로운 강의실
			if(rooms.peek() <= c.start)
				rooms.poll();
			rooms.offer(c.end);
		}

		System.out.println(rooms.size());

	}

}