import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	/**
	 * # 구간 합 구하기
	 * 2차원 배열이 주어질 때 좌표로 주어지는 구간에 대한 합계를 구한다
	 * */	
	static BufferedReader br;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException{
		// 객체 초기화
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 표의 크기 N, 합 구해야하는 횟수 M
		String[] NM = br.readLine().trim().split(" ");
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);
		
		// 각 줄의 누적합?
		
		// 표 정보
		int[][] map = new int[N + 1][N + 1];
		int[][] prefixSumMap = new int[N + 1][N + 1];
		// N줄의 표 정보가 들어온다
		for (int nLoop = 1; nLoop <= N; nLoop++) {
			// 한 줄씩 읽는다 (공백 구분)
			String[] tmp = br.readLine().trim().split(" ");
			for (int idx = 0; idx < N; idx++) {
				// 표 정보 저장
				map[nLoop][idx + 1] = Integer.parseInt(tmp[idx]);
				// 각 가로줄의 누적합
				prefixSumMap[nLoop][idx + 1] = map[nLoop][idx + 1] + prefixSumMap[nLoop][idx];
			}
		}
		
		// M줄의 구간 좌표 정보
		// 각 줄은 "시작 x 시작 y 종료 x 종료 y" 값이다
		// 구해야 하는 범위 : 두 좌표를 '왼쪽위-오른쪽아래' 대각선 꼭지점으로 두는 직사각형
		for(int mLoop = 0; mLoop < M; mLoop++) {
			String[] xy = br.readLine().trim().split(" ");
			int startX = Integer.parseInt(xy[0]);
			int startY = Integer.parseInt(xy[1]);
			int endX = Integer.parseInt(xy[2]);
			int endY = Integer.parseInt(xy[3]);
			// 확인할 row는 시작x ~ 종료x
			// 확인할 col은 시작y ~ 종료y
			int prefixSum = 0;
			for(int startRow = startX; startRow<=endX; startRow++) {
				prefixSum += prefixSumMap[startRow][endY] - prefixSumMap[startRow][startY - 1];
			}
			sb.append(prefixSum).append("\n");
		}
		System.out.println(sb);
		
	}

}
