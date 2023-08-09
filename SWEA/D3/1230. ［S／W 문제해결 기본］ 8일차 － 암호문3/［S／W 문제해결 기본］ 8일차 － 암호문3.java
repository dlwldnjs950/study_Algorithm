import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 	# 1230_암호문3
	 * 
	 * 	[설명]
	 * 암호문 1개는 숫자덩어리
	 * 암호문 N를 모아둔 암호문 뭉치
	 * 
	 * 	# 명령어
	 * 1. I x y s : 삽입연산. x번째 암호문 바로 다음에 y개 암호문 삽입 (s자리에 y개의 암호문 주어짐)
	 * 2. D x y : 삭제연산. x번째 바로 다음부터 y개 암호문 삭제
	 * 3. A y s : 추가연산. 암호문 맨 뒤에 y개의 암호문 덧붙임 (s자리에 y개의 암호문 주어짐)
	 * 
	 * 	[입력]
	 * 테스트 케이스 10개
	 * ===> 원본 암호문 뭉치를 구성하는 암호문 개수 N
	 * ===> 원본 암호문 뭉치
	 * ===> 명령어 개수 M
	 * ===> 명령어
	 * 
	 * 	[출력]
	 * 수정된 결과의 처음 10개 암호문 출력
	 * 
	 * 	[풀이방법]
	 * 각 암호문의 수행 동작을 함수로 정의해 사용
	 * 삽입, 삭제, 추가 연산이 있으므로 List 객체로 암호문을 관리한다.
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static List<Integer> sentence;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 테스트 케이스 개수 T
		int T = 10;

		for(int testCase =1; testCase <=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			// 뭉치를 구성하는 암호문 개수 N
			int N = Integer.parseInt(br.readLine().trim());
			
			// 암호문 뭉치 입력받기
			st = new StringTokenizer(br.readLine().trim());
			
			// 암호문 뭉치 List 객체 구성하기
			sentence = new LinkedList<>();
			while(st.hasMoreTokens()) {
				sentence.add(Integer.parseInt(st.nextToken()));
			}
			
			// 명령어 개수 M
			int M = Integer.parseInt(br.readLine().trim());
			// 명령어 입력받기
			st = new StringTokenizer(br.readLine().trim());
			while(st.hasMoreTokens()) {
				switch (st.nextToken()) {
				case "I":	// 삽입연산
					insert();
					break;
				case "D":	// 삭제연산
					delete();
					break;
				case "A":	// 추가 연산
					add();
					break;
				}
			}
			
			for(int idx = 0; idx<10; idx++) {
				sb.append(sentence.get(idx)).append(" ");
			}
			sb.append("\n");

		}
		System.out.println(sb);
	}

	// x y s : 삽입연산. x번째 암호문 바로 다음에 y개 암호문 삽입 (s자리에 y개의 암호문 주어짐)
	static void insert() {
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		List<Integer> insert = new LinkedList<>();
		for(int loop=0; loop<y; loop++) {
			insert.add(Integer.parseInt(st.nextToken()));
		}
		
		sentence.addAll(x, insert);
	}
	
	// x y : 삭제연산. x번째 바로 다음부터 y개 암호문 삭제
	static void delete() {
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		for(int idx=0; idx<y; idx++) {
			sentence.remove(x + idx);
		}
	}
	
	// y s : 추가연산. 암호문 맨 뒤에 y개의 암호문 덧붙임 (s자리에 y개의 암호문 주어짐)
	static void add() {
		int y = Integer.parseInt(st.nextToken());
		List<Integer> add = new LinkedList<>();
		for(int loop=0; loop<y; loop++) {
			add.add(Integer.parseInt(st.nextToken()));
		}
		sentence.addAll(sentence.size()-1, add);
	}
}
