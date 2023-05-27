import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 바구니 개수
		int[] box = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			box[i] = i;
		}
		int M = sc.nextInt();
		for (int tc = 0; tc < M; tc++) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			while (true) {
				if(i>=j)
					break;
				int temp = box[i];
				box[i] = box[j];
				box[j] = temp;
				i++; j--;
			}
		}
		for (int i = 1; i <= N; i++) {
			System.out.print(box[i] + " ");
		}
	}
}
