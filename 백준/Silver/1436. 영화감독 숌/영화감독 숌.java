import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		int cnt = 0;
		int tc = 666;
		while(true) {
			String tmp = tc + "";
			if (tmp.contains("666"))
				cnt++;
			if (cnt == T) {
				System.out.println(tc);
				return;
			}
			tc++;
		}
	}
}
