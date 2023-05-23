import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] box = new int[N];
		int M = sc.nextInt();
		for (int i = 0; i < N; i++) {
			box[i] = i + 1;
		}
		for (int tc = 0; tc < M; tc++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int temp = box[a - 1];
			box[a - 1] = box[b - 1];
			box[b - 1] = temp;
		}
		for (int i = 0; i < N; i++) {
			System.out.print(box[i] + " ");
		}
	}
}
