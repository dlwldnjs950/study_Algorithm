import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 세로 2줄, 가로 N칸
	 * 
	 * 첫째줄 : 1 ~ N이 차례대로
	 * 둘째줄 : 1 이상 N 이하 정수 (무작위)
	 * 		첫째줄에서 숫자를 적절히 뽑으면
	 * 		그 뽑힌 정수 집합과 뽑힌 정수 바로 밑 둘째줄 정수 집합 일치
	 * 	최대로 많이 뽑기
	 * 
	 * 앞에서부터 순서대로 뽑/안뽑
	 * 다 선택 되었을 때, 선택인덱스 집합과 선택값 집합이 같은지 확인
	 * */
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static boolean isSelected[];
	static int N, grid[];
	static List<Integer> result = new ArrayList<Integer>();
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine().trim());
		grid = new int[N +1];
		isSelected = new boolean[N +1];
		
		for(int idx=1; idx<=N; idx++) {
			grid[idx] = Integer.parseInt(br.readLine().trim());
		}
		
		for(int idx=1; idx<=N; idx++) {
			isSelected[idx] = true;
			dfs(idx, idx);
			isSelected[idx] = false;
		}

		Collections.sort(result);
		sb.append(result.size()).append("\n");
		for (int num : result)
			sb.append(num).append("\n");

		System.out.println(sb);
	}

	static void dfs(int startIdx, int targetIdx) {
		if (isSelected[grid[startIdx]] == false) {
			isSelected[grid[startIdx]] = true;
			dfs(grid[startIdx], targetIdx);
			isSelected[grid[startIdx]] = false;
		}
		if(grid[startIdx] == targetIdx)
			result.add(targetIdx);
	}
}