import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0, range = w * 2 + 1;

        // 맨 앞 구간
        if (stations[0] - w > 1) {
            answer += count(1, stations[0] - w - 1, range);
        }

        // 중간 구간
        for (int i = 1; i < stations.length; i++) {
            answer += count(stations[i - 1] + w + 1, stations[i] - w - 1, range);
        }

        // 맨 뒤 구간
        if (stations[stations.length - 1] + w < n) {
            answer += count(stations[stations.length - 1] + w + 1, n, range);
        }

        return answer;
    }

    int count(int left, int right, int range) {
        return (right >= left) ? (int) Math.ceil((double) (right - left + 1) / range) : 0;
    }
}
