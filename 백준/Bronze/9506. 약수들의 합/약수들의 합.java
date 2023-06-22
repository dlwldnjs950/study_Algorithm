import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			int n = sc.nextInt();
			if(n==-1)
				break;

			ArrayList<Integer> divisor = new ArrayList<>();
			int sum = 0;

			for (int i = 1; i * i <= n; i++) {
				if (n % i == 0) {
					divisor.add(i);
					sum += i;
					if (i * i != n) {
						divisor.add(n / i);
						sum += (n / i);
					}
				}
			}
			sum-=n;
			if (sum == n) {
				divisor.sort(new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						return o1 - o2;
					}
				});
				System.out.print(n+" = ");
				for(int i=0; i<divisor.size()-2;i++) {
					System.out.print(divisor.get(i)+" + ");
				}
				System.out.println(divisor.get(divisor.size()-2));
			}else {
				System.out.println(n+" is NOT perfect.");
			}
		}
	}
}