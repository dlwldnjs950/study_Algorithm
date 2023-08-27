import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// K
		int K = Integer.parseInt(br.readLine().trim());

		Stack<Integer> numbers = new Stack<>();
		int sum=0;
		for(int idx =0; idx <K; idx++) {
			int input = Integer.parseInt(br.readLine().trim());
			if(input == 0)
				sum-=numbers.pop();
			else
				sum+=numbers.push(input);
		}
		sb.append(sum);
		System.out.println(sb);
	}

}
