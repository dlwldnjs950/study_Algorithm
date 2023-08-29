import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/**
	 * # 17478_재귀함수가 뭔가요
	 * 
	 * [설명] 재귀 함수에 대해 답변하는 챗봇의 응답을 출력해라
	 * 
	 * [입력] ===> 재귀 횟수
	 * 
	 * [출력]
	 * 
	 * [풀이방법]
	 *  1. 재귀적으로 반복되는 문구와 아닌 문구를 구분한다
	 *  2. 재귀 내에서도 반복적으로 출력되는지
	 *  	2-1. 특정 조건에만 출력되는지 구분한다. 
	 *  	2-2. 어느 시점에 출력되는지(재귀가 호출되었을 때, 또는 재귀 호출 이후)
	 * 
	 * 재귀의 기저 조건 loop가 원하는 횟수에 도달했을 때
	 * 각 재귀의 동작
	 * 출력할 문구 앞에 재귀를 호출한 순번에 따라 "____"붙이기
	 */

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	/*
	 * 0 : 재귀와 상관없이 출력
	 * 1 : 재귀 호출 시 출력
	 * 2~4 : 재귀 내에서 출력
	 * 5 : 재귀를 끝내는 조건에서 출력
	 * 6 : 재귀를 끝내고 출력
	 * */
	static final String[] SENTENCES = new String[]{
			"어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n",
			"\"재귀함수가 뭔가요?\"\n",
			"\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n",
			"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n",
			"그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n",
			"\"재귀함수는 자기 자신을 호출하는 함수라네\"\n",
			"라고 답변하였지.\n"
	};

	static int loop;
	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 답변을 몇번 반복하는지 입력받기
		loop = Integer.parseInt(br.readLine().trim());
		
		sb.append(SENTENCES[0]);
		printAnswer(0,"");
		
		System.out.print(sb);
	}

	static void printAnswer(int loopCnt, String prefix){
		// 재귀 호출 시 출력s
		sb.append(prefix);
		sb.append(SENTENCES[1]);
		
		// 기저 조건
		if(loopCnt == loop) {
			sb.append(prefix);
			sb.append(SENTENCES[5]);
			sb.append(prefix);
			sb.append(SENTENCES[6]);
			return;
		}
		
		// 재귀 내에서 출력
		for(int sIdx = 2; sIdx<=4; sIdx++) {
			sb.append(prefix);
			sb.append(SENTENCES[sIdx]);
		}
		printAnswer(loopCnt+1, prefix+"____");
		// 재귀 호출 이후 출력
		sb.append(prefix);
		sb.append(SENTENCES[6]);
	}
}
