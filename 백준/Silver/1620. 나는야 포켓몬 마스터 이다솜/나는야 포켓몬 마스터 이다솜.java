import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] nums = br.readLine().split(" ");
		int[] num = { Integer.parseInt(nums[0]), Integer.parseInt(nums[1]) };

		HashMap<String, String> name = new HashMap<>();
		HashMap<String, String> number = new HashMap<>();
		for (int i = 1; i <= num[0]; i++) {
			String tmp = br.readLine();
			name.put(tmp, i+"");
			number.put(i+"", tmp);
		}
		for(int i=0; i<num[1]; i++) {
			String tmp = br.readLine();
			if(name.containsKey(tmp))
				System.out.println(name.get(tmp));
			else if(number.containsKey(tmp))
				System.out.println(number.get(tmp));
		}
	}
}
