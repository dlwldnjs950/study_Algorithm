import java.math.BigInteger;
import java.util.Scanner;

public class Main {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		BigInteger cnt = new BigInteger("2").pow(N).subtract(BigInteger.ONE);
		sb.append(cnt).append('\n');		
		if (N <= 20) {
			hanoi(N, 1, 2, 3);
		}
		System.out.println(sb);
	}

	// N은 depth
	static void hanoi(int depth, int start, int mid, int end) {
		if (depth == 1) {
			sb.append(start).append(" ").append(end).append('\n');
			return;
		} else {
			hanoi(depth - 1, start, end, mid);
			sb.append(start).append(" ").append(end).append('\n');
			hanoi(depth - 1, mid, start, end);
		}
	}
}
