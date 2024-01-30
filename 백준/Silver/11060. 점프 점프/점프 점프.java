import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 1 x N 크기의 미로
	 * 각 칸에는 정수가 쓰여있다 = 한 번에 최대 점프할 수 있는 칸 수
	 * 		3번째칸에 3이 적혀있다면, 4,5,6번 칸 중 하나로 점프할 수 있다.
	 * 
	 * 가장 왼쪽에서 가장 오른쪽으로 갈 때, 최소 점프 횟수는?
	 * 갈 수 없는 경우엔 -1
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 미로 크기
		int size = Integer.parseInt(br.readLine().trim());
		
		// 미로 정보
		int map[] = new int[size + 1];
		// 최소 점프 저장 변수
		int minJump[] = new int[size + 1];

		st = new StringTokenizer(br.readLine().trim());

		for (int idx = 1; idx <= size; idx++) {
			map[idx] = Integer.parseInt(st.nextToken());
			minJump[idx] = 10001;
		}

		minJump[1] = 0;

		for (int idx = 1; idx <= size; idx++) {
			for (int distance = 1; distance <= map[idx]; distance++) {
				if (idx + distance > size)
					break;
				minJump[idx + distance] = Math.min(minJump[idx] + 1, minJump[idx + distance]);
			}
		}

		if (minJump[size] == 10001)
			sb.append(-1);
		else
			sb.append(minJump[size]);

		System.out.println(sb);
	}

}
