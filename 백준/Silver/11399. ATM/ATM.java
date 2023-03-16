import java.util.Arrays;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] ns = new int[N];
		
		for (int i = 0; i < ns.length; i++) {
			ns[i]=sc.nextInt();			
		}
		Arrays.sort(ns);
		int sum=0;
		for (int i = N; i > 0; i--) {
			sum+=ns[N-i]*i;			
		}
		System.out.println(sum);

	}

}