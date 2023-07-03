import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		List<Integer> num = new ArrayList<Integer>();
		
		while(st.hasMoreTokens()) {
			num.add(Integer.parseInt(st.nextToken()));
		}
		
		for(int x=-999; x<=999; x++) {
			for(int y=-999;y<=999;y++) {
				if(num.get(0)*x+num.get(1)*y==num.get(2) 
						&& num.get(3)*x+num.get(4)*y==num.get(5)) {
					System.out.println(x+" "+y);
					return;
				}
			}
		}
	}
}
