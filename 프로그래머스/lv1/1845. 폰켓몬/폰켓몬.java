import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int[] nums) {
        List<Integer> num = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if(!num.contains(nums[i]))
				num.add(nums[i]);			
		}
		if(nums.length/2<num.size())
			return nums.length/2;
		else
			return num.size();
    }
}