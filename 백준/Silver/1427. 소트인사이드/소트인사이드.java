import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String num = br.readLine();

		List<Integer> nums = new ArrayList<>();
		for (int i = 0; i < num.length(); i++) {
			nums.add(num.charAt(i) - '0');
		}
		nums.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		StringBuilder sb = new StringBuilder();
		for(int i : nums) {
			sb.append(i);
		}
		System.out.println(sb);
	}
}
