import java.util.Scanner;

public class Main {
	/**
	 * 순열 : 서로 다른 것들 중 몇개를 뽑아서 한 줄로 나열하는 것
	 * 		nPr
	 * 
	 * */
	
	// 리스트 요소 개수
	static int N;
	// 뽑았는지 표시하는 변수
	static boolean[] isSelected;
	// 뽑을 개수 M
	static int M;
	// 뽑은 M개의 요소 저장 변수
	static int[] numbers;
	
	// 출력
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 리스트 요소 개수
		N = sc.nextInt();
		// 서로 다른 것들 리스트 저장 변수
//		List<Integer> numList = new ArrayList<>();

		// 뽑았는지 표시하는 변수
		isSelected = new boolean[N + 1]; // 1~N까지의 수가 리스트인 경우 인덱스 값으로 바로 사용하기 위함이다.

		// 뽑을 개수 M
		M = sc.nextInt();
		// 뽑은 M개의 요소 저장 변수
		numbers = new int[M];

		Perm(0);
		System.out.println(sb);
	}
	
	static void Perm(int cnt) {
		if(cnt==M) {
			for(int num : numbers)
				sb.append(num).append(" ");
			sb.append("\n");
		}else {
			for (int i = 1; i <= N; i++) {
				if (isSelected[i])
					continue;
				numbers[cnt] = i;
				isSelected[i] = true;
				Perm(cnt + 1);
				isSelected[i] = false;
			}
		}
	}

}
