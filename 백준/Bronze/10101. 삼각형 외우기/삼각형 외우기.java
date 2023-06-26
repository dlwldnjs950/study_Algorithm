import java.util.Arrays;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int[] gak = new int[3]; 
		int sum=0;
		for (int tc = 0; tc < 3; tc++) {
			gak[tc] = sc.nextInt();
			sum+=gak[tc];
		}
		if(sum!=180)
			System.out.println("Error");
		else {
			Arrays.sort(gak);
			if(gak[0]==gak[1] && gak[1]==gak[2])
				System.out.println("Equilateral");
			else if(gak[0]==gak[1] || gak[1]==gak[2] || gak[0]==gak[2])
				System.out.println("Isosceles");
			else
				System.out.println("Scalene");
		}
	}
}
