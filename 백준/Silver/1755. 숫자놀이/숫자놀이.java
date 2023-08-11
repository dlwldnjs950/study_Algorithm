import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 	# 1755_숫자놀이
	 * 
	 * 	[설명]
	 * 숫자를 영어로 읽되 숫자 단위로 하나씩 읽는다
	 * 정수의 범위가 주어질 때, 
	 * 정수를 영어 문자열 기준으로 사전순 정렬해라
	 * 
	 * 	[입력]
	 * ===> 숫자 범위 M과 N (공백 구분)
	 * 
	 * 	[출력]
	 * 정렬한 문자열을 한 줄에 10개씩 출력한다.
	 * 
	 * 	[풀이방법]
	 * 숫자 마다 영어 문자열로 바꾸는 함수
	 * sort에 Comparator를 새로 넣어주는데 이 문자열을 기준으로 해주면 된다
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		// 범위의 숫자로 구성된 List를 만든다
		List<Integer> numbers = new ArrayList<>();
		for(int num=M; num<= N; num++) {
			numbers.add(num);
		}
		
		Collections.sort(numbers, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return readingNumbers(o1).compareTo(readingNumbers(o2));
			}
		});
		
		for(int idx=0; idx<numbers.size(); idx++) {
			sb.append(numbers.get(idx)).append(" ");
			if(idx%10 == 9)
				sb.append("\n");
		}
		System.out.println(sb);
	}

	private static String readingNumbers(int number) {
		String rnsb = "";
		
		while(number>0) {
			switch(number%10) {
			case 0:
				rnsb = "zero"+ rnsb;
				break;
			case 1:
				rnsb = "one"+ rnsb;
				break;
			case 2:
				rnsb = "two"+ rnsb;
				break;
			case 3:
				rnsb = "three"+ rnsb;
				break;
			case 4:
				rnsb = "four"+ rnsb;
				break;
			case 5:
				rnsb = "five"+ rnsb;
				break;
			case 6:
				rnsb = "six"+ rnsb;
				break;
			case 7:
				rnsb = "seven"+ rnsb;
				break;
			case 8:
				rnsb = "eight"+ rnsb;
				break;
			case 9:
				rnsb = "nine"+ rnsb;
				break;
			}
			number/=10;
			rnsb+= " ";
		}
		return rnsb.toString().trim();
	}
}
