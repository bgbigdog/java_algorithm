# java_algorithm
![alt text](image.png)
快慢指针法（Floyd 判圈算法）背后的数学推理建立在**链表中存在环**时两个指针的相遇性质上。我们来从数学的角度详细推导这个过程，并解释为什么它能找出“重复数字”。

---

## 🧩 1. 问题建模为链表找环

题目中给定数组：

* `nums` 长度为 `n+1`
* 元素取值范围是 `[1, n]`，因此根据 **鸽巢原理**，**至少有一个数字重复**
* 把每个下标 `i` 看成链表中的“节点”，值 `nums[i]` 视为“下一个节点指针”

于是我们得到了一个从 `0` 开始的伪链表结构：

```
0 → nums[0] → nums[nums[0]] → ...
```

这个结构必然 **形成一个环**，因为某个值会重复，意味着某两个节点指向同一个“下一个节点”。

---

## 🧠 2. Floyd 判圈法推理（快慢指针）

### 定义变量：

* `μ`：**环前长度**（从起点到环开始位置的距离）
* `λ`：**环长度**
* `x`：环开始的下标（重复数字）

---

### 第一步：相遇点推理

设：

* 慢指针一次走 1 步：`slow = slow + 1`
* 快指针一次走 2 步：`fast = fast + 2`

假设在第 `t` 次迭代时它们第一次相遇。

那么根据链表结构，我们可以写出：

* 慢指针走了 `t` 步
* 快指针走了 `2t` 步

两者相遇意味着快指针比慢指针多走了若干个**环的整圈长度**：

```
2t - t = kλ ⟹ t = kλ
```

即：**相遇时，慢指针刚好走了 k 个环长度（在环上）**

所以，无论从哪里进环，两个指针都会在环内相遇。

---

### 第二步：找环的起点（重复数）

**此时我们让 slow 回到起点 0，fast 留在相遇点**，然后两个指针都以 **每次一步**的速度前进，接下来它们将在环的入口（即重复数位置）再次相遇。

为什么呢？

* 原 slow 到环入口距离为：`μ`
* fast 在环内距离环入口的距离：因为 slow 走了 `t = kλ` 步，刚好处于环中 `t - μ` 的位置（模 λ）
* 所以，两者再次相遇时都刚好走了 `μ` 步，来到入口位置

即：

```
相遇点 → ... → 重复值（环入口）
   ↑               ↑
   fast          slow (从起点出发)
```

最终返回的就是环的入口，也就是重复数字本身。

---

## ✅ 数学关键总结

| 步骤    | 数学原理            | 结果       |
| ----- | --------------- | -------- |
| 相遇点存在 | 快慢指针步数差为环长度整数倍  | `t = kλ` |
| 再次相遇点 | 分别从头与相遇点出发，一步一走 | 相遇点为环入口  |
| 环入口含义 | 重复值即为环入口        | 找到重复数    |

---

## 🏁 总结

Floyd 判圈法通过如下关键数学特性保证算法正确性：

1. 环一定存在（因为存在重复值）；
2. 相遇点存在，且距离环入口固定；
3. 重新从起点与相遇点出发，以同速必定在环入口再次相遇。

这就是为什么 **不需要修改数组也能找到重复值**。
```java
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
```

![alt text](image-1.png)
![alt text](image-2.png)

```java
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
```

![alt text](image-4.png)

![alt text](image-3.png)
```java
public int climbStairs(int n) {
        int res[] = new int[n];
        res[0] = 1;
        res[1] = 2;

        for(int i = 2; i < n; i++){
            res[i] = res[i - 1] + res[i - 2];
        }

        return res[n-1];
    }
```

![alt text](image-5.png)
```java
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
```

![alt text](image-6.png)
```java
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
```