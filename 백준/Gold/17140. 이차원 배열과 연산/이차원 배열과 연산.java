import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static List<List<Integer>> origArray;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		int tRow = Integer.parseInt(st.nextToken()) - 1;
		int tCol = Integer.parseInt(st.nextToken()) - 1;
		int tValue = Integer.parseInt(st.nextToken());

		// 행 기준 배열
		origArray = new ArrayList<>();

		for (int rIdx = 0; rIdx < 3; rIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			origArray.add(new ArrayList<>());

			for (int cIdx = 0; cIdx < 3; cIdx++) {
				int value = Integer.parseInt(st.nextToken());

				origArray.get(rIdx).add(value);
			}
		}

		int time = 0;

		while (time <= 100) {
			if (origArray.size() > tRow && origArray.get(tRow).size() > tCol && origArray.get(tRow).get(tCol) == tValue) {
				System.out.println(time);
				return;
			}

			int height = origArray.size();
			int width = origArray.get(0).size();

			if (height >= width) {
				// R연산
				operationR();
			} else {
				// C연산
				operationC();
			}

			time++;
		}

		System.out.println(-1);

	}

	private static void operationR() {

		int maxColLen = 0;
		List<List<Integer>> newArray = new ArrayList<>();
		for (List<Integer> rowArr : origArray) {

			// 개수 알기
			Map<Integer, Integer> cnt = new HashMap<>();
			List<Integer> numbers = new ArrayList<>();
			
			for (int value : rowArr) {
				if (value == 0) continue;  // 0은 세지 않음
				
				if (cnt.containsKey(value)) {
					cnt.put(value, cnt.get(value) + 1);
				} else {
					numbers.add(value);
					cnt.put(value, 1);
				}
			}
			
			// 정렬하기
			Collections.sort(numbers, new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					if (cnt.get(o1) != cnt.get(o2))
						return cnt.get(o1) - cnt.get(o2);
					else
						return o1.compareTo(o2);
				}
			});

			// 배열 갱신하기
			List<Integer> newRowArr = new ArrayList<>();
			for(int value : numbers) {
				newRowArr.add(value);
				newRowArr.add(cnt.get(value));
			}
			maxColLen = Math.max(maxColLen, newRowArr.size());
			
			newArray.add(newRowArr);
		}
		
		// 0으로 열 길이 맞추기
		for(int idx = 0; idx < newArray.size(); idx++) {
			List<Integer> rowArr = newArray.get(idx);
			while(rowArr.size() < maxColLen) {
				rowArr.add(0);
			}
			// System.out.println(rowArr);
		}

		origArray = newArray;

	}

	private static void operationC() {
		
		origArray = rotateArray(origArray);
		operationR();
		origArray = rotateArray(origArray);
	}

	private static List<List<Integer>> rotateArray(List<List<Integer>> targetArray) {
		List<List<Integer>> newArray = new ArrayList<>();
		
		for(int cIdx = 0; cIdx < targetArray.get(0).size(); cIdx++) {
			
			List<Integer> rowArr = new ArrayList<>();
			
			for(int rIdx = 0; rIdx < targetArray.size(); rIdx++) {
				rowArr.add(targetArray.get(rIdx).get(cIdx));
			}
			//System.out.println(rowArr.toString());
			newArray.add(rowArr);
		}
		
		return newArray;
	}
}
