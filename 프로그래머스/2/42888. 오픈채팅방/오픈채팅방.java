import java.util.*;
/*
출입하는 사람들 볼 수 있는 관리자창
닉네임 변경 방법
    1. 채팅방을 나간 후, 새로운 닉네임으로 다시 들어가기
    2. 채팅방에서 닉네임 변경 
    두 경우 모두 (기존에 출력된 메세지 닉네임도 변경)

닉네임 변경 기록이 담긴 문자열 배열
방을 개설한 사람이 보게 되는 메세지를 문자열 배열 형태로 return

(동작 유저아이디 닉네임) 조합으로 문자열
동작 : Enter, Leave, Change
*/
class Solution {
    
    static final String[] MESSAGE = {"님이 들어왔습니다.", "님이 나갔습니다."};
    public String[] solution(String[] record) {
        // 각 유저의 닉네임 관리
        // 메시지는 (유저아이디 - 동작)으로 관리
        Map<String, String> userNickname = new HashMap<>();
        // 출력 메시지 리스트
        List<String> messages = new ArrayList<>();
        
        for(String re : record){
            String[] commands = re.split(" ");
            // Enter, Change일 때, 닉네임 변경
            if(("Leave").equals(commands[0])){
                // 메시지 추가
                messages.add(commands[1]+" "+1);
            }else if(("Enter").equals(commands[0])){
                // 닉네임 변경
                userNickname.put(commands[1], commands[2]);
                // 메시지 추가
                messages.add(commands[1]+" "+0);
            }else if(("Change").equals(commands[0])){
                // 닉네임 변경
                userNickname.put(commands[1], commands[2]);                
            }
        }
        
        String[] answer = new String[messages.size()];
        for(int idx=0; idx<messages.size(); idx++){
            String[] command = messages.get(idx).split(" ");
            answer[idx] = userNickname.get(command[0])+MESSAGE[Integer.parseInt(command[1])];
        }
        
        return answer;
    }
}