import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	/**
	 * 	#2961 도영이가 만든 맛있는 음식
	 * 
	 * 	[ 설명 ]
	 * 재료 N개
	 * 각 재료의 신맛 S와 쓴맛 B
	 * 	신맛의 시너지는 곱, 쓴맛은 합
	 * 재료는 적어도 하나 사용해야 한다
	 * 신맛과 쓴맛의 차이가 가장 작은 요리를 만들자
	 * 
	 * 	[ 입력 ]
	 * ===> 재료 개수 N
	 * ===> 각 재료의 신맛과 쓴맛 공백 구분
	 * 
	 * 	[ 출력 ]
	 * 신맛과 쓴맛의 차이 최소값
	 * 
	 * 	[ 풀이 방법 ]
	 * 1. 주어진 재료를 선택하는 부분 집합
	 * 	1-1. 이 때, 공집합은 제외한다 (재료는 적어도 하나 사용)
	 * 			 -> 부분 집합 생성 시 선택된 재료 개수 매개변수?
	 * 	1-2. 부분집합은 모든 재료를 살펴보며 각 재료의 선택 여부를 결정
	 * 			선택 여부를 표시할 배열이 필요하다
	 * 2. 부분집합이 구해졌을 때,
	 * 	2-1. 선택된 재료들에 대해 신맛은 곱, 쓴맛은 합을 구한다
	 * 	2-2. 신맛곱과 쓴맛합 차이의 최소값을 갱신한다
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int ingredientCnt, ingreTaste[][], minDiff;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 재료 개수
		ingredientCnt = Integer.parseInt(br.readLine().trim());
		
		// 각 재료의 신맛과 쓴맛 정보
		ingreTaste = new int[ingredientCnt][2];
		for(int ingreIdx = 0; ingreIdx<ingredientCnt; ingreIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			ingreTaste[ingreIdx][0] = Integer.parseInt(st.nextToken());
			ingreTaste[ingreIdx][1] = Integer.parseInt(st.nextToken());
		}
		
		// 재료에 대한 부분집합 구하기	
		isSelected = new boolean[ingredientCnt];
		minDiff = Integer.MAX_VALUE;
		powerSet(0,0);
		
		sb.append(minDiff);
		System.out.println(sb);
	}

	static boolean[] isSelected;
	
	private static void powerSet(int cnt, int trueCnt) {// 살펴볼 재료의 인덱스 번호(또는 살펴본 재료의 개수), 선택한 재료 개수
		// 기저 조건 - 전체 재료를 살펴봤으면
		if(cnt == ingredientCnt) {
			// 선택된 재료가 하나도 없으면 넘어간다
			if(trueCnt == 0)
				return;
			//System.out.println(Arrays.toString(isSelected) + trueCnt);
			// 각 재료에 대해 선택된 재료의 신맛곱, 쓴맛합을 구한다
			int sour = 1; int bitter = 0;
			for(int ingreIdx = 0; ingreIdx<ingredientCnt ; ingreIdx++) {
				if(isSelected[ingreIdx]) {
					sour *= ingreTaste[ingreIdx][0];
					bitter += ingreTaste[ingreIdx][1];
				}
			}
			//System.out.println(sour +"/"+bitter);
			// 신맛과 쓴맛의 차이의 절댓값과 비교하여 차이의 최소값 갱신
			minDiff = Math.min(minDiff, Math.abs(sour - bitter));
			return;
		}
		
		// 각 재귀의 동작
		// 해당 재료를 선택하고 다음 재료 확인
		isSelected[cnt] = true;
		powerSet(cnt +1, trueCnt +1);
		// 해당 재료를 선택하지 않고 다음 재료 확인
		isSelected[cnt] = false;
		powerSet(cnt +1, trueCnt);
	}

}
