import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 회문2
	 * 
	 * 	[설명]
	 * 가로세로 모두를 보아 가장 긴 회문의 길이를 구하라
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 번호 (필요 없음) - 10개가 주어짐
	 * ===> 100*100의 글자판 정보
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * 가로방향과 세로방향 각각 확인
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int SIZE = 100; 

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = 10;

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			// 테스트 케이스 번호
			br.readLine().trim();
			
			// 100*100 숫자판 정보
			char[][] map = new char[SIZE][];
			for (int rIdx = 0; rIdx < SIZE; rIdx++) {
				String tmp = br.readLine().trim();
				map[rIdx] = tmp.toCharArray();
			}
			
			int maxLength = Integer.MIN_VALUE;
			// 가로방향 회문 확인
			for (int rIdx = 0; rIdx < SIZE; rIdx++) {
				// 시작 지점과 끝 지점 선택
				// 좌우로 좁혀오면서 확인
				for (int leftCol = 0; leftCol < SIZE; leftCol++) {
					for (int rightCol = SIZE-1; rightCol >=0 && leftCol<=rightCol; rightCol--) {
						boolean flag = true;
						// 시작지점이 항상 왼쪽에 있거나 같아야한다
						for(int idx = 0; leftCol+idx<=rightCol-idx; idx++) {
							// 서로 다르면 확인 지점 바꾸기
							if(map[rIdx][leftCol+idx] != map[rIdx][rightCol-idx]) {
								flag = false;
							}
						}
						// 아직 회문이 유효할 경우에만
						if (flag) {
							// 회문의 길이는 시작-끝+1
							int length = rightCol - leftCol + 1;
							maxLength = Math.max(maxLength, length);
						}
					}
				}
			}
			
			// 세로방향 회문 확인
			for (int cIdx = 0; cIdx < SIZE; cIdx++) {
				// 시작 지점과 끝 지점 선택
				// 상하로 좁혀오면서 확인
				for (int upRow = 0; upRow < SIZE; upRow++) {
					for (int downRow = SIZE - 1; downRow >= 0 && upRow<=downRow; downRow--) {
						boolean flag = true;
						// 시작지점이 항상 왼쪽에 있거나 같아야한다
						for (int idx = 0; upRow + idx <= downRow - idx; idx++) {
							// 서로 다르면 확인 지점 바꾸기
							if (map[upRow + idx][cIdx] != map[downRow - idx][cIdx]) {
								flag = false;
							}
						}
						if (flag) {
							// 회문의 길이는 시작-끝+1
							int length = downRow - upRow + 1;
							maxLength = Math.max(maxLength, length);
						}
					}
				}
			}
			sb.append(maxLength).append("\n");
		}
		System.out.println(sb);
	}

}
