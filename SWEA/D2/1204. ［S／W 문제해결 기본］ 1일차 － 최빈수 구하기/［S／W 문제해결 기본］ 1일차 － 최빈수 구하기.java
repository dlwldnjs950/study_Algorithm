import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			// 테스트 케이스 번호는 필요하지 않다
			br.readLine().trim();
			
			//1000명의 학생의 번호를 입력받는다
			st = new StringTokenizer(br.readLine().trim());
			
			// 각 점수의 횟수를 저장할 배열 (점수 = 인덱스번호)로 사용하기 위해 101칸 배열
			int[] gradeCnt = new int[101];
			// 나중에 반복문을 다시 돌지 않고
			// 카운트를 하면서 가장 많이 나온 횟수와 그 숫자를 저장한다.
			int maxCnt=0; int maxCntNum=0;
			while(st.hasMoreTokens()){
				int tmpGrade = Integer.parseInt(st.nextToken());
				gradeCnt[tmpGrade]++;
				if(gradeCnt[tmpGrade] > maxCnt) {
					maxCnt = gradeCnt[tmpGrade];
					maxCntNum = tmpGrade;
				}else if(gradeCnt[tmpGrade] == maxCnt) {	// 같은 최빈수가 있으면 큰수로 바꾸기...?
					if(maxCntNum < tmpGrade)
					maxCntNum = tmpGrade;
				}
			}
			sb.append(maxCntNum).append("\n");
		}
		System.out.println(sb);
	}

}
