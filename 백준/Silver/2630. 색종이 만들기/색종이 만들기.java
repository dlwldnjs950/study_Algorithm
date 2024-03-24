import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	// 시작 칸, 해당 칸 너비
	// 한가지 색상만 있는지 확인...
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int paperSize, map[][], white, blue;
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		paperSize = Integer.parseInt(br.readLine().trim());
		
		map = new int[paperSize][paperSize];
		
		for(int rIdx=0; rIdx<paperSize; rIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int cIdx=0; cIdx<paperSize; cIdx++) {
				map[rIdx][cIdx] = Integer.parseInt(st.nextToken());
			}
		}
		
		separatePaper(0, 0, paperSize);
		
		sb.append(white).append("\n").append(blue);
		System.out.println(sb);

	}
	private static void separatePaper(int row, int col, int width) {
		
		// 해당 공간이 같은 색으로 채워져 있는지 확인
				int sum = 0;
				for (int rIdx = row; rIdx < row + width; rIdx++) {
					for (int cIdx = col; cIdx < col + width; cIdx++) {
						sum += map[rIdx][cIdx];
					}
				}
				
				if(sum == 0) {	// 전부 흰색
					white++;
				}else if(sum == width*width) {	// 전부 파란색
					blue++;
				}else {	// 섞여있다는 뜻이니까 4분할해서 재귀
					int half = width >> 1;
					separatePaper(row, col, half);
					separatePaper(row, col + half, half);
					separatePaper(row + half, col, half);
					separatePaper(row + half, col + half, half);
				}
	}
}
