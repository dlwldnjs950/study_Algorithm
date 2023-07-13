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
		List<Point2> Point2 = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			String[] nums = br.readLine().split(" ");
			Point2.add(new Point2(Integer.parseInt(nums[0]), Integer.parseInt(nums[1])));
		}
		Point2.sort(new Comparator<Point2>() {
			@Override
			public int compare(Point2 o1, Point2 o2) {
				if (o1.y != o2.y)
					return o1.y - o2.y;
				else
					return o1.x - o2.x;
			}
		});
		for (int i = 0; i < n; i++) {
			System.out.println(Point2.get(i).x+" "+Point2.get(i).y);
		}
	}
}

class Point2 {
	int x;
	int y;

	public Point2(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
