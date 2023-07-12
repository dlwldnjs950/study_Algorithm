import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		List<Point> point = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			String[] nums = br.readLine().split(" ");
			point.add(new Point(Integer.parseInt(nums[0]), Integer.parseInt(nums[1])));
		}
		point.sort(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if (o1.x != o2.x)
					return o1.x - o2.x;
				else
					return o1.y - o2.y;
			}
		});
		for (int i = 0; i < n; i++) {
			System.out.println(point.get(i).x+" "+point.get(i).y);
		}
	}
}
class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
