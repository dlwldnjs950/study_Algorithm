import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int[] coin = { 25, 10, 5, 1 };
		int T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int C = sc.nextInt();
			int[] cnt = new int[4];
			for (int i = 0; i < 4; i++) {
				cnt[i] = C / coin[i];
				C %= coin[i];
			}
			for (int i = 0; i < 4; i++) {
				System.out.print(cnt[i]+" ");
			}
			System.out.println();
		}
	}
}
