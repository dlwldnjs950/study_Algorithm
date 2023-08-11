import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 20299_3대 측정
	 * 
	 * 	[설명]
	 * 3대 측정은 스쿼트, 벤치프레스, 데드리프트
	 * 세명이 한팀으로 출전 -> 코드포스 레이팅 비교 (팀원 각각의 실력을 수치로 나타내주는)
	 * 
	 * 	# VIP 클럽 자격 조건
	 * 1. 팀원 3명의 코드포스 레이팅의 합이 K 이상
	 * 2. 모든 팀원의 코드포스 레이팅이 L 이상
	 * 
	 * 지원자 중 가입할 수 있는 팀의 수와 VIP 회원들의 레이팅을 출력
	 * 
	 * 	[입력]
	 * ===> 팀수 N 팀조건 K 개인조건 L (공백구분)
	 * (N줄의 입력)
	 * ===> 팀원의 레이팅 (공백 구분)
	 * 
	 * 	[출력]
	 * ---> 가입 가능한 팀 수
	 * ---> VIP 회원들의 레이팅 (입력받은 순서대로)
	 * 
	 * 	[풀이방법]
	 * 각 팀당 3명씩이니까 3으로 나눈 몫으로 팀을 구분해서
	 * 가능 여부 표시 배열에 표시
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 팀수 N 팀조건 K 개인조건 L (공백구분)
		st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		// 입력된 순서대로 팀원의 레이팅을 저장한다
		List<Integer> rating = new ArrayList<>();
		// 가입 불가능 여부를 표시하는 배열
		//레이팅을 입력할 때, 개인의 레이팅이 L을 넘어야한다.
		boolean[] impossible = new boolean[N];
		int[] teamSum = new int[N];
		

		// 반복문의 인덱스 값이 팀 번호가 된다 (팀번호 0부터 시작)
		for (int teamNum = 0; teamNum < N; teamNum++) {
			// 각 팀별 팀원의 레이팅
			st = new StringTokenizer(br.readLine().trim());
			while (st.hasMoreTokens()) {
				int tmp = Integer.parseInt(st.nextToken());
				rating.add(tmp);
				// 각 팀원이 L 미만이면 가입하지 못한다
				if (tmp < L)
					impossible[teamNum] = true;
				// 각 팀별 레이팅 합계
				teamSum[teamNum] += tmp;
			}
		}
		
		int cnt=0;
		// 합계로 가입 가능한 팀인지 판별 및 팀 개수 카운트
		for(int teamNum=0 ; teamNum<N; teamNum++) {
			if(teamSum[teamNum]<K)
				impossible[teamNum] = true;
			if(!impossible[teamNum])
				cnt++;
		}
		
		sb.append(cnt).append("\n");
		for(int idx = 0; idx<rating.size(); idx++) {
			// 가입이 가능한 팀이었다면
			// 팀원의 인덱스/3의 값이 팀 번호다 (팀 번호는 0부터 시작)
			if(!impossible[idx/3])
				// 출력해주자
				sb.append(rating.get(idx)).append(" ");
		}
		
		System.out.println(sb);
	}

}
