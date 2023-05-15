import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int n = (int) Math.ceil((N - 4) / 4);

		for (int i = 0; i < n; i++) {
			System.out.print("long ");
		}
		System.out.println("long int");
	}
}