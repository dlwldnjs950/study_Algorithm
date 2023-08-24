import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 15961_회전 초밥
	 * 
	 * 	[설명]
	 * 초밥의 종류는 번호
	 * 같은 종류 초밥 둘 이상
	 * 
	 * 먹은 초밥의 식대만큼 계산
	 * 
	 * 	# 이벤트
	 * 1. 임의의 한 위치부터 k 개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공
	 * 2. 쿠폰 : 1번 행사에 참여한 경우 쿠폰에 적힌 종류의 초밥 하나 추가 무료 제공
	 * 
	 * 가능한 다양한 종류의 초밥 먹기
	 * 	연속해서 초밥을 먹을 경우엔
	 * 		쿠폰에 적힌 초밥은 제외하고 먹는게 좋다
	 * 
	 * 회전초밥 음식점 벨트 상태
	 * 메뉴에 있는 초밥 가짓수
	 * 연속해서 먹는 접시 개수
	 * 쿠폰 번호
	 * 
	 * 손님이 먹을 수 있는 초밥 가짓수의 최대값?
	 * 
	 * 	[입력]
	 * ===> 회전벨트에놓인접시수 N 초밥의가짓수d 연속해서먹는접시수 k 쿠폰번호 c (공백구분)
	 * (N줄의 입력)
	 * ===> 각 접시에 놓인 초밥의 번호
	 * 
	 * 	[출력]
	 * 
	 * 	[풀이방법]
	 * 방법1. k개 만큼 고르고 연속되어 있는지 확인
	 * 방법2. 연속된거 먹고 확인하기
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		// 회전 벨트에 놓인 접시 수 N
		int N = Integer.parseInt(st.nextToken());
		// 초밥의 가짓 수 d
		int d = Integer.parseInt(st.nextToken());
		// 연속해서 먹는 접시 수 k
		int k = Integer.parseInt(st.nextToken());
		// 쿠폰번호 c
		int c = Integer.parseInt(st.nextToken());
		
		// 회전 벨트 정보
		int[] sushi = new int[N];
		// 선택된 스시 정보
		int[] selected = new int[d+1];
		// 회전 벨트 정보 입력받기
		for(int idx = 0; idx<N; idx++) {
			sushi[idx] = Integer.parseInt(br.readLine().trim());
		}

		List<Integer> eaten = new ArrayList<>();
		// 연속한거 먹기 로 풀어보자
		// 0번 인덱스를 기준으로 k개를 먹는 상황부터 시작
		int kindCnt = 0;	// 먹은 스시 종류
		for(int idx = 0; idx<k; idx++) {
			if(selected[sushi[idx]] == 0)	// 선택된적 없으면
				kindCnt++;	// 종루 개수에 카운트
			selected[sushi[idx]]++;
			eaten.add(sushi[idx]);
		}
		//System.out.println(eaten);
		int maxCnt = kindCnt;
		// 최대 가짓수 일 때, 먹기 시작하는 인덱스 리스트
		List<Integer> list = new ArrayList<>();
		list.add(0);

		// 시작 상태에서 i번 인덱스 빼고, i+k번 인덱스 넣고
		// 넣는 인덱스가 한바퀴 돌아서 와야하니까 (i+k)%N번 인덱스로 계산
		for (int idx = 0; idx < N - 1; idx++) {
			// 빠지는 초밥 종류
			int outKind = sushi[idx];
			// 추가되는 초밥 종류
			int inKind = sushi[(idx + k) % N];
			//System.out.println("out : " + outKind + " in : " + inKind);
			eaten.remove(0);
			eaten.add(inKind);
			// 선택되었던 종류의 초밥이라면 개수에서 제외하고, 선택 해제
			if (--selected[outKind] == 0) {
				kindCnt--;
			}
			// 선택된 적 없는 종류의 초밥이면 개수 증가, 선택 표시
			if (++selected[inKind] == 1) {
				kindCnt++;
			}
			//System.out.println("종류 가짓수 : " + kindCnt);
			// 선택한 초밥에 쿠폰이 포함되어 있으면
			if(selected[c]!=0) {
				maxCnt = Math.max(maxCnt, kindCnt);
			}else {
				maxCnt = Math.max(maxCnt, kindCnt+1);
			}
		}
		System.out.println(maxCnt);
	}

}
