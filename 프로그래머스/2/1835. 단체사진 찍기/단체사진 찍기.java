import java.util.*;

class Solution {
    public int solution(int n, String[] data) {
        // 친구들의 배열
        char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        // 순열을 저장할 리스트
        List<String> permutations = new ArrayList<>();
        // 순열 생성
        generatePermutations(friends, 0, permutations);
        
        int validCount = 0;
        
        // 생성된 순열마다 조건 체크
        for (String perm : permutations) {
            boolean isValid = true;
            for (String condition : data) {
                char first = condition.charAt(0);
                char second = condition.charAt(2);
                char op = condition.charAt(3);
                int gap = condition.charAt(4) - '0';
                
                int firstIndex = perm.indexOf(first);
                int secondIndex = perm.indexOf(second);
                int distance = Math.abs(firstIndex - secondIndex) - 1;
                
                if (op == '=') {
                    if (distance != gap) {
                        isValid = false;
                        break;
                    }
                } else if (op == '<') {
                    if (distance >= gap) {
                        isValid = false;
                        break;
                    }
                } else if (op == '>') {
                    if (distance <= gap) {
                        isValid = false;
                        break;
                    }
                }
            }
            if (isValid) {
                validCount++;
            }
        }
        
        return validCount;
    }
    
    // 순열 생성 함수
    private void generatePermutations(char[] array, int currentIndex, List<String> permutations) {
        if (currentIndex == array.length) {
            permutations.add(new String(array));
            return;
        }
        
        for (int i = currentIndex; i < array.length; i++) {
            swap(array, currentIndex, i);
            generatePermutations(array, currentIndex + 1, permutations);
            swap(array, currentIndex, i);
        }
    }
    
    // 배열의 두 원소를 스왑하는 함수
    private void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
