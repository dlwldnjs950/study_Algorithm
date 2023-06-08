import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str = sc.next();
		for(int i=0;i<=str.length()/2;i++) {
			if(str.charAt(i)!=str.charAt(str.length()-(i+1))) {
				System.out.println("0");
				return;
			}
		}
		System.out.println(1);
	}
}