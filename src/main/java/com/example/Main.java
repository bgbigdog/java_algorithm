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

    public int minCostClimbingStairs(int[] cost) {
        if(cost.length == 1)return cost[0];

        int dp[] = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;

        for(int i = 2; i < dp.length; i++){
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[dp.length - 1];
    }

    public int uniquePaths(int m, int n) {

        if (m == 1) {
            return 1;
        }

        if (n == 1) {
            return 1;
        }

        int dp[][] = new int [m][n];
        for(int i = 0; i < n; i++){
            dp[0][i] = 1;
        }

        for(int i = 0; i < m; i++){
            dp[i][0] = 1;
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}