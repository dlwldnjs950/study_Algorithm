import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		int N=sc.nextInt();

		for(int i=0; 3*i<=N; i++) {
			for(int j=0; 5*j<=N;j++) {
				if((5*j+3*i)==N) {
					System.out.println(i+j);
					return;
				}
			}
		}
		System.out.println(-1);
	}

}