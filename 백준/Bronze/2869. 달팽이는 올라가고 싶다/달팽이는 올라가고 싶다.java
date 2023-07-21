import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		double A=0,B=0,V=0;
		Scanner sc = new Scanner(System.in);
		
		A=sc.nextInt();
		B=sc.nextInt();
		V=sc.nextInt();
		int day=(int) Math.ceil((V-B)/(A-B));
		System.out.println(day);

	}

}