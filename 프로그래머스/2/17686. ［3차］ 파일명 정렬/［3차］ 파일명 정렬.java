import java.util.*;

/*
숫자가 포함된 파일 목록 관리 불편
파일 정렬이 문자코드 순서로 된다

영문 대소문자, 숫자, 공백, 마침표, 빼기 부호
HEAD : 문자 (한 글자 이상)     => 사전순 정렬
NUMBER : 숫자 (한글자~다섯글자, 앞쪽에 0 가능)    => 숫자순 정렬
TAIL : 그 나머지 (숫자 가능, 빈문자열 가능)   => head와 number가 같다면 순서 유지
*/
class Solution {
    public class FileNames{
        String head;
        String number;
        String tail;
        
        FileNames(String head, String number, String tail){
            this.head = head;
            this.number = number;
            this.tail = tail;
        }
    }
    public String[] solution(String[] files) {
    
        
        // System.out.println("\""+separateFileName(files[0]).head+"\""+"\""+separateFileName(files[0]).number+"\"");
        Arrays.sort(files, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                FileNames f1 = separateFileName(o1);
                FileNames f2 = separateFileName(o2);
                    // System.out.println(o1+" : "+f1.number+" // "+o2+" : "+f2.number);
                if((f1.head).equals(f2.head)){
                    //return 1;
                    return Integer.parseInt(f1.number) - Integer.parseInt(f2.number);
                }else{
                    return (f1.head).compareTo(f2.head);
                }
            }
        });
        return files;
    }
    
    public FileNames separateFileName(String file){
        String head = file.split("[0-9]")[0].toLowerCase();
        String numTail = file.substring(head.length());
        String number = "";
        for(int idx=0; idx<numTail.length(); idx++){
            if(!Character.isDigit(numTail.charAt(idx))|| idx==5){
                number = numTail.substring(0,idx++);
                break;
            }
        }
        if(number.equals(""))
            number = numTail;
        
        return new FileNames(head, number, "");
    }
}