import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		long N = sc.nextLong();
		int B = sc.nextInt();
		String result = "";
		while (N > 0) {
			long extra = N % B;
			char tmp = 0;
			if (extra >= 0 && extra <= 9)
				tmp = (char) (extra + '0');
			else
				tmp = (char) (extra + 55);
			result = tmp + result;
			N = N / B;
		}
		System.out.println(result);
	}
}