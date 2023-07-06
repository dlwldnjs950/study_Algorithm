import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		List<Integer> num = new ArrayList<>();
		for (int tc = 0; tc < T; tc++) {
			num.add(sc.nextInt());
		}
		Collections.sort(num);
		
		StringBuilder sb = new StringBuilder();
		for(int value : num) {
			sb.append(value).append('\n');
		}
		System.out.println(sb);
	}
}
