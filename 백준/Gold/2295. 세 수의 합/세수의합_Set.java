import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;

	static int numCnt, numbers[];

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));

		numCnt = Integer.parseInt(br.readLine().trim());
		numbers = new int[numCnt];

		for (int idx = 0; idx < numCnt; idx++) {
			numbers[idx] = Integer.parseInt(br.readLine().trim());
		}
		Arrays.sort(numbers);

		int maxSum = 0;
		
		// e1 + e2 + e3 = sum
		// e1 + e2 = sum - e3;로 바꿔서 생각한다
		Set<Integer> twoSums = new HashSet<>();
		for(int element1 = 0; element1 < numCnt; element1++) {
			// 같은 원소도 가능
			for(int element2 = element1; element2 < numCnt; element2++) {
				twoSums.add(numbers[element1] + numbers[element2]);
			}
		}
		
		for(int element3 = 0; element3 < numCnt; element3++) {
			for(int sumElement = element3; sumElement<numCnt; sumElement++) {
				int diff = numbers[sumElement] - numbers[element3];
				
				if(twoSums.contains(diff))
					maxSum = Math.max(maxSum, numbers[sumElement]);
			}
		}
		
		System.out.println(maxSum);
	}
}
