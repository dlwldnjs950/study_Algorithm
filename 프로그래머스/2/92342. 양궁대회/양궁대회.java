import java.util.*;
/*
만들 수 있는 모든 점수 조합을 확인하기보다 적젛한 가지치기가 필요하다
현재 점수를 얻는지 얻지 못하는지
*/
class Solution {
    static int maxDiff;
    static int[] answer;

    public int[] solution(int n, int[] info) {
        maxDiff = -1;
        answer = new int[11];
        int[] ryan = new int[11];
        backtrack(n, info, ryan, 10);
        
        if (maxDiff <= 0) {
            return new int[]{-1};
        }
        return answer;
    }

    private void backtrack(int arrows, int[] apeach, int[] ryan, int idx) {
        if (idx == -1 || arrows == 0) {
            ryan[10] += arrows;  // 남은 화살은 모두 0점에 배치
            int ryanScore = 0;
            int apeachScore = 0;
            for (int i = 0; i < 11; i++) {
                if (ryan[i] > apeach[i]) {
                    ryanScore += 10 - i;
                } else if (apeach[i] > 0) {
                    apeachScore += 10 - i;
                }
            }
            int diff = ryanScore - apeachScore;
            if (diff > maxDiff) {
                maxDiff = diff;
                answer = ryan.clone();
            }
            ryan[10] -= arrows;  // 되돌리기
            return;
        }
        
        // 현재 점수를 이기는 경우
        if (arrows > apeach[idx]) {
            ryan[idx] = apeach[idx] + 1;
            backtrack(arrows - ryan[idx], apeach, ryan, idx - 1);
            ryan[idx] = 0;  // 되돌리기
        }

        // 현재 점수를 지는 경우
        backtrack(arrows, apeach, ryan, idx - 1);
    }
}