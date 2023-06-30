import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String[] nums = br.readLine().split(" ");
			int[] num = new int[3];
			for (int i = 0; i < 3; i++) {
				num[i] = Integer.parseInt(nums[i]);
			}
			Arrays.sort(num);
			if (num[0] + num[1] + num[2] == 0)
				break;
			else if (num[0] + num[1] <= num[2]) {
				System.out.println("Invalid");
			} else {
				if (num[0] == num[1] && num[1] == num[2])
					System.out.println("Equilateral");
				else if (num[0] == num[1] || num[1] == num[2])
					System.out.println("Isosceles");
				else
					System.out.println("Scalene");
			}
		}
	}
}
