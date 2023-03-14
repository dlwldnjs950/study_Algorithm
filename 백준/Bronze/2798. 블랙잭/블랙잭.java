import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		int M = in.nextInt();
 
		int[] ns = new int[N];
 
		for (int i = 0; i < N; i++) {
			ns[i] = in.nextInt();
		}
		
		int result = search(ns, N, M);
		System.out.println(result);
	}
	
	static int search(int[] ns, int N, int M) {
		int result = 0;
		for (int i = 0; i < N - 2; i++) {
			for (int j = i + 1; j < N - 1; j++) {
				for (int k = j + 1; k < N; k++) {
					int temp = ns[i] + ns[j] + ns[k];
					
					if (M == temp) {	
						return temp;
					}
					 
					if(result < temp && temp < M) {
						result = temp;
					}
				}
			}
		}
		return result;
	}

}
