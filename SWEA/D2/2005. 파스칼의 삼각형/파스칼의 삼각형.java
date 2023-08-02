import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Date : 2023. 8. 2.
 * @Problem : # 2005 파스칼의 삼각형
 * @Description : 
 * 	크기가 N인 파스칼의 삼각형을 만들자
 * 	파스칼 삼각형 규칙
 * 	1. 첫번쨰 줄은 항상 숫자 1이다
 * 	2. 두번째 줄 부터 각 숫자들은 자신의 왼쪽과 오른쪽 위 의 숫자의 합으로 구성
 *
 *	합을 구하는데 필요한 이전 줄과
 *	구한 합을 저장할 줄이 필요하다
 *
 */
public class Solution {

	static BufferedReader br;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스 개수 입력받기
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int testCase=1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append("\n");
			// 파스칼 삼각형 크기 입력받기
			int N = Integer.parseInt(br.readLine().trim());
			int[][] tmpResult = new int[2][10];
			int cur=0, prv=0;
			for(int triSize = 0; triSize<N ; triSize++) {
				prv = (triSize+1)%2;
				cur = triSize%2;
				for(int resultIdx = 0; resultIdx<=triSize; resultIdx++) {
					if (resultIdx == 0 || resultIdx == triSize) tmpResult[cur][resultIdx] = 1;
					else tmpResult[cur][resultIdx] = tmpResult[prv][resultIdx - 1] + tmpResult[prv][resultIdx];
				}
				for(int resultIdx = 0; resultIdx<=triSize; resultIdx++) {
					sb.append(tmpResult[cur][resultIdx]).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);

	}

}
