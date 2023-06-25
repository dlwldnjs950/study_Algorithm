import java.util.Arrays;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		if (T == 1) {
			System.out.println(0);
			return;
		}
		int[] x = new int[T];
		int[] y = new int[T];

		for (int tc = 0; tc < T; tc++) {
			x[tc] = sc.nextInt();
			y[tc] = sc.nextInt();
		}
		Arrays.sort(x);
		Arrays.sort(y);
		System.out.println((x[T-1]-x[0])*(y[T-1]-y[0]));
	}
}