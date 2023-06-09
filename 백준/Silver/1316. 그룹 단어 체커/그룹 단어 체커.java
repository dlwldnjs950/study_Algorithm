import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		int cnt = 0;
		for (int tc = 0; tc < T; tc++) {
			String str = sc.next();
			String contain = "";
			boolean chk = true;
			for (int i = 0; i < str.length(); i++) {
				String tmp = str.charAt(i) + "";
				if (!contain.contains(tmp)) // 나온적 없으면 contain에 포함
					contain = tmp + contain;
				else if (contain.charAt(0) == str.charAt(i)) { // 지금 확인하고있는 문자면 그냥 지나치면 되고
					continue;
				} else { // 그러다 앞에 나왔던 문자가 다시 나오면 더 확인 안함
					chk = false;
					break;
				}
			}
			if (chk) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}