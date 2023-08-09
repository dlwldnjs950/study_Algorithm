import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 2563_색종이
	 * 
	 * 	[설명]
	 * 가로세로 100인 정사각형 도화지
	 * 그 위에 가로세로 10인 정사각형 색종이 평행하게 붙임
	 * 한 장 또는 여러 장
	 * 색종이가 붙은 영역의 넓이 구하기
	 * 
	 * 색종이는 항상 도화지 안쪽에 붙어있다
	 * 
	 * 	[입력]
	 * ===> 색종이 수 N
	 * (N 줄 입력)
	 * ===> 자연수 2개 (공백 구분)
	 * 		도화지왼쪽변~색종이왼쪽변거리 도화지아래쪽변~색종이아래쪽변거리
	 * 
	 * 	[출력]
	 * 색종이 영역 넓이
	 * 
	 * 	[풀이방법]
	 * 색종이가 겹쳐있는 부분은 다중 카운트하면 안됨
	 * 색종이 한장 확인할 때, 카운팅했다는 표시를 하고
	 * 다음 색종이 확인할 때, 이미 카운팅 했으면 카운트하지 않는다
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int WHITESIZE = 100;
	static final int BLACKSIZE = 10;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 색종이 몇장인지 N
		int N = Integer.parseInt(br.readLine().trim());
		// 각 칸 카운팅 여부 2차원 배열
		boolean isCounted[][] = new boolean[WHITESIZE+1][WHITESIZE+1];

		int totalCnt = 0;
		for (int loop = 0; loop < N; loop++) {
			st = new StringTokenizer(br.readLine().trim());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// 시작 지점을 기준으로 색종이 크기만큼 확인
			for (int row = x; row < x + BLACKSIZE; row++) {
				for (int col = y; col < y + BLACKSIZE; col++) {
					// 카운트하지 않은 칸이라면
					if(!isCounted[row][col]) {
						totalCnt++;
						//카운트 했다고 표시
						isCounted[row][col] = true;
					}
				}
			}
		}
		sb.append(totalCnt);
		System.out.println(sb);
	}

}
