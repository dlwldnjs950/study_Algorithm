import java.io.*;
import java.util.*;

public class Main {
	
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static final int LETTER_NUM = 26;
	static final String BASIC_LETTER = "antic";
	static final int BASIC_BITMASK = (1 << ('a' - 'a')) | (1 << ('n' - 'a')) |
									 (1 << ('t' - 'a')) | (1 << ('i' - 'a')) | 
									 (1 << ('c' - 'a'));
	// 10000010000100000101
	
	static int wordNum, letterNum, maxReadable;
	static List<Integer> bitmaskList;
	
	public static void main(String[] args) throws IOException{

		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine().trim());
		// 단어 개수
		wordNum = Integer.parseInt(st.nextToken());
		// 글자 개수
		letterNum = Integer.parseInt(st.nextToken());
			
		// 기본 알파벳도 못읽음
		if(letterNum < 5){
			System.out.println(0);
			return;
		}
		
		// 전부 읽기 가능
		if(letterNum == LETTER_NUM){
			System.out.println(wordNum);
			return;
		}
		
		// 각 단어의 비트마스킹
		bitmaskList = new ArrayList<>();
		
		for(int loop = 0; loop < wordNum; loop++) {
			char[] word = br.readLine().trim().toCharArray();
			bitmaskList.add(wordBitMasking(word));
		}
		
		// 배울 단어 선택하기
		maxReadable = 0;
		selectLetter(0, 0, BASIC_BITMASK);
		
		
		System.out.println(maxReadable);
	}
	
	static void selectLetter(int cnt, int startIdx, int selectedLetter) {

		if(cnt == letterNum - BASIC_LETTER.length()) {
			
			int readable = 0;
			for(int word : bitmaskList) {
				if((word & selectedLetter) == word) {
					readable++;
				}
			}
			
			maxReadable = Math.max(readable, maxReadable);
			return;
		}
		
		for(int letter = startIdx; letter < LETTER_NUM; letter++) {
			if((selectedLetter & (1 << letter)) != 0)
				continue;
			selectLetter(cnt + 1, letter + 1, selectedLetter | (1 << letter));
		}
		
	}
	
	// 단어 비트 마스킹
	static int wordBitMasking(char[] word) {
		int bitmask = 0;
		for(int idx = 4; idx + 4 < word.length; idx++) {
			bitmask = bitmask | (1 << (word[idx] - 'a'));
		}
		return bitmask | BASIC_BITMASK;	// 기본 비트 마스크 해도되고 안해도 되고
		// 앞뒤4글자 제외하고 글자를 배우는지아닌지만 확인하면 되니까
	}

	
}
