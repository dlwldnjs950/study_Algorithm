import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 4012_요리사
	 * 
	 * 	[설명]
	 * 두 명의 손님에게 음식 제공
	 * 최대한 비슷한 맛의 음식 만들어야함
	 * N개의 식재료
	 * N/2개로 나눠 두 개의 요리 A와 B
	 * 
	 * 	# 식재료의 맛
	 * 식재료 i와 식재료 j의 시너지 Sij
	 * 최종 맛은 이 시너지들의 합
	 * 
	 * 두 음식 간에 맛의 차이가 최소가 되는 경우
	 * 그 맛의 차이를 출력
	 * 
	 * 	[입력]
	 * ===> 테스트 케이스 개수 T
	 * (T개의 테스트 케이스)
	 * ===> 재료의 개수 N
	 * (N 줄의 입력)
	 * ===> 다른 재료와의 시너지 (공백 구분)
	 * 
	 * 	[출력]
	 * 맛 차이의 최소값
	 * 
	 * 	[풀이방법]
	 * 각 재료별  다른 재료와의 시너지 저장 List<Map<Integer,Integer>>
	 * 조합을 만들어내고 시너지 들의 합을 구하기
	 * 재료가 6개라서 1,2,3번을 골랐다면 S12+S13+S21+S23+S31+S32가 이 음식의 맛 점수...?
	 * List.get(1)해서 자기자신 제외하고 Map.get
	 * 
	 * 조합을 찾는 문제지만 선택된 원소의 자리 기준으로 구분하는게...나을거 같으니 부분집합 코드로 풀자~
	 * 
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static boolean[] isSelected;
	static int N, minDiff;
	static Map<Integer,Map<Integer,Integer>> synergy;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = Integer.parseInt(br.readLine().trim());

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			N = Integer.parseInt(br.readLine().trim());
			
			// 각 재료의 시너지 저장 자료구조
			synergy = new HashMap<>();
			for (int iIdx = 1; iIdx <= N; iIdx++) {
				// iIdx의 다른 재료와의 시너지 저장
				synergy.put(iIdx, new HashMap<Integer,Integer>());
				st = new StringTokenizer(br.readLine().trim());
				for (int otherIdx = 1; otherIdx <= N; otherIdx++) {
//					if(iIdx == otherIdx) {
//						// 자기 자신이면 넣지않고 넘어간다
//						st.nextToken();
//						continue;
//					}
					// 이 때, 자기 자신과의 시너지는 0이다
					// 안넣으려고했는데 for문 돌릴때 그냥 0 값을 더하는게 조건문 덜 쓸거같아서 일단..
					synergy.get(iIdx).put(otherIdx, Integer.parseInt(st.nextToken()));
				}
			}
			
			minDiff = Integer.MAX_VALUE;
			// 각 재료가 선택되었는지 표시하는 배열 
			isSelected = new boolean[N+1];
			subset(0,0);
			
			sb.append(minDiff).append("\n");
		}
		System.out.println(sb);
	}
	
	static void subset(int cnt, int selected) {
		int sumA = 0;
		int sumB = 0;
		if(cnt == N) {
			if(selected == N/2) {
			// 각 요리의 시너지 합을 구하고 차이를...구하고...
			// 하나는...true인거 더하고...다른 하나는 false인거 더하고...
			sumA = synergySum(true);
			sumB = synergySum(false);
			minDiff = Math.min(minDiff, Math.abs(sumA-sumB));
			}
			return;
		}
		isSelected[cnt] = true;
		subset(cnt + 1, selected + 1);
		isSelected[cnt] = false;
		subset(cnt + 1, selected);
	}

	static int synergySum(boolean flag){	// flag 값에 따라 고른것만 더하거나 안고른것만 더하거나
		int sum=0;
		for(int idx = 1; idx<=N; idx++) {
			for(int idx2=1; idx2<=N; idx2++) {
				if(isSelected[idx]==flag && isSelected[idx2]==flag) {
					sum+= synergy.get(idx).get(idx2);
				}
			}
		}
		return sum;
	}
}
