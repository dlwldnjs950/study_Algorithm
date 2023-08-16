import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 1992_쿼드트리
	 * 
	 * 	[설명]
	 * 쿼드 트리 : 흑백 영상 압축 표현
	 * 	0(흰 점), 1(검은 점)이 몰려있으면 압축
	 * 1. 전부 0이면 0
	 * 2. 전부 1이면 1
	 * 3. 0과 1이 섞여있으면
	 * 		왼쪽위, 오른쪽위, 왼쪽아래, 오른쪽아래 4개의 영역으로  나누어 압축
	 * 		압축 결과를 차례대로 괄호 안에 묶어서 표현
	 * 
	 * N*N 크기의 영상이 주어질 때, 압축한 결과를 출력
	 * 
	 * 	[입력]
	 * ===> 영상의 크기 결정 N
	 * (N줄의 입력)
	 * ===> 영상 정보 (공백 구분 안됨)
	 * 
	 * 	[출력]
	 * 영상 압축 결과
	 * 
	 * 	[풀이방법]
	 * 색종이 만들기처럼
	 * 사분면으로 나누고
	 * 전부 0인지 확인
	 * 아니면 또 사분면으로 나누기
	 * 괄호는 해당 재귀를 부른 시작과 마지막
	 * 		(왼쪽위 사분면을 방문 전에 왼쪽 괄호
	 * 		오른쪽아래 사분면 방문 후에 오른쪽 괄호)
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N, video[][];

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 영상 크기 N 입력받기
		N = Integer.parseInt(br.readLine().trim());

		// 영상 정보 입력 받기
		video = new int[N][N];
		for(int rIdx = 0; rIdx< N; rIdx++) {
			String tmp = br.readLine().trim();
			for(int cIdx = 0; cIdx < N; cIdx++) {
				video[rIdx][cIdx] = tmp.charAt(cIdx)-'0';
			}
		}
		
		compressing(0, 0, N);
		System.out.println(sb);
	}
	
	private static void compressing(int r, int c, int size) { // 시작 좌표, 너비
		
		// 해당 구역을 전부 더해서 0인지 1인지 섞여있는지
		int sum = 0;
		for (int rIdx = r; rIdx < r + size; rIdx++) {
			for (int cIdx = c; cIdx < c + size; cIdx++) {
				sum += video[rIdx][cIdx];
			}
		}

		if (sum == 0) { // 전부 0이었다
			sb.append(0);
		} else if (sum == size * size) { // 전부 1이었다
			sb.append(1);
		} else { // 섞여있으면 4분면으로 나눠서 같은 동작
			int half = size >> 1;
			sb.append("(");
			compressing(r, c, half);
			compressing(r, c + half, half);
			compressing(r + half, c, half);
			compressing(r + half, c + half, half);
			sb.append(")");
		}
	}

}
