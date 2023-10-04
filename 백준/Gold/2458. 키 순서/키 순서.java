import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {
     
    /**
     *  # 18681_줄 세우기
     * 
     *  [설명]
     * 학생들 키 순서대로 나열
     * 일부 학생끼리만 키 비교
     * 명확하게 키를 알 수 있는 사람의 수 출력
     * 
     * 학생 수 2이상 500이하 정수
     * 키 비교 횟수는 (N * (N-1)) /2 이하의 정수
     * 
     *  [입력]
     * ===> 테스트 케이스 수
     * ===> 학생 수
     * ===> 키를 비교한 횟수
     * ===> 키 순서 정보
     * 
     *  [출력]
     * 명확하게 키 순서 알 수 있는 사람 수 출력
     * 
     *  [풀이방법]
     * 위상 정렬 후 만들어진 순서에 대해
     * 각 순서 좌우에 사이클이 없다면 순서를 명확하게 알 수 있다.
     * 루프 판단이... 쉽지 않네...DFS는 탐색 시작점이 문제고 애초에 루프도 아니구나
     * 
     * */
     
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
 
    static boolean visited[];
    static int visitCnt;
     
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		
		// 학생 수
		int studentNum = Integer.parseInt(st.nextToken());

		// 비교 횟수
		int compareNum = Integer.parseInt(st.nextToken());

		// 연결 정보 저장할 자료구조
		List<List<Integer>> connectMap = new ArrayList<>();
		// 역방향 연결 정보 저장 자료구조
		List<List<Integer>> reverseMap = new ArrayList<>();

		for (int loop = 0; loop <= studentNum; loop++) {
			connectMap.add(new ArrayList<>());
			reverseMap.add(new ArrayList<>());
		}
		// 진입 차수 표시 배열
		int intoCnt[] = new int[studentNum + 1];

		// 비교 정보 저장
		for (int loop = 0; loop < compareNum; loop++) {
			st = new StringTokenizer(br.readLine().trim());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			connectMap.get(from).add(to);
			reverseMap.get(to).add(from);
			intoCnt[to]++;
		}

		int cnt = 0;

		// 특정 정점 기준으로 역방향 탐색, 정방향 탐색해서 모든 정점을 방문한다면 명확하게 아는 것
		for (int number = 1; number <= studentNum; number++) {
			visited = new boolean[studentNum + 1];
			int tmpCnt = Bfs(number, connectMap) + Bfs(number, reverseMap);

			// System.out.println(tmpCnt);
			if (tmpCnt == studentNum)
				cnt++;
		}
		sb.append(cnt).append("\n");
		System.out.println(sb);
	}
 
    private static int Bfs(int number, List<List<Integer>> map) {
        int cnt = 0;
 
        Queue<Integer> queue = new ArrayDeque<>();
        if(!visited[number])
            cnt++;
        visited[number] = true;
        queue.offer(number);
         
        while(!queue.isEmpty()) {
            int current = queue.poll();
             
            for(int num : map.get(current)) {
                if(visited[num])
                    continue;
                if(!visited[num])
                    cnt++;
                visited[num] = true;
                queue.offer(num);
            }
        }
        return cnt;
    }
 
 
}