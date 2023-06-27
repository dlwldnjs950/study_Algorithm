import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		int cnt = 0;
		for (int tc = 0; tc < T; tc++) {
			int tmp = sc.nextInt();
			if (is_prime(tmp))
				cnt++;
		}
		System.out.println(cnt);
	}

	public static boolean is_prime(int num) {
		if(num==1)
			return false;
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}
}
