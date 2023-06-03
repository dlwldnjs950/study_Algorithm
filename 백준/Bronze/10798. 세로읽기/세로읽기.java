import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		List<String> str = new ArrayList<>();
		int max = 0;
		for (int i = 0; i < 5; i++) {
			String tmp = sc.next();
			if (tmp.length() > max)
				max = tmp.length();
			str.add(tmp);
		}
		for (int i = 0; i < max; i++) { // charAt
			for (int j = 0; j < 5; j++) { // 각 문자열
				String tmp = str.get(j);
				if (i >= tmp.length())
					continue;
				System.out.print(tmp.charAt(i));
			}
		}
	}
}
