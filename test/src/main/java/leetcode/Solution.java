package leetcode;

import java.util.*;

public class Solution {
    public static void main(String[] args){
        System.out.println(Arrays.toString(new Solution().twoSum2(new int[]{3, 4, 3},6)));
    }
    public int[] myTwoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target&&i!=j) {
                    return new int[]{i,j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target) {
                    return new int[]{i,j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        for (int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        Set<Integer> set = map.keySet();
        for (Integer i :
                set) {
            System.out.println(i+":"+map.get(i));
        }
        for (int i=0;i<nums.length;i++){
            if(map.containsKey(target-nums[i])&&map.get(target-nums[i])!=i){
                return new int[]{i,map.get(target-nums[i])};
            }
        }

        throw new IllegalArgumentException("No two sum solution");
    }
}