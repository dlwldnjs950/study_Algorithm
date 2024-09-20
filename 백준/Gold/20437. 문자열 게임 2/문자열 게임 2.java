import java.io.*;
import java.util.*;

public class Main {

	/**/

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 각 문자의 개수가 K개보다 많은 경우만 확인하면 되고
		// 각 문자의 인덱스를 차례대로 확인하면 좋으니까
		// Map과 List 활용
		
		int tc = Integer.parseInt(br.readLine().trim());
		
		for(int testCase = 0; testCase < tc; testCase++) {
		
			String wStr = br.readLine().trim();
			int kNum = Integer.parseInt(br.readLine().trim());
			
			Map<Character, List<Integer>> charMap = new HashMap<>();
			
			for(int idx = 0; idx < wStr.length(); idx++) {
				char tmp = wStr.charAt(idx);
				charMap.putIfAbsent(tmp, new ArrayList<>());
				charMap.get(tmp).add(idx);
			}
			
			int maxLength = 0;
			int minLength = Integer.MAX_VALUE;
			
			for(List<Integer> indexes : charMap.values()) {
				
				// 기준 개수 이상 존재하는 문자의 인덱스만 확인하면 된다.
				if(indexes.size() >= kNum) {
					for(int idx = 0; idx + kNum -1 < indexes.size(); idx++) {
						int newLength = indexes.get(idx + kNum -1) - indexes.get(idx) + 1;
						
						maxLength = Math.max(maxLength, newLength);
						minLength = Math.min(newLength, minLength);
					}
				}
			}
			
			if(maxLength == 0 && minLength == Integer.MAX_VALUE) {
				sb.append(-1);
			}else {
				sb.append(minLength).append(" ").append(maxLength);
			}
			sb.append("\n");
			
		}

		System.out.println(sb);

	}

}
