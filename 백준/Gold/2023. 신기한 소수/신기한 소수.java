import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	/**
	 * # 2023 신기한 소수
	 * 	7331이 있을 때, 7331도 소수, 733도 소수, 73도 소수, 7도 소수
	 * 	N자리의 신기한 소수를 모두 찾아라 
	 * 
	 * 	N : 1~8
	 * 
	 * 	각 자리수는 홀수로 이루어져 있을 것 (1,3,5,7,9)
	 * 	(짝수가 포함되어있으면, 자릿수를 줄여 확인할 때 짝수가 된다)
	 * 	N자리 숫자를 만드는건 순열
	 * 	숫자를 10으로 나눠가며 확인 (재귀?)
	 * 	숫자가 1자리 숫자가 될 때 까지
	 * 	
	 * # 소수 판별 방법
	 * 	
	 * 앞에서부터 홀수를 붙여가면서 판단
	 * 한자리 ->두자리 ->세자리...-> N자리
	 * 두자리 수에 새로운 홀수 붙여서 판단
	 * 그럼 자리수 늘릴때마다 새로운 List?
	 * Queue에 넣어?
	 * 
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int[] ODDS = { 1,3,5,7,9 };
	static Queue<String> primeNumber;	
	static int N;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// N 입력받기
		N = Integer.parseInt(br.readLine().trim());
		
		primeNumber = new LinkedList<>();
		primeNumber.add(String.valueOf(2));
		for (int oIdx = 1; oIdx < ODDS.length - 1; oIdx++) {
			primeNumber.add(String.valueOf(ODDS[oIdx]));
		}
		
		// 수를 꺼내서 ODDS를  붙여 소수인지 확인하고, 소수면 넣고
		// 숫자의 길이가 N이 될 때까지
		generateNumber(primeNumber.poll());
		String[] primeArray = primeNumber.toArray(new String[primeNumber.size()]);
		Arrays.sort(primeArray);
		for(String num : primeArray) {
			sb.append(num).append("\n");
		}
		System.out.println(sb);
	}
	
	// 홀수로 N자리 수 만들기
	private static void generateNumber(String number) {
		// 넘어온 숫자 문자열의 길이가 N이 되면 끝낸다
		// 4자리 숫자 하나 뺀거는 다시 넣어준다
		if(number.length()==N) {
			primeNumber.add(number);
			return;
		}
		for (int idx = 0; idx < ODDS.length; idx++) {
			int tmpNum = Integer.valueOf(number + ODDS[idx]);
			if (isPrime(tmpNum)) {
				primeNumber.add(String.valueOf(tmpNum));
			}
		}
		generateNumber(primeNumber.poll());
	}
	
	// 소수 판별
	private static boolean isPrime(int number) {
		for (int num = 2; num * num <= number; num++) {	//2부터 확인해야한다 (nullpoint 오류 났었음)
			if (number % num == 0)
				return false;
		}
		return true;
	}
}
