import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean[][] paper = new boolean[101][101];

		int T = sc.nextInt();
		int cnt = 0;
		for (int tc = 0; tc < T; tc++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			for (int i = x; i < x + 10; i++) {
				for (int j = y; j < y + 10; j++) {
					if (!paper[i][j]) {
						paper[i][j] = true;
						cnt++;
					}
				}
			}
		}
		System.out.println(cnt);
	}
}