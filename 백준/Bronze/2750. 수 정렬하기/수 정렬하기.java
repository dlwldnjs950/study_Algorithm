import java.util.Arrays;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		int[] num = new int[T];
		for (int tc = 0; tc < T; tc++) {
			num[tc] = sc.nextInt();
		}
		Arrays.sort(num);
		for(int i=0 ;i<T; i++) {
			System.out.println(num[i]);
		}
	}
}
