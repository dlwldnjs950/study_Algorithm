import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		List<Member> member = new ArrayList<>();
		for (int tc = 0; tc < T; tc++) {
			int age = sc.nextInt();
			String name = sc.next();
			member.add(new Member(age, name));
		}
		member.sort(new Comparator<Member>() {
			@Override
			public int compare(Member o1, Member o2) {
				if (o1.age != o2.age)
					return o1.age - o2.age;
				else
					return 0;
			}
		});
		for (Member mem : member) {
			System.out.println(mem.age + " " + mem.name);
		}
	}
}

class Member {
	int age;
	String name;

	Member(int a, String n) {
		this.age = a;
		this.name = n;
	}
}