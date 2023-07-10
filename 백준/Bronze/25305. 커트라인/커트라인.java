import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] nk = br.readLine().split(" ");
		String[] score = br.readLine().split(" ");
		Arrays.sort(score, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				int oo1 = Integer.parseInt(o1);
				int oo2 = Integer.parseInt(o2);
				return oo2 - oo1;
			}
		});
		System.out.println(score[Integer.parseInt(nk[1]) - 1]);
	}
}
