import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str = sc.next();
		int cnt = 0;
		for (int i = 0; i < str.length(); i++) {
			switch (str.charAt(i)) {
			case 'c':
				if (i + 1 < str.length()) {
					if (str.charAt(i + 1) == '-' || str.charAt(i + 1) == '=')
					i++;
				}
				break;
			case 'd':
				if (i + 1 < str.length()) {
					if (str.charAt(i + 1) == '-') {
						i++;
					} else if (str.charAt(i + 1) == 'z') {
						if (i + 2 < str.length() && str.charAt(i + 2) == '=') {
							i += 2;
						}
					}
				}
				break;
			case 'l':
				if (i + 1 < str.length() && str.charAt(i + 1) == 'j') {
					i++;
				}
				break;
			case 'n':
				if (i + 1 < str.length() && str.charAt(i + 1) == 'j') {
					i++;
				}
				break;
			case 's':
				if (i + 1 < str.length() && str.charAt(i + 1) == '=') {
					i++;
				}
				break;
			case 'z':
				if (i + 1 < str.length() && str.charAt(i + 1) == '=') {
					i++;
				}
				break;
			default:
				break;
			}
			cnt++;
		}
		System.out.println(cnt);
	}
}
