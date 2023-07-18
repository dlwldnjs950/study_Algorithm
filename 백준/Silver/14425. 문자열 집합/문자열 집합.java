import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		List<String> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(sc.next());
		}

		int cnt = 0;
		for (int i = 0; i < M; i++) {
			if (list.contains(sc.next()))
				cnt++;
		}
		System.out.println(cnt);
	}
}
