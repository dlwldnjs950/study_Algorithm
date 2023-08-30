import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 1225_암호생성기
	 * 
	 * 	[설명]
	 * 	# 암호 생성 방법 (1 사이클)
	 * 1) 8개의 숫자를 입력 받는다
	 * 2) 첫번째 숫자를 1감소한 뒤 맨뒤로 보낸다
	 * 3) 다음 첫번째 숫자를 2 감소한 뒤 맨뒤로
	 * ...
	 * 6) 다음 첫번째 숫자를 5감소한 뒤 맨뒤로
	 * 
	 * 숫자를 감소시킬 때, 0보다 작아지면 0으로 유지
	 * 이때, 8자리 숫자 값이 암호가 된다.
	 * (어떤 한자리가 0이 될 때 까지 반복)
	 * 
	 * 10개의 테스트 케이스
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 번호
	 * ===> 8개의 번호 
	 * 
	 * 	[출력]
	 * 각 테스트 케이스 별로 만들어진 암호 배열을 출력한다
	 * 
	 * 	[풀이방법]
	 * 1. 앞에서 빼고 뒤로 넣기 때문에 큐 자료구조를 사용한다
	 * 2. 어떤 한 숫자가 0이 될 때까지 반복한다
	 * 3. 감소시키는는 숫자는 1-> 2-> 3-> 4->5 순서로 바뀜
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int NUMBERCNT = 8;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = 10;

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 테스트 케이스 번호 (필요없으므로 받기만 한다)
			br.readLine();
			
			// 숫자 배열 입력받기
			Queue<Integer> originNumber = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine().trim());
			while (st.hasMoreTokens()) {
				originNumber.offer(Integer.parseInt(st.nextToken()));
			}
			
			int minusNumber =1;
			while(true) {
				// 맨앞에 있는 숫자를 꺼내서
				int tmp = originNumber.poll();
				// 값을 차감하고
				tmp-= minusNumber++;
				// 0보다 작아지는 경우엔 0으로 유지하고
				if(tmp<0)
					tmp = 0;
				// 해당 값을 큐에 넣은 다음에
				originNumber.offer(tmp);
				
				// 5를 넘어가면 0 1부터 다시 차감
				if(minusNumber == 6)
					minusNumber = 1;
				// 그 값이 0이 되었다면 반복을 그만한다.
				if(tmp == 0)
					break;
				
			}
			
			while(!originNumber.isEmpty()) {
				sb.append(originNumber.poll()).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
