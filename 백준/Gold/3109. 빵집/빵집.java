import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 3109_빵집
	 * 
	 * 	[설명]
	 * 가스 도둑이 되기로한 원웅
	 * 빵집 위치 R*C 격자로 표현
	 * 		. : 빈칸
	 * 		x : 건물
	 * 	첫째열 : 근처 빵집
	 * 	마지막열 : 원웅이네 빵집
	 * 
	 * 	# 파이프 연결 규칙
	 * 1. 건물이 있으면 파이프 놓을 수 없음
	 * 2. 파이프는 첫째열에서 시작해 마지막열에서 끝남
	 * 3. 오른쪽, 오른쪽위, 오른쪽아래 연결 가능
	 * 4. 파이프라인이 서로 겹치면 안됨
	 * 
	 * 파이프라인 최대 개수?
	 * 
	 * 	[입력]
	 * ===> 공간 크기 R과 C (공백 구분)
	 * (R줄의 입력)
	 * ===> 공백 구분 안됨
	 * 
	 * 	[출력]
	 * 파이프라인 최대 개수
	 * 
	 * 	[풀이방법]
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int R, C, ans, minRow;
	static char map[][];
	static boolean flag, isSet[][];
	
	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 공간 크기 R과 C (공백 구분)
		st = new StringTokenizer(br.readLine().trim());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		// 공간 정보 입력받기
		map = new char[R][C];
		for (int rIdx = 0; rIdx < R; rIdx++) {
			String tmp = br.readLine().trim();
			for (int cIdx = 0; cIdx < C; cIdx++) {
				map[rIdx][cIdx] = tmp.charAt(cIdx);
			}
		}

		// 파이프가 놓인 위치 표시
		isSet = new boolean[R][C];
		for (int rIdx = 0; rIdx < R; rIdx++) {
			flag = false;
			isSet[rIdx][0] = true;
			setPipe(rIdx, 0);
		}
		sb.append(ans);
		System.out.println(sb);
	}

	// 일단 파이프 한줄 놓는 방법의 수
	// 제일 위쪽으로 향하는 파이프
	static int[] dr = { -1, 0, 1 }, dc = { 1, 1, 1 };

	private static void setPipe(int row, int col) {

		if(col == C-1) {
			// 파이프가 완성되었다
			ans++;
			flag = true;
			isSet[row][col] = true;
			return;
		}
		for (int i = 0; i < dr.length; i++) {
			// 마지막 열에 왔을 때 가장 위쪽에 있는 파이프			
			
			int nr = row + dr[i];
			int nc = col + dc[i];

			// 공간 범위 밖이거나
			if (nr < 0 || nc < 0 || nr >= R || nc >= C)
				continue;
			// 건물이 위치해있거나 파이프가 이미 놓여있으면 안됨
			if (map[nr][nc] == 'x' || isSet[nr][nc])
				continue;
			
			isSet[nr][nc] = true;
			setPipe(nr, nc);
			// 방문 표시를 풀지 않아도 된다.
			// 왜냐하면 파이프를 위쪽 방향으로 향하는걸 우선으로 하니까
			// 이미 우선이 되는 경우를 살펴봤으니까 표시를 해제할 필요가 없다
			if(flag)	// 파이프가 완성되었으면 더 확인할 필요가 없으니까 return
				return;
		}

	}
	
}
