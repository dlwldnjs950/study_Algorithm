import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// N
		int N = Integer.parseInt(br.readLine().trim());

		// set으로 관리
		Set<String> input = new HashSet<>();
		int cnt = 0;
		for(int idx =0; idx <N; idx++) {
			String tmp = br.readLine().trim();
			
			if(tmp.equals("ENTER")) {
				cnt += input.size();	// 지금까지 횟수 누적하고
				input.clear();	// 초기화
				continue;
			}
			input.add(tmp);
		}
		cnt+= input.size();
		
		sb.append(cnt);
		System.out.println(sb);
	}

}
