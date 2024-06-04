import java.util.*;
/*
y에서 x를 만들기...
또는 dp지 뭐...
배열에 해당 숫자를 만들기까지 해온 연산의 개수를 저장
*/
class Solution {
    public int solution(int x, int y, int n) {
        
        boolean[] visited = new boolean[y +1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(x);
        visited[x] = true;
        
        int cnt = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int loop=0; loop<size; loop++){
                int number = q.poll();

                if(number == y)
                    return cnt;

                if(number + n <= y && !visited[number +n]){
                    q.offer(number + n);
                    visited[number +n] = true;
                }
                if(number * 2 <= y && !visited[number * 2]){
                    q.offer(number *2);
                    visited[number *2] = true;
                }
                if(number *3 <= y && !visited[number *3]){
                    q.offer(number *3);
                    visited[number *3] = true;
                }
            }
            cnt++;
        }
        
        return -1;
    }
}
