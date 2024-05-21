import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 투포인터인 이유 정렬 후 양 끝을 가리키자
	 * 
	 * 양 끝값의 합의 중간값이 0보다 작으면 오른쪽을 옮기고
	 * 					0보다 크면 왼쪽을 옮기..
	 * 면 알알 산산 조합을 찾을 수는 있겠다.
	 * */

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int numCnt = Integer.parseInt(br.readLine().trim());
		
		// 용액 정보 저장
		int beakers[] = new int[numCnt];
		st = new StringTokenizer(br.readLine().trim());
		for(int idx=0; idx<numCnt; idx++) {
			beakers[idx]= Integer.parseInt(st.nextToken());
		}
		
		// 정렬
		Arrays.sort(beakers);

		int left = 0;
		int right = numCnt-1;

		// 가장 가까운 값
		int min = Integer.MAX_VALUE;
		// 그때의 left right
		int minPointerValue[] = new int[2];

		while (left < right) {

			int value = (beakers[left] + beakers[right]);

			// 더 최솟값이라면 갱신
			if (Math.abs(value) < min) {
				minPointerValue[0] = beakers[left];
				minPointerValue[1] = beakers[right];
				min = Math.abs(value);
				if (value == 0)
					break;
			}

			if (value < 0)
				left++;
			else if (value > 0)
				right--;

		}
		sb.append(minPointerValue[0]).append(" ").append(minPointerValue[1]);
		
		System.out.println(sb);

	}

}
