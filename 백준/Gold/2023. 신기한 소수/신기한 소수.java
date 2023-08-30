import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	/**
	 * 	#2023 신기한 소수
	 * 
	 * 	[ 설명 ]
	 * 왼쪽부터 1자리, 2자리, 3자리, 4자리 모두 소수 인 신기한 소수
	 * N자리 숫자 중에서 어떤 수들이 신기한 소수인지
	 * 그 소수를 모두 출력
	 * 
	 * 	[ 입력 ]
	 * ===> 자리수 N
	 * 
	 * 	[ 출력 ]
	 * 신기한 소수를 오름차순으로 정렬해 한줄에 하나씩 출력
	 * 
	 * 	[ 풀이 방법 ]
	 * 중복 순열 문제
	 * 	=> N자리 수 만들고 각각에 대해 조건 만족하는지 판단...하기에는 경우의 수가 너무 많다
	 * 왼쪽부터 1자리 2자리 3자리 4자리 각각도 소수를 만족해야 한다
	 * 
	 * 1자리 수 부터 시작해서 뒤에 숫자를 붙여서 소수인 숫자들의 리스트를 만든다
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 자릿수
		int numLength = Integer.parseInt(br.readLine().trim());
		
		// 오름차순으로 정렬되어있어야하므로 우선순위 큐로 관리한다.
		PriorityQueue<Long> uniqueNumbers = new PriorityQueue<>();
		uniqueNumbers.offer((long) 0);
		
		
		do {
			// 같은 길이의 소수만큼만 확인
			int size = uniqueNumbers.size();
			for(int loop = 0; loop<size; loop++) {
				// 찾아둔 소수를 하나씩 꺼내서 확인한다
				long current = uniqueNumbers.poll();
				//System.out.println("큐에서 꺼낸 수: " +current);
				
				// 1부터 9까지 붙여본다
				for(int number=1; number<=9; number++) {
					// 숫자를 뒤로 붙여서 확인한다.
					long tmp = current*10 + number;
					// 해당 수가 소수면 큐에 넣는다
					if(isPrimeNumber(tmp))
						uniqueNumbers.offer(tmp);
				}
			}
		// 큐에 담긴 숫자 길이로 반복을 제한한다
		}while(uniqueNumbers.peek().toString().length()<numLength);

		// 큐에 담긴 값을 하나씩 꺼내어 출력문에 붙인다
		while(!uniqueNumbers.isEmpty()) {
			sb.append(uniqueNumbers.poll()).append("\n");
		}
		System.out.println(sb);
	}

	static boolean isPrimeNumber(long number){
		// 1은 소수가 아니다
		if(number == 1)
			return false;
		for(int divisor = 2; divisor*divisor<=number; divisor++) {
			// 어떤 수로 나뉜다면 약수가 1과 자기자신을 제외하고 존재한다는 의미
			if(number%divisor == 0)
				return false;
		}
		
		return true;
	}
}
