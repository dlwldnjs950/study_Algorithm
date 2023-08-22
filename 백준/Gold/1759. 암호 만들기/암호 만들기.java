import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 1759_암호 만들기
	 * 
	 * 	[설명]
	 * 	# 암호 규칙
	 * 1. 서로 다른 L개의 알파벳 소문자로 구성
	 * 2. 최소 한개의 모음
	 * 3. 최소 두개의 자음
	 * 4. 알파벳은 증가하는 순서로 배열
	 * 
	 * 사용했을 법한 문자 종류 C가지
	 * 
	 * 가능성 있는 암호를 모두 구하시오
	 * 
	 * 	[입력]
	 * ===> 암호길이L 문자종류C
	 * ===> C개의 문자 ( 공백 구분 )
	 * 
	 * 	[출력]
	 * 가능성 있는 암호 모두 출력
	 * 
	 * 	[풀이방법]
	 * 빠른 방법
	 * 길이 4의 조합 만들어서 조건 만족하는지 확인 
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int L,C;
	static String codeM[], alpha[];
	static boolean selected[];
	static PriorityQueue<Character> code;
	
	static final int MOCNT = 1, JACNT = 2;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 암호길이L 문자종류C
		st = new StringTokenizer(br.readLine().trim());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());		
		
		// 암호에 사용되는 문자 종류
		alpha = br.readLine().trim().split(" ");
		Arrays.sort(alpha);
		//System.out.println(Arrays.toString(alpha));
		selected = new boolean[C];
		
		// 구성된 암호
		code = new PriorityQueue<>();
		codeM = new String[L];
		
		// 암호 만들기
		comb(0,0);		
		
		System.out.println(sb);
	}
	
	private static void comb(int cnt,int start) {
		// 암호가 만들어 졌을 때,
		if(cnt == L) {
			// 조건 만족하면  정렬해서 출력하기
			if(check(codeM)) {
				for(String s : codeM) {
					sb.append(s);
				}
				sb.append("\n");
			}
			return;
		}
		for (int idx = start; idx < C; idx++) {
			codeM[cnt] = alpha[idx];
			comb(cnt + 1, idx + 1);
		}
		
	}
	
	// 암호 조건 만족하는지 확인하는 함수
	private static boolean check(String[] code) {
		int mo=0, ja=0;
		for(int idx = 0; idx<L; idx++) {
			if(code[idx].equals("a") ||code[idx].equals("u") ||code[idx].equals("o") ||code[idx].equals("i") ||code[idx].equals("e"))
				mo++;
			else
				ja++;
		}
		if(mo>=MOCNT && ja>=JACNT)
			return true;
		else
			return false;
	}
}
