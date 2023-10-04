import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
  
public class SWEA_5643_키순서_이지원 {
      
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
     *  위상 정렬은 아니었다
     *  
     *  해당 정점 기준으로 정방향, 역방향 탐색
     *  모든 정점을 방문 = 그래프가 모두 연결
     * 
     * */
      
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
  
    static boolean visited[];
    static int visitCnt;
      
    public static void main(String[] args) throws IOException{
  
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        // 테스트 케이스 개수 T
        int T = Integer.parseInt(br.readLine().trim());
  
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(" ");
  
            // 학생 수
            int studentNum = Integer.parseInt(br.readLine().trim());
              
            // 비교 횟수
            int compareNum = Integer.parseInt(br.readLine().trim());
  
            // 연결 정보 저장할 자료구조
            List<List<Integer>> connectMap = new ArrayList<>();
            // 역방향 연결 정보 저장 자료구조
            List<List<Integer>> reverseMap = new ArrayList<>();
  
            for (int loop = 0; loop <= studentNum; loop++) {
                connectMap.add(new ArrayList<>());
                reverseMap.add(new ArrayList<>());
            }
  
            // 비교 정보 저장
            for (int loop = 0; loop < compareNum; loop++) {
                st = new StringTokenizer(br.readLine().trim());
  
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
  
                connectMap.get(from).add(to);
                reverseMap.get(to).add(from);
            }
  
            int cnt = 0;
              
            // 특정 정점 기준으로 역방향 탐색, 정방향 탐색해서 모든 정점을 방문한다면 명확하게 아는 것
            for (int number = 1; number <= studentNum; number++) {
                visited = new boolean[studentNum + 1];
                int tmpCnt = Bfs(number, connectMap) + Bfs(number,reverseMap);
  
                //System.out.println(tmpCnt);
                if(tmpCnt == studentNum)
                    cnt++;
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
  
    // 파라미터로 역방향, 정방향 그래프를 넣어준다
    private static int Bfs(int number, List<List<Integer>> map) {
        int cnt = 0;
  
        Queue<Integer> queue = new ArrayDeque<>();
        // 시작 정점 방문을 여러번 체크하면 안되니까
        // 아직 방문하지 않은 경우에 카운트
        if(!visited[number])
            cnt++;
        visited[number] = true;
        queue.offer(number);
          
        while(!queue.isEmpty()) {
            int current = queue.poll();
              
            // 연결된 정점에 대해서만 방문
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
