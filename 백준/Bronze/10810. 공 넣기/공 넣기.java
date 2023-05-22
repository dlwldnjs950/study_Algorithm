import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] box = new int[N];
		int M = sc.nextInt();
		for (int tc = 0; tc < M; tc++) {
			int i=sc.nextInt();
			int j=sc.nextInt();
			int k=sc.nextInt();
			for(int a=i-1;a<j;a++) {
				box[a]=k;
			}
		}
		for (int i = 0; i < N; i++) {
			System.out.print(box[i]+" ");
		
        }
	}
}
