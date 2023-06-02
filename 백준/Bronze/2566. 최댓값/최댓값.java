import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int max = 0;
		int x=1; int y=1;    //배열의 값이 모두 0인 경우
		for(int i=1;i<=9;i++) {
			for(int j=1;j<=9;j++) {
				int tmp = sc.nextInt();
				if(tmp>max) {
					max = tmp;
					x=i;y=j;
				}
			}
		}
		System.out.println(max);
		System.out.println(x+" "+y);
	}
}
