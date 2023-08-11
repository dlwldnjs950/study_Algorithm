import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 2839_설탕 배달
	 * 
	 * 	[설명]
	 * 설탕 N킬로그램 배달
	 * 설탕 묶음은 3kg, 5kg
	 * 최대한 적은 봉지를 들고가자
	 * 
	 * 	[입력]
	 * ===> 배달할 설탕 무게 N
	 * 
	 * 	[출력]
	 * 배달하는 봉지 최소 개수
	 * (Nkg을 만들지 못한다면 -1 출력)
	 * 
	 * 	[풀이방법]
	 * 완전 탐색으로 풀었군...
	 * 무게를 인덱스로 갖는 배열!
	 * 값으로는 봉지 개수를 가지게 된다.
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 배달할 설탕 무게 N 입력받기
		int N = Integer.parseInt(br.readLine().trim());
		int[] bongji = new int[N + 1];

		if (N >= 3)
			bongji[3] = 1;
		if (N >= 5)
			bongji[5] = 1;
		
		for (int num = 6; num <= N; num++) {
			// 무게가 5의 배수면 - 봉지 중에 더 무거운 것부터 개수를 확인하자~!
			if (num % 5 == 0) {
				bongji[num] = bongji[num - 5] + 1;
			// 무게가 3의 배수면
			} else if (num % 3 == 0) {
				bongji[num] = bongji[num - 3] + 1;
			// 3의 배수도, 5의 배수도 아닌데
			} else {
				// 이 전에 봉지의 조합이 있었다면, (예를 들어 8)
				if (bongji[num - 3] != 0 && bongji[num - 5] != 0) {
					bongji[num] = Math.min(bongji[num - 3], bongji[num - 5]) + 1;
				}
			}
		}
		if(bongji[N] == 0) {
			sb.append(-1);
		}else {
			sb.append(bongji[N]);
		}
		
		System.out.println(sb);
	}

}
