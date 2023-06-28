import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int M = sc.nextInt();
		int N = sc.nextInt();
		int sum = 0;
		int min = Integer.MAX_VALUE;
		for (int tc = M; tc <= N; tc++) {
			if (is_prime(tc)) {
				sum += tc;
				if (min > tc)
					min = tc;
			}
		}
		if (sum == 0)
			System.out.println(-1);
		else {
			System.out.println(sum + "\n" + min);
		}
	}

	public static boolean is_prime(int num) {
		if (num == 1)
			return false;
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}
}