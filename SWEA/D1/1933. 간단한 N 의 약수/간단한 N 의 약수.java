import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		// 완전 탐색
		// 반복문에서 i*i<N인 동안 확인한다
		// 찾은 약수를 저장하기 위한 List 변수 선언 (약수가 몇개일지 모름)
		List<Integer> divisor = new ArrayList<>();
		for (int num = 1; num * num <= N; num++) {
			if (N % num == 0) { // 나눈 나머지가 0이면 약수
				divisor.add(num);
				if (num * num != N)	// 제곱수가 아닐때만
					divisor.add(N / num);	// 나눈 몫도 약수이므로
			}
		}
		// 오플차순 출력을 위한 정렬
		Collections.sort(divisor);
		StringBuilder sb = new StringBuilder();
		for(int idx=0; idx<divisor.size(); idx++) {
			sb.append(divisor.get(idx)).append(" ");
		}
		System.out.println(sb);
	}

}
