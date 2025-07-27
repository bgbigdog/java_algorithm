package com.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int array[] = { 1, 1, 1, 1, 1 };
        // nextPermutation(array);
        // findTargetSumWays(array, 3);
        String strArray[] = {"0","00","1"};
        int m = 2;
        int n = 1;
        findMaxForm(strArray, m, n);
    }

    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        if (nums[0] == slow)
            return slow;
        slow = nums[0];
        do {
            slow = nums[slow];
            fast = nums[fast];
        } while (slow != fast);

        return slow;

    }

    public static void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while (i > 0 && nums[i - 1] >= nums[i]) {
            i--;
        }

        if (i > 0) {
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

        for (int i = 0; i < nums.length; i++) {
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
        for (; j < rNum; j++) {
            nums[j] = 0;
        }

        for (; j < rNum + wNum; j++) {
            nums[j] = 1;
        }

        for (; j < rNum + wNum + bNum; j++) {
            nums[j] = 2;
        }
    }

    public int climbStairs(int n) {
        int res[] = new int[n];
        res[0] = 1;
        res[1] = 2;

        for (int i = 2; i < n; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }

        return res[n - 1];
    }

    public int minCostClimbingStairs(int[] cost) {
        if (cost.length == 1)
            return cost[0];

        int dp[] = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i < dp.length; i++) {
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

        int dp[][] = new int[m][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    public static int integerBreak(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;

        for (int i = 3; i < n + 1; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = max(j * (i - j), j * dp[i - j], dp[i]);
            }
        }

        return dp[n];
    }

    public static int max(int a0, int a1, int a2) {
        if (a0 > a1) {
            if (a0 > a2) {
                return a0;
            } else {
                return a2;
            }
        } else {
            if (a1 > a2) {
                return a1;
            } else {
                return a2;
            }
        }
    }

    public static int numTrees(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        if (n < 2)
            return 1;

        // dp[i]表示的是i整数的数组有多少种可能
        for (int i = 2; i < dp.length; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }

    public boolean canPartition(int[] nums) {
        int half = 0;
        for (int i = 0; i < nums.length; i++) {
            half += nums[i];
        }
        if (half != half / 2 * 2)
            return false;

        half = half / 2;
        int dp[] = new int[half + 1];

        // 如何转化为01背包问题，就是把i处的重量的东西只能一次地塞入背包里面去
        // 这个地方难点就是确定dp含义
        // dp[i] 的意思就是容量为i的时候最大的容量的最大价值就是dp[i]
        // dp[i] = max(dp[i], dp[i - nums[j]] + nums[j])
        // 初始化顺序，外层是考虑放入的一个接着一个的价值的东西
        // 初始化的话都是为0，然后就是开始往里面塞东西

        for (int i = 0; i < nums.length; i++) {
            for (int j = half; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }

        if (dp[half] == half)
            return true;

        return false;
    }

    public static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if ((sum + target) % 2 != 0)
            return 0;
        int left = (sum + target) / 2;
        // left是需要装满的价值的，然后我现在需要做的是找装到这个量级的数量是多少
        // 构造dp数组，为dp[i]为装满i的价值的情况下是有dp[i]的数量的装法
        // 寻找递归方程式：dp[j] =dp[j] + dp[j - nums[i]]
        // 正序会导致重复的寻找导致问题

        int dp[] = new int[left + 1];
        dp[0] = 1;

        for (int j = 0; j < nums.length; j++) {
            for (int i = dp.length - 1; i >= nums[j]; i--) {
                dp[i] += dp[i - nums[j]];
            }
        }

        return dp[left];
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        // dp[i][j]是指的i个零和j个一的情况下有多少个元素
        // dp[i][j] = max(dp[i][j], dp[i - x][j - y] + 1)
        // 初始化dp[i][j]初始化0这个理解到位了
        // 遍历顺序：倒着来，防止重复地去加这个一

        int dp[][] = new int[m + 1][n + 1];

        for (int k = 0; k < strs.length; k++) {
            int zeroNum = 0;
            int oneNum = 0;

            for(char tempt: strs[k].toCharArray()){
                if (tempt == '0') {
                    zeroNum++;
                }else{
                    oneNum++;
                }
            }
            if(zeroNum > m || oneNum > n)continue;

            for(int i = m; i >= zeroNum; i--){
                for(int j = n; j >= oneNum; j--){
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }

        return dp[m][n];

    }
}