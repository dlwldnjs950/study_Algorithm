import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String num = br.readLine();
		int N = Integer.parseInt(num);
        int result = 0;
		for (int i = (N - num.length() * 9); i < N; i++) {
			int tmp = i;
			int sum = 0;
			while (tmp != 0) {
				sum += tmp % 10;
				tmp /= 10;
			}
			if(sum+i==N) {
				result = i;
				break;
			}			
		} 
		System.out.println(result);
	} 
}
