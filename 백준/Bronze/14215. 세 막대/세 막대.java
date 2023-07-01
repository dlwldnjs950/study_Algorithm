import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] nums = br.readLine().split(" ");
		int[] num = new int[3];
		for(int i=0; i<3;i++) {
			num[i] = Integer.parseInt(nums[i]);
		}
		Arrays.sort(num);
		if(num[0]+num[1]<=num[2]) {
			System.out.println((num[0]+num[1])*2-1);
		}else {
			System.out.println(num[0]+num[1]+num[2]);
		}
	}
}
