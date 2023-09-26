import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	/**
	 * 
	 * 스도쿠 채우기
	 * 1부터 9까지 채워보기
	 * 	다 채워보고 맞는지 확인하는건 오래 걸리니까
	 * 	놓기전에 놓을 수 있는지 확인 먼저 하기
	 * 해당하는 (정사각형, 행, 열) 규칙에 맞는지
	 * 
	 */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int SIZE = 9;
	static int map[][];
	static boolean end;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 하다 만 스도쿠 입력받기
		map = new int[SIZE][SIZE];
		
		for(int rowIdx= 0; rowIdx<SIZE; rowIdx++) {
			
			String line = br.readLine().trim();
			
			for(int colIdx= 0; colIdx<SIZE; colIdx++) {
				map[rowIdx][colIdx] = line.charAt(colIdx) - '0';
			}
		}
		
		end = false;
		fillNumber(0);
		
		for(int[] row : map) {
			for(int col : row) {
				sb.append(col);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	// 숫자 채우기
	// index = row * SIZE + col
	private static void fillNumber(int index) {
		//마지막 숫자까지 채웠으면 끝내기
		if(index == SIZE*SIZE) {
			// System.out.println("다채웠따");
			end = true;
			return;
		}
		
		int row = index / SIZE;
		int col = index % SIZE;
		
		// 0인 경우에만 채우기
		if (map[row][col] == 0) {

			for (int number = 1; number < 10; number++) {

				if (itsOkay(index, number)) {
					
					map[row][col] = number;
					fillNumber(index + 1);
					
					// 제대로된 답을 이미 찾았으면 되돌리면 안된다.
					if(end) return;
					// 되돌리기
					map[row][col] = 0;
				}
			}
		}else {
			// 0 이 아니면 그냥 이동만
			fillNumber(index + 1);
		}
	}
	
	// 해당 숫자 놓을 수 있는지 확인하기
	private static boolean itsOkay(int index, int number) {
		int row = index / SIZE;
		int col = index % SIZE;
		
		// 가로 세로 확인
		for(int idx = 0; idx<SIZE; idx++) {
			// 넣으려는 숫자가 이미 들어가 있다면
			if(map[row][idx] == number || map[idx][col] == number)
				return false;
		}
		
		// 사각형 영역 확인		
		int startRow = (row/3)*3;	// 3*3등분된 영역에서 몇번째 인지 찾는거니까 /3하고 각 칸이 3*3이니까 *3
		int startCol = (col/3)*3;
		
		for(int rowIdx = 0; rowIdx<3; rowIdx++) {
			for(int colIdx = 0; colIdx<3; colIdx++) {
				// 넣으려는 숫자가 이미 들어가 있따면
				if(map[startRow + rowIdx][startCol + colIdx] == number)
					return false;
			}
		}
		
		return true;
	}
	
}
