import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String N = sc.next();
		int B = sc.nextInt();
		long result = 0l;
		for (int i = 0; i < N.length(); i++) {
			int idx = N.length() - (1+i);
			int num = 0;
			char n = N.charAt(idx);
			if (n >= '0' && n <= '9')
				num = n - '0';
			else
				num = n - 55;
			result+= num * Math.pow(B, i);
		}
		System.out.println(result);
	}
}