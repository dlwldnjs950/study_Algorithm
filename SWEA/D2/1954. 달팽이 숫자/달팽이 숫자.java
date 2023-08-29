import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 1954_달팽이 숫자
	 * 
	 * 	[설명]
	 * N*N까지 숫자가 시계방향으로 이루어져 있다
	 * 
	 * 정수 N을 입력받아 N크기의 달팽이 출력
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 개수
	 * ===> 달팽이 크기 N
	 * 
	 * 	[출력]
	 * 달팽이 숫자 출력
	 * 
	 * 	[풀이방법]
	 * 1. 배열에 값을 저장해서 출력한다
	 * 2. 배열에 값을 넣는 방법
	 * 	2-1. 우하좌상 바꿔가며 입력
	 * 	2-2. 다음에 넣을 방향이
	 * 		  범위를 넘어갈 때, 이미 값이 입력되어있을 때 방향 바꾸기
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int snailSize, snailMap[][];

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append("\n");

			// 달팽이 사이즈 입력받기
			snailSize = Integer.parseInt(br.readLine().trim());
			
			// 달팽이숫자 저장할 배열
			snailMap = new int[snailSize][snailSize];

			int number = 1;	// 넣을 숫자 
			int row = 0, col = 0;	// 숫자 넣을 좌표
			// 델타 배열
			int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };
			// 방향
			int direction = 0;
			while (true) {
				snailMap[row][col] = number;
				
				// 넣는 숫자가 N*N과 같아지면 끝낸다
				if(number == snailSize * snailSize)
					break;
				
				// 다음 좌표 확인
				int nr = row + dr[direction];
				int nc = col + dc[direction];

				// 범위를 넘어가면 방향을 바꾼다
				if (nr < 0 || nc < 0 || nr >= snailSize || nc >= snailSize) {
					direction = (direction + 1) % dr.length;
					continue;
				}
				// 이미 값이 있는 칸이어도 값을 바꾼다
				if (snailMap[nr][nc] != 0) {
					direction = (direction + 1) % dr.length;
					continue;
				}
				// 무사히 넘어왔으면 다음 넣을 좌표로 갱신
				row = nr;
				col = nc;
				// 넣을 값도 ++
				number++;
			}

			for (int idx = 0; idx < snailSize; idx++) {
				for(int num : snailMap[idx]) {
					sb.append(num).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

}