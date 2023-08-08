import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	/**
	 * 	# 1220 Magnetic
	 * 
	 * 	[설명]
	 * S극(푸른) 자성체는 N극에 이끌림 (row --)
	 * N극(붉은) 자성체는 S극에 이끌림 (row ++)
	 * 테이블에 남아있는 교착 상태의 개수를 구해라
	 * 교착 상태 : N극과 S극이 서로 부딪혀 떨어지지 못하고 남아있는 상태
	 * 
	 * 테이블 크기 100X100
	 * N극은 윗부분에 S극은 아랫부분에 위치
	 * 
	 * 	[입력]
	 * (10개의 테스트 케이스)
	 * 정사각형 테이블의 한변 길이 (항상 100)
	 * 테이블 상태 한 줄, 공백 구분
	 * 		1은 N극 자성체(붉은)
	 * 		2는 S극 자성체(푸른)
	 * 
	 * 	[풀이방법]
	 * 한 회전에 2는 row-- 움직임
	 * 		  1은 row++ 움직임
	 * 더이상 움직임이 없을 때 까지...지만~! 더이상 움직임이 없는지 판단 어케혀...?
	 * row++로 탐색하며
	 * 		1을 만나면, 2를 만나는지
	 * 			지나온 길의 중간에서 부딕히게 됨
	 * 		1을 만났는데, 2를 안만나면
	 * 			떨어짐...인데 교착상태인 애들이 있어서 못떨어짐
	 * 		1을 안만났는데, 2를 만나면
	 * 			떨어짐...인데 교착상태인 애들이 있으면 못떨어짐
	 * 테이블 끝까지만 확인
	 * 		==> 마지막 테이블 상태 출력이면 이 과정을 코드로...^^*
	 * 
	 * 빨-파 페어가 몇개 있는지 카운트~!
	 * row++로 탐색하며
	 * 1을 만나고 나서, 2를 만나면 cnt++ (flag 해제)
	 * 
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int SIZE = 100;
	
	public static void main(String[] args) throws IOException{
		//System.setIn(new FileInputStream("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스 수 T
		int T = 10;
		
		for(int testCase = 1; testCase<= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			// 테이블의 크기가 입력된다 (필요없음)
			br.readLine().trim();
			
			// 테이블 배열 객체
			int[][] table = new int[SIZE][SIZE];
			
			// 각 열 별 flag - row가 바뀔 때 마다 확인
			// 1을 만났으면 해당 열의 flag를 true
			// true인 상태에서 2를 만나면 cnt++하고 flag 다시 false
			boolean[] flag = new boolean[100];
			
			// 교착상태 개수
			int cnt=0;
			
			for(int rowIdx=0; rowIdx<100; rowIdx++) {
				// 공백으로 구분된 한 줄 입력받기
				st = new StringTokenizer(br.readLine().trim());
				for(int colIdx = 0; colIdx<100; colIdx++) {
					// 해당 위치 테이블 값
					table[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
					// 1을 만났으면 flag에 표시
					if(table[rowIdx][colIdx]==1)	flag[colIdx] = true;
					// 2를 만났으면 flag 상태 확인 및 표시
					if(table[rowIdx][colIdx]==2 && flag[colIdx]) {
						cnt++;
						flag[colIdx] = false;
					}
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);

	}

}
