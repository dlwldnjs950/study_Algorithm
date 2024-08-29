import java.io.*;
import java.util.*;

public class Main {
	
	/**
	 * 	# 1339_단어수학
	 * 
	 * 	[설명]
	 * N개의 단어로 이루어진 단어 수학 문제
	 * (알파벳 대문자)
	 * 
	 * 각 알파벳 대문자를 0~9까지의 숫자 중 하나로 바꿔서 N개의 수를 합하는 문제
	 * 
	 * 같은 알파벳 = 같은 숫자
	 * (알파벳끼리 서로 다른 숫자)
	 * 
	 * 합의 최대값? 
	 * 
	 * 	[입력]
	 * ==> 단어 개수
	 * ==> (N개의 줄에 단어)
	 * 		단어에 포함된 알파벳 최대 10개
	 * 		수의 최대 길이 8 
	 * 
	 * 	[출력]
	 * 합의 최대값
	 * 
	 * 
	 * 	[풀이방법]
	 * 가중치...
	 * 
	 * */
	
	static class Alpha implements Comparable<Alpha>{
		char alpha;
		int freq;
		int firstIdx;
		
		Alpha(char alpha, int freq, int firstIdx){
			this.alpha = alpha;
			this.freq = freq;
			this.firstIdx = firstIdx;
		}

		@Override
		public int compareTo(Alpha o) {
			if(this.freq != o.freq)
				return o.freq - this.freq;
			else
				return this.firstIdx - o.firstIdx;
		}
	}
	
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 단어 개수
		int wordNum = Integer.parseInt(br.readLine().trim());
			
		// 단어 목록
		List<String> wordList = new ArrayList<>();
		
		for(int loop = 0; loop < wordNum; loop++) {
			wordList.add(br.readLine().trim());
		}
		
		// 가중치 맵
		Map<Character, Integer> map = new HashMap<>();

        // 각 알파벳의 가중치를 계산
        for (String word : wordList) {
            int length = word.length();
            for (int i = 0; i < length; i++) {
                char ch = word.charAt(i);
                int value = (int) Math.pow(10, length - i - 1);
                map.put(ch, map.getOrDefault(ch, 0) + value);
            }
        }

        // 가중치가 큰 순서대로 정렬
        List<Integer> values = new ArrayList<>(map.values());
        values.sort(Collections.reverseOrder());

        int num = 9;
        int result = 0;

        // 가중치가 큰 알파벳부터 숫자 할당
        for (int value : values) {
            result += value * num;
            num--;
        }
		
		System.out.println(result);
	}

	
}
