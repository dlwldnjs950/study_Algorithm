import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {
			String VPS = sc.next();
			Stack<String> stack = new Stack<>();

			for (int i = 0; i < VPS.length(); i++) {
				if (VPS.charAt(i) == '(') {
					stack.push(VPS.charAt(i) + "");
				} else if (VPS.charAt(i) == ')') {
					if (!(stack.empty()) && stack.peek().equals("("))// 짝이 맞으면
						stack.pop();
					else {
						stack.push(VPS.charAt(i) + "");
					}
				}

			}
			if (stack.empty())
				System.out.println("YES");
			else
				System.out.println("NO");

		}

	}

}