import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	/**
	 * 	[ 설명 ]
	 *  
	 * 	[ 입력 ]
	 * ===> Text
	 * ===> Pattern
	 * 
	 * 	[ 출력 ]
	 * 첫째줄 : Text의 중간에 Pattern이 몇번 나타나는지
	 * 둘째줄 : Pattern이 나타나는 위치 (공백 구분)
	 * 
	 * 	[풀이 방법]
	 * KMP 알고리즘
	 * 
	 */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 텍스트
		String text = br.readLine();

		// 패턴
		String pattern = br.readLine();

		// (패턴 내) 부분 일치 테이블 배열
		// 매칭이 실패했을 때, 패턴 포인터가 돌아갈 곳
		// partition 배열
		//	: 패턴의 0번째 인덱스를 제외한 각 인덱스마다 맨 앞부터 해당 인덱스까지의 부분 문자열 중 접두사와 접미사가 일치하는 최대 길이
		int[] partition = new int[pattern.length()];
		//String copyP = new String(pattern);
		
		// 패턴 문자열 문자열 두개
		// 1번 문자부터 확인
		// sidx가 교재의 i
		int pidx = 0;
		for (int sidx = 1; sidx < pattern.length(); sidx++) {
			
			while(pattern.charAt(sidx) != pattern.charAt(pidx) && pidx > 0) {
				pidx = partition[pidx -1];
			}
			
			// 같으면 pidx 증가
			if(pattern.charAt(sidx) == pattern.charAt(pidx)) {
				pidx++;
				partition[sidx] = pidx;
			}
		}
		
		//System.out.println(Arrays.toString(partition));
		
		// 텍스트에서 패턴 비교
		int pIdx = 0;
		List<Integer> indexes = new ArrayList<>();
		
		for (int textIdx = 0; textIdx < text.length(); textIdx++) {

			while (text.charAt(textIdx) != pattern.charAt(pIdx) && pIdx > 0) {
				pIdx = partition[pIdx - 1];
			}

			// 같으면 pidx 증가
			if (text.charAt(textIdx) == pattern.charAt(pIdx)) {
				
				// 패턴의 마지막 까지 비교했다면 리스트에 담고
				if(pIdx == pattern.length() -1) {
					indexes.add(textIdx - pIdx + 1);
					// 패턴의 마지막 인덱스의 부분 일치 테이블 값으로 변경
					pIdx = partition[pIdx];
					
				// 아니면 인덱스만 증가
				}else
					pIdx++;
			}
		}
		
		sb.append(indexes.size()).append("\n");
		for(int tmp : indexes) {
			sb.append(tmp).append(" ");
		}
		
		System.out.println(sb);
	}

}
