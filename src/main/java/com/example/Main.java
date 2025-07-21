package com.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("first change code");

    }

    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while(slow != fast);

        if (nums[0] == slow) return slow;
        slow = nums[0];
        do{
            slow = nums[slow];
            fast = nums[fast];
        }while(slow != fast);

        return slow;
        
    }
}