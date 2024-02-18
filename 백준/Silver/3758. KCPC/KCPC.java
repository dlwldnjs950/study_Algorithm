import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Team implements Comparable<Team> {
		int teamID;
		int totalScore;
		int submitCount;
		int lastSubmitTime;
		int[] scores;

		public Team(int ID, int problemCnt) {
			this.teamID = ID + 1;
			this.totalScore = 0;
			this.submitCount = 0;
			this.lastSubmitTime = 0;
			scores = new int[problemCnt + 1];
		}

		@Override
		public int compareTo(Team o) {
			if (this.totalScore == o.totalScore) {
				if(this.submitCount == o.submitCount) {
					
					// 마지막 제출 시간이 빠를 수록
					return this.lastSubmitTime - o.lastSubmitTime;
				}
				
				// 제출 횟수가 적을 수록
				return this.submitCount - o.submitCount;
			}

			// 점수가 높은 순서
			return o.totalScore - this.totalScore;
		}
		
		public void calculateTotalSum() {
			for(int score : scores) {
				this.totalScore += score;
			}
		}
	}
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine().trim());
		
		for(int testCase = 1; testCase<=tc; testCase++) {
			
			// 팀개수 문제개수 당신팀ID 로그엔트리개수
			st = new StringTokenizer(br.readLine().trim());			
			int teamCnt = Integer.parseInt(st.nextToken());
			int problemCnt = Integer.parseInt(st.nextToken());
			int teamID = Integer.parseInt(st.nextToken());
			int logEntryCnt = Integer.parseInt(st.nextToken());
			
			// 각 팀의 현황
			List<Team> teams = new ArrayList<Team>();
			for(int idx = 0; idx<teamCnt; idx++) {
				teams.add(new Team(idx, problemCnt));
			}
			
			// 각 로그
			for(int logIdx = 0; logIdx<logEntryCnt; logIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				
				int id = Integer.parseInt(st.nextToken());
				int problem = Integer.parseInt(st.nextToken());
				int score = Integer.parseInt(st.nextToken());
				
				teams.get(id-1).scores[problem] = Math.max(score, teams.get(id-1).scores[problem]);
				teams.get(id-1).submitCount +=1;
				teams.get(id-1).lastSubmitTime = logIdx;
			}
			
			for(Team t : teams) {
				t.calculateTotalSum();
			}
			
			Collections.sort(teams);
			
			for(int idx = 0; idx<teamCnt; idx++) {
				if(teams.get(idx).teamID == teamID)
					sb.append(idx +1);
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

}
