import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 숫자 버튼과 채널 -/+ 버튼
	 * 숫자 버튼중에 고장난게 있음 => 숫자 버튼 list에 저장해서 contains
	 * 100번에서 시작해서 수빈이가 원하는 채널로 이동
	 * 만들 수 있는 가장 가까운 숫자...
	 * */

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Some {
	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int targetNum = Integer.parseInt(br.readLine().trim());
		int brokenCnt = Integer.parseInt(br.readLine().trim());
		
		List<Integer> brokenNums = new ArrayList<>();
		if (brokenCnt > 0)
			st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < brokenCnt; idx++) {
			brokenNums.add(Integer.parseInt(st.nextToken()));
		}
		
		int pushCnt = Math.abs(targetNum - 100);
		
		//그냥 0부터 999999까지 전부 확인한다.
	    for (int i = 0; i <= 999999; i++) {
	        String num = String.valueOf(i);

	        boolean isBreak = false;
	        for (int j = 0; j < num.length(); j++) {
	        	int tmpNum = Integer.parseInt(num.charAt(j)+"");
	            if (brokenNums.contains(tmpNum)) {
	                //고장난 버튼을 눌러야 하면 멈춘다.
	                isBreak = true;
	                break;
	            }
	        }
	        if (!isBreak) {
	            //버튼을 누르는 횟수 중 가장 적은 횟수를 result에 담는다.
	            int min = Math.abs(targetNum - i) + num.length();
	            pushCnt = Math.min(min, pushCnt);
	        }
	    }
		

		sb.append(pushCnt);
		System.out.println(sb);

	}

}
