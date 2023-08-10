import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 6808_규영이와 인영이의 카드게임
	 * 
	 * 	[설명]
	 * 1에서 18까지 카드로 게임
	 * 한 게임에 카드 섞어 9장씩 나눔 
	 * 	( 9 라운드 )
	 * 한 라운드에 한 장씩 카드 내서 점수
	 * 	높은 수 : 두 카드 합
	 * 	낮은 수 : 노 점수
	 * 총점 높으면 승자
	 * 총점 같으면 무승부
	 * 
	 * 규영이가 낸 카드 주어짐 (순서 고정. 정렬 안되어있음)
	 * 규영이가 이기는 경우와 지는 경우가 총 몇가지인지 구해라
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 개수 TC
	 * (TC줄의 입력)
	 * ===> 규영이의 카드 (공백 구분)
	 * 
	 * 	[출력]
	 * 이기는 경우와 지는 경우 공백으로 구분해 출력
	 * 
	 * 	[풀이방법]
	 * 규영이의 카드에 포함되지 않은 카드들로
	 * 순열을 구해서
	 * 각 자리를 비교해 점수를 계산하고 (규영, 인영 모두)
	 * 규영이가 이기는 경우, 지는 경우를 각각 카운트
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int kyuCard[];
	static int inCard[];
	static int cards[];
	static boolean isSelected[];
	
	static int kyuScore;
	static int inScore;
	
	static int kyuWin;
	static int kyuLose;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 TC
		int TC = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=TC; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			kyuWin = 0;
			kyuLose = 0;
			
			// 규영이의 카드 입력받기
			st = new StringTokenizer(br.readLine().trim());
			kyuCard = new int[9];
			// 규영이가 가진 카드를 표시할 배열 카드값-1이 인덱스
			boolean kyuHas[] = new boolean[18];
			for (int idx = 0; idx < 9; idx++) {
				kyuCard[idx] = Integer.parseInt(st.nextToken());
				kyuHas[kyuCard[idx] - 1] = true;
			}
			// 인영이가 가진 카드 결정하기
			inCard = new int[9];
			int inIdx = 0;
			for (int idx = 0; idx < 18; idx++) {
				if(!kyuHas[idx])
					inCard[inIdx++] = idx+1;
			}
			
			// 인영카드 순열 만들기
			// 만들어진 카드 저장할 배열
			cards = new int[9];
			isSelected = new boolean[9];
			// 만들어진 카드로 점수 계산하기
			// 누가 이겼는지 확인하기
			// 순열함수에서 했음
			permutation(0);
			sb.append(kyuWin).append(" ").append(kyuLose).append("\n");
			
		}
		System.out.println(sb);
	}
	
	static void permutation(int cnt) {
		
		if(cnt==9) {
			// 점수 계산하기
			calculrate();
			whoWin();
		}else {
			for (int idx = 0; idx < 9; idx++) {
				if (isSelected[idx])	// 이미 선택된 수라면 넘어가고
					continue;
				cards[cnt] = inCard[idx];	// 고른 카드에 넣기
				isSelected[idx] = true;
				permutation(cnt + 1);
				isSelected[idx] = false;
			}		
		}
	}
	
	static void calculrate() {
		// 계산하기 전에 초기화
		kyuScore = 0;
		inScore = 0;
		for(int idx = 0; idx < 9; idx++) {
			if(kyuCard[idx] > cards[idx]) {	// 규영 카드가 더 큰 경우
				kyuScore+= kyuCard[idx] + cards[idx];
			}else {	// 인영 카드가 더 큰 경우. 같은 카드는 없음
				inScore+= kyuCard[idx] + cards[idx];
			}
		}
	}
	
	static void whoWin() {
		if(kyuScore > inScore)
			kyuWin++;
		else if(kyuScore < inScore)
			kyuLose++;
	}

}
