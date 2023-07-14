import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		List<String> word = new ArrayList<String>();

		for (int tc = 0; tc < T; tc++) {
			String tmp = br.readLine();
			if (!word.contains(tmp)) {
				word.add(tmp);
			}
		}
		word.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() == o2.length())
					return o1.compareTo(o2);
				else
					return o1.length() - o2.length();
			}
		});
		for (String str : word) {
			System.out.println(str);
		}
	}
}
