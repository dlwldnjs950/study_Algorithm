import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 1735_분수 합
	 * 
	 * 	[설명]
	 * 두 개의 분수가 주어짐
	 * 두 분수의 합을 기약분수 형태로 나타내라
	 * 
	 * 	[입력]
	 * ===> 분수 1 (분자 분모)
	 * ===> 분수 2 (분자 분모)
	 * 
	 * 	[출력]
	 * 두 분수의 합을 분자 분모
	 * 
	 * 	[풀이방법]
	 * 분수의 합 : 
	 * 	분모을 최소공배수로 바꾸고
	 * 	그에 맞춰 분자도 바꾸고
	 * 	더한 다음에
	 * 	기약분수로 만들기 위해 최대공약수로 분자와 분모를 나눈다
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 분수 1
		st = new StringTokenizer(br.readLine().trim());
		int nume1 = Integer.parseInt(st.nextToken());
		int deno1 = Integer.parseInt(st.nextToken());
		
		// 분수 2
		st = new StringTokenizer(br.readLine().trim());
		int nume2 = Integer.parseInt(st.nextToken());
		int deno2 = Integer.parseInt(st.nextToken());
		
		// 분모의 최소공배수
		int resultDeno = lcm(Math.min(deno1, deno2), Math.max(deno1, deno2));
		
		// 맞춰서 분자도 바꿔주기
		nume1 = nume1 * (resultDeno/deno1);
		nume2 = nume2 * (resultDeno/deno2);
		
		// 분수 더하기
		int resultNume = nume1+nume2;
		
		// 더한 분수를 최대공약수로 나눠 기약분수로 만들기
		int gcd = gcd(Math.min(resultNume, resultDeno), Math.max(resultNume, resultDeno));
		
		sb.append(resultNume/gcd).append(" ").append(resultDeno/gcd);
		System.out.println(sb);
	}

	static int gcd(int a, int b) {
		while (b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

	static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}
}
