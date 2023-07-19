import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		Set<String> emList = new HashSet<>();
		for (int i = 0; i < n; i++) {
			String[] tmp = br.readLine().split(" ");
			if (tmp[1].equals("enter")) {
				emList.add(tmp[0]);
			} else if (tmp[1].equals("leave")) {
				emList.remove(tmp[0]);
			}
		}

		// 그냥 요소를 하나씩 출력할 때
		Iterator iter = emList.iterator();
		List<String> remain = new ArrayList<>(emList);
		Collections.sort(remain, (o1, o2) -> o2.compareTo(o1));
		/*
		 * ArrayList<Integer> list = new ArrayList<>(set); // set을 ArrayList로 변경
		 * 
		 * Collections.sort(list, (o1,o2) -> o2 - o1); // 내림차순 정렬
		 * System.out.println(list.toString()); // [3, 2, 1]
		 * 
		 * Collections.sort(list, (o1,o2) -> o1 - o2); // 오름차순 정렬
		 * System.out.println(list.toString()); // [1, 2, 3]
		 */
		for(String str:remain) {
			System.out.println(str);
		}
	}
}
