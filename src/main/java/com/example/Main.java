package com.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int array[] = {7,2,3,4,5,5};
        nextPermutation(array);

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

    public static void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while (i > 0 && nums[i - 1] >= nums[i]) {
            i--;
        }

        if(i > 0){
            int j = nums.length - 1;
            while (nums[i - 1] >= nums[j]) {
                j--;
            }

            int tempt = nums[j];
            nums[j] = nums[i - 1];
            nums[i - 1] = tempt;
        }

        Arrays.sort(nums, i, nums.length);
    }

    public static void sortColors(int[] nums) {
        int rNum = 0;
        int wNum = 0;
        int bNum = 0;

        for(int i = 0; i < nums.length; i++){
            switch (nums[i]) {
                case 0:
                    rNum++;
                    break;
                case 1:
                    wNum++;
                    break;
                default:
                    bNum++;
                    break;
            }
        }

        int j = 0;
        for(;j < rNum; j++){
            nums[j] = 0;
        }

        for(;j < rNum+wNum; j++){
            nums[j] = 1;
        }

        for(;j < rNum + wNum + bNum; j++){
            nums[j] = 2;
        }
    }

    public int climbStairs(int n) {
        int res[] = new int[n];
        res[0] = 1;
        res[1] = 2;

        for(int i = 2; i < n; i++){
            res[i] = res[i - 1] + res[i - 2];
        }

        return res[n-1];
    }
}