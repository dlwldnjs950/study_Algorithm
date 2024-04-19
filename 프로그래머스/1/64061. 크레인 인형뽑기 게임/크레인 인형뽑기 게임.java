import java.util.*;
/*
NxN의 격자 게임판
바구니
멈춘 열의 맨 위에 있는 인형을 집어 바구니로 옮김
같은 인형 연속으로 2개 쌓이면 같이 제거

게임판의 상태와 크레인을 작동시킨 위치가 주어질 때,
터트려져 사라진 인형의 개수?
*/
class Solution {
    public int solution(int[][] board, int[] moves) {
        // 각 열을 stack으로 담아보자, 바구니도 스택
        List<Stack<Integer>> game = new ArrayList<>();
        // 맨아랫줄부터 담는다
        for(int rIdx=board.length-1; rIdx>= 0; rIdx--){
         for(int cIdx=0; cIdx< board[0].length; cIdx++){
            if(rIdx==board.length-1)
                game.add(new Stack<Integer>());
             if(board[rIdx][cIdx]!=0)
                game.get(cIdx).push(board[rIdx][cIdx]);
         }
        }
        
        int answer = 0;
        
        Stack<Integer> basket = new Stack<>();
        for(int idx=0; idx<moves.length; idx++){
            if(game.get(moves[idx]-1).empty())
                continue;
            int nowDoll = game.get(moves[idx]-1).pop();
            if(basket.isEmpty()){
                basket.push(nowDoll);
                continue;
            }else if(basket.peek() == nowDoll){                
                basket.pop();
                answer+=2;;
            }else{
                basket.push(nowDoll);
            }
        }
        return answer;
    }
}