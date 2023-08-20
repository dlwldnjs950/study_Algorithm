import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 2485_가로수
	 * 
	 * 	[설명]
	 * 가로수 위치는 기준점으로부터 떨어져있는거리
	 * 모든 가로수가 같은 간격이 되도록 새로 심어야하는 가로수의 최소수?
	 * (단, 추가 나무는 기존 나무 사이에만 심을 수 있다)
	 * 
	 * 	[풀이방법]
	 * 시작 가로수 앞은 신경쓰지 않는다
	 * 간격들의 최대공약수를 구해서
	 * 심어져 있지 않은 자리를 카운트
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 가로수 개수 N
		int N = Integer.parseInt(br.readLine().trim());

		// 나무 높이
		long[] tree = new long[N];
		// 나무 사이 간격
		long[] distance = new long[N - 1];
		for (int idx = 0; idx < N; idx++) {
			tree[idx] = Long.parseLong(br.readLine().trim());
			if (idx > 0)
				distance[idx - 1] = tree[idx] - tree[idx-1];
		}
				
		// 각 거리의 최대 공약수를 구한다.
		long gcd = distance[0];
		for(int idx = 1; idx<N-1; idx++) {
			gcd = gcd(gcd, distance[idx]);
		}
		
		// 나무의 시작과 끝에 심을 수 있는 최대 나무 수에서 심어진 나무 수 빼기
		sb.append((tree[N-1]-tree[0])/gcd+1-tree.length);
		
		System.out.println(sb);
	}

	private static long gcd(long A, long B) {
		long a = Math.min(A, B);
		long b = Math.max(A, B);
		
		while (b != 0) {
			long r = a % b;
			a = b;
			b = r;
		}
		return a;
	}
}
