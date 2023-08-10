import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 14889_스타트와 링크
	 * 
	 * 	[설명]
	 * N명의 사람들이 절반으로 팀을 나눠 경기
	 * 사람들 마다 같은 팀이었을 때 시너지가 있다
	 * 능력치를 최소로하는 팀 조합을 찾아 그 능력치 차이의 최솟값을 출력
	 * 
	 * 	[입력]
	 * ===> 사람 수 N
	 * (N 줄의 입력)
	 * ===> 각 사람과의 시너지 (공백 구분)
	 * 
	 * 	[출력]
	 * 능력치 차이의 최소값 출력
	 * 
	 * 	[풀이방법]
	 * 이번엔 배열에 저장해서 해보자~
	 * 조합으로 풀어보자~
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static boolean[] isSelected;
	static int N, minDiff;
	static int[][] teamwork;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine().trim());
		
		// 각 사람의 능력치 입력받기
		teamwork = new int[N+1][N+1];
		for(int rIdx = 1; rIdx<=N; rIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int cIdx = 1; cIdx<=N; cIdx++) {
				teamwork[rIdx][cIdx] = Integer.parseInt(st.nextToken());
			}
		}
		
		isSelected = new boolean[N+1];
		minDiff = Integer.MAX_VALUE;
		comb(0, 1);

		sb.append(minDiff);
		System.out.println(sb);
	}
	
	private static void comb(int cnt, int start) {
		int sumStart = 0;
		int sumLink = 0;
		
        // 인원 절반으로 팀이 꾸려졌으면 재귀 멈춤
		if(cnt == N/2) {
			for(int rIdx = 1; rIdx<=N; rIdx++) {
				for (int cIdx = 1; cIdx <= N; cIdx++) {
                    // 선택된 팀원들 능력치만 더하기
					if (isSelected[rIdx] && isSelected[cIdx])
						sumStart += teamwork[rIdx][cIdx];
                    // 선택 안된 팀원들 능력치만 더하기
					if (!isSelected[rIdx] && !isSelected[cIdx])
						sumLink += teamwork[rIdx][cIdx];
				}
			}
            // 최소값 저장
			minDiff = Math.min(minDiff, Math.abs(sumLink-sumStart));
			return;
		}
		for(int idx = start; idx<=N; idx++) {
			isSelected[idx] = true;
			comb(cnt+1, idx+1);
			// 선택된 배열에 넣는 것 처럼 값이 덮어씌어지지 않기 때문에 표시 해제
			isSelected[idx] = false;
		}
	}

}
