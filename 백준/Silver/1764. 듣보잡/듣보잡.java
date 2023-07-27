import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] nums = br.readLine().split(" ");
		Map<String, Integer> human = new HashMap<>(); // 중복을 제거할 자료구조
		List<String> both = new ArrayList<>(); // 듣도 보도 못한 사람 목록
		for (int i = 0; i < Integer.parseInt(nums[0]) + Integer.parseInt(nums[1]); i++) {
			String tmp = br.readLine();
			if (human.put(tmp, human.getOrDefault(tmp, 0) + 1) != null) {
				both.add(tmp);
			}
		}
		Collections.sort(both);
		System.out.println(both.size());
		for (String str : both) {
			System.out.println(str);
		}

	}
}
