import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] arr = new int[4];
		for(int i=0;i<4;i++) {
			arr[i]=sc.nextInt();
		}
		arr[2]-=arr[0];
		arr[3]-=arr[1];
		int min=Integer.MAX_VALUE;
		for(int i=0;i<4;i++) {
			if(arr[i]<min) min=arr[i];
		}
		System.out.println(min);
	}
}