import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	/**
	 * # 12891 DNA 비밀번호
	 * 
	 * 	DNA 문자열 : 모든 문자열에 등장하는 문자가 {‘A’, ‘C’, ‘G’, ‘T’}
	 * 	DNA 문자열의 부분 문자열을 비밀번호로 쓰기로함
	 * 	등장 문자 개수가 특정 개수 이상이어야함
	 * 
	 * 	부분 문자열 길이
	 * 	각 문자의 최소 등장 개수
	 * 
	 * 	만들 수 있는 비밀번호 종류의 수를 구해라
	 * 	부분 문자열 등장 위치가 다르면 부분문자열이 같아도 다른 문자열
	 * 
	 * 	슬라이딩 윈도우 : 만들어진 문자열 옆으로 옮겨가면서
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	static final Character[] DNA = {'A', 'C', 'G', 'T'};
	static int[] leastCnt;	// 최소등장횟수
	static int[] appearCnt;	// 각 문자 선택된 횟수
	static boolean[] isSelected;
	static int S;	// 문자열 길이
	static int P;	// 부분 문자열 길이
	static String DNAstr;	// 민호가 만든 임의의 문자열
	static int finalCnt;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 문자열 길이 S, 부분 문자열 길이 P (한 줄에 공백 구분으로 입력)
		st = new StringTokenizer(br.readLine().trim());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		// 문자열 입력받기
		DNAstr = br.readLine().trim();
		isSelected = new boolean[S];
		
		// 각 문자 최소 등장 횟수 (한 줄에 공백 구분)
		st = new StringTokenizer(br.readLine().trim());
		leastCnt = new int[4];
		for (int lIdx = 0; lIdx < DNA.length; lIdx++) {
			leastCnt[lIdx] =  Integer.parseInt(st.nextToken());
		}
		
		// 만들어진 문자열을 옆으로 옮겨가며 확인
		// 문자열의 0번 인덱스부터 시작되는 P 길이의 문자열 일 때,각 개수를 카운팅
		// 오른쪽으로 옮겨가면서 왼쪽 문자 개수 -- 오른쪽 문자 개수 ++
		// 문자열 마지막까지 만 확인
		appearCnt  = new int[4];
		for(int idx=0; idx<P; idx++) {
			if(DNAstr.charAt(idx) == DNA[0]) appearCnt[0]++;
			if(DNAstr.charAt(idx) == DNA[1]) appearCnt[1]++;
			if(DNAstr.charAt(idx) == DNA[2]) appearCnt[2]++;
			if(DNAstr.charAt(idx) == DNA[3]) appearCnt[3]++;
		}
		if(compareCnt())
			finalCnt++;
		for(int idx=1; idx+P-1 < S; idx++) {
			if(DNAstr.charAt(idx-1) == DNA[0]) appearCnt[0]--;
			if(DNAstr.charAt(idx-1) == DNA[1]) appearCnt[1]--;
			if(DNAstr.charAt(idx-1) == DNA[2]) appearCnt[2]--;
			if(DNAstr.charAt(idx-1) == DNA[3]) appearCnt[3]--;
			if(DNAstr.charAt(idx+P-1) == DNA[0]) appearCnt[0]++;
			if(DNAstr.charAt(idx+P-1) == DNA[1]) appearCnt[1]++;
			if(DNAstr.charAt(idx+P-1) == DNA[2]) appearCnt[2]++;
			if(DNAstr.charAt(idx+P-1) == DNA[3]) appearCnt[3]++;
			
			if(compareCnt())
				finalCnt++;
		}
		System.out.println(finalCnt);
	}

	private static boolean compareCnt() {

		for(int idx = 0; idx<leastCnt.length; idx++) {
			if(leastCnt[idx] > appearCnt[idx])
				return false;
		}
		return true;
	}
}
