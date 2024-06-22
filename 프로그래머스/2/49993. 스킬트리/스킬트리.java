import java.util.*;
/*

*/
class Solution {
    
    public int solution(String skill, String[] skill_trees) {
        List<Character> skills = new ArrayList<>();
        for(int idx=0; idx<skill.length(); idx++){
            skills.add(skill.charAt(idx));
        }
        //System.out.println(skills.size());
        //System.out.println(skills.toString());
        
        for(int aidx=0; aidx<skill_trees.length; aidx++){
            
            String nowST = skill_trees[aidx];
            for(int idx=0; idx<skill_trees[aidx].length(); idx++){
                char ch = skill_trees[aidx].charAt(idx);
                //System.out.println(ch);
                if(!skills.contains(ch)){
                    nowST = nowST.replace(String.valueOf(ch), "");
                }
            }
            skill_trees[aidx] = nowST;
        }
        
        //System.out.println(Arrays.toString(skill_trees));
        
        int answer = 0;
        
        for(String st : skill_trees){
            if(skill.startsWith(st))
                answer++;
        }
        
        return answer;
    }
}