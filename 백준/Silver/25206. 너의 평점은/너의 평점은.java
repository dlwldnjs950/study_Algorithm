import java.util.HashMap;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] alpha = { "A+", "A0", "B+", "B0", "C+", "C0", "D+", "D0"};
		HashMap<String, Float> grade = new HashMap<>();
		for (int i = 0; i < alpha.length; i++) {
			grade.put(alpha[i], (float) (4.5 - (0.5) * i));
		}
		grade.put("F", 0.0f);

		float sum = 0.0f;
		float sumHak = 0.0f;
		for (int tc = 0; tc < 20; tc++) {
			sc.next();
			float hak = sc.nextFloat();
			String grd = sc.next();
			if(grd.equals("P")) continue;
			sum += hak * grade.get(grd);
			sumHak += hak;
		}
		System.out.printf("%.6f",sum/sumHak);
	}
}