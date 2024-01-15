import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 가장 뒤 두 자리를 적절히 바꿔서 N을 F로 나누어 떨어지게 만들자
	 * 가능한 것이 여러가지면 가능한 작게 만들자
	 * 
	 * N이 275, F가 5면 답은 00
	 * 
	 * 최고자리 수 ~ 백의 자리 까지의 수에서 가장 가까운 F의 배수
	 * 
	 * N / 100 * 100 / F (올림) * F
	 * */
		
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Tomato {
	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine().trim());
		int F = Integer.parseInt(br.readLine().trim());

		int result = (int) Math.ceil(((double) (N / 100) * 100) / F) * F;
//		result *= F;

		result %= 100;
		if (result < 10)
			sb.append(0);
		sb.append(result);

		System.out.println(sb);

	}

}
