import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] MN = br.readLine().split(" ");
		int M = Integer.parseInt(MN[0]);
		int N = Integer.parseInt(MN[1]);
		char[][] map = new char[M][N];
		for(int i=0; i<M;i++) {
			String str = br.readLine();
			for(int j=0; j<N;j++) {
				map[i][j]=str.charAt(j);
			}
		}
		
		int min = Integer.MAX_VALUE;
		for (int a = 0; a <= M-8; a++) {
			for (int b = 0; b <= N-8; b++) {
				int cnt=0;
				char first = map[a][b];	//맨 왼쪽 위칸의 색 저장
				char expect = first;
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if(map[a+i][b+j]!=expect)
							cnt++;
						expect = switchWB(expect);	//칸 넘어가기 전에 기대값 변경
					}
					expect = switchWB(first);	//줄이 바뀔때 기대값 변경
					first = switchWB(first);	//각 줄의 첫번째 칸
				}
				if(min>cnt)
					min=cnt;
				cnt=0;
				first = switchWB(map[a][b]);	//맨 왼쪽 위칸의 색 저장
				expect = first;
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if(map[a+i][b+j]!=expect)
							cnt++;
						expect = switchWB(expect);	//칸 넘어가기 전에 기대값 변경
					}
					expect = switchWB(first);	//줄이 바뀔때 기대값 변경
					first = switchWB(first);	//각 줄의 첫번째 칸
				}
				if(min>cnt)
					min=cnt;
			}
		}
		System.out.println(min);
	}

	public static char switchWB(char wb) {
		if (wb == 'W')
			return 'B';
		else
			return 'W';
	}
}