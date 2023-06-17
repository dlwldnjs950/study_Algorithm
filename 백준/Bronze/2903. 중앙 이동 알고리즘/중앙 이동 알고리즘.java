import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		T = (int) Math.pow(2, T) + 1;
		T = (int) Math.pow(T, 2);
		System.out.println(T);
	}
}