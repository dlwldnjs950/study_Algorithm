import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 1233_사칙연산 유효성 검사
	 * 
	 * 	[설명]
	 * 사칙연산 식은 이진 트리로 표현할 수 있다
	 * 가장 마지막 리프 노드만 숫자
	 * 나머지는 연산자가 있어야 한다
	 * 
	 * 10개의 테스트 케이스
	 * 노드의 개수는 200개를 넘지 않는다
	 * 
	 * 	[입력]
	 * ===> 트리 정점의 수 N (1번이 루트 정점)
	 * ===> 정점번호 연산자 자식번호1 자식번호2
	 * 		또는 정점번호 숫자
	 * 
	 * 	[출력]
	 * 계산 가능하면 1
	 * 불가능하면 0 출력
	 * 
	 * 	[풀이방법]
	 * 트리를 배열로 표현했을 때 인덱스 번호 특성을 활용
	 * => 트리를 배열에 저장할때 번호 a 정점의 자식 정점은 a*2와 a*2+1 번이다
	 * 1. 올바른 입력
	 * 		1-1. 4개의 정보 입력 시 연산자가 포함된 입력
	 * 		1-2. 2개의 정보 입력 시 숫자가 포함된 입력
	 * 2. 잘못된 입력
	 * 		2-1. 연산자 정보가 들어와야 하는데 숫자 정보가 들어온 경우
	 * 			2-1-1. 정점의 자식이 존재한다(자식번호가 구간 내에 포함)
	 * 		2-2 숫자 정보가 들어와야 하는데 연산자 정보가 들어온 경우
	 * 			2-1-1. 정점의 자식이 없다.(자식번호가 구간 내에 미포함)
	 * 
	 * 정보가 4개가 들어왔을 때,
	 * 		연산자 정보가 들어왔어야 하는게 맞는지
	 * 		연산자 정보가 포함되었는지 확인
	 * 정보가 2개 들어왔을 때,
	 * 		숫자 정보가 들어왔어야 하는게 맞는지
	 * 		숫자 정보가 포함되어 있는지  확인
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int OPERATION_INFO_CNT = 4;
	static final int NUMBER_INFO_CNT = 2;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = 10;

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");

			// 정점 개수
			int vertexCnt = Integer.parseInt(br.readLine().trim());

			boolean flag = true;

			// 정점 개수 만큼 정보 입력 받기
			for (int inputLoop = 0; inputLoop < vertexCnt; inputLoop++) {
				st = new StringTokenizer(br.readLine());
				
				// 정점 번호와 2번째 정보는 공통으로 필요하므로 미리 변수에 값을 저장
				int vertexNo = Integer.parseInt(st.nextToken());
				String secondToken = st.nextToken();

				// 토큰 2개는 변수에 저장했으므로 -2
				// 연산자 정보 토큰 개수와 같으면
				if (st.countTokens() == OPERATION_INFO_CNT - 2) {
					
					// 연산자 정보가 들어올 때였고, 연산자 정보가 들어왔다면 유효함
					if(inRange(vertexNo, vertexCnt) && !isNumber(secondToken)) {
						continue;
					}else {
						flag = false;
					}
				
				}else if (st.countTokens() == NUMBER_INFO_CNT - 2) {
					
					// 숫자 정보가 들어올 때였고, 숫자 정보가 들어왔다면 유효함
					if(!inRange(vertexNo, vertexCnt) && isNumber(secondToken)) {
						continue;
					}else {
						flag = false;
					}
				}				
			}
			sb.append(flag?1:0).append("\n");

		}
		System.out.println(sb);
	}

	// 해당 정점의 자식 정점 번호가 범위 내인지 판단
	static boolean inRange(int vertex, int vertexCnt) {
		int leftNo = vertex*2;
		int rightNo = vertex*2 +1;
		
		// 둘다 범위 내가 아니라면 가장 마지막 리프노드
		if(leftNo> vertexCnt && rightNo > vertexCnt)
			return false;
		
		return true;
	}
	
	// 2번째 정보가 숫자 정보인지 확인
	static boolean isNumber(String secondToken) {
		char tmp = secondToken.charAt(0);
		
		// 연산자 종류
		char[] operations = { '+', '-', '*', '/' };
		
		// 연산자 중에 하나라면 숫자가 아니다
		for (int operationIdx = 0; operationIdx < operations.length; operationIdx++) {
			if(tmp == operations[operationIdx])
				return false;
		}
		
		return true;
	}
}
