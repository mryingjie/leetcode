package com.github.myyingjie.leetcode.algorithm;

/**
 * created by Yingjie Zheng at 2019-09-17 16:35
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意你不能在买入股票前卖出股票。
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * <p>
 * 使用dp table，动态规划(状态机)解法
 * 本题是一系列股票问题的最简单情况 假设最多允许的交易次数为K(本题为1，完整的交易包含买卖两步，只要有了买的操作就表示已经使用了一次交易机会) 天数为N(prices数组的长度为N)
 * 类似的扩展问题还有K=2 ,K = 任意。。。
 * <p>
 * 思路：动态规划 + 状态机
 * 1、使用状态机(DP table)算法首先要穷举出所有的状态和行为
 * 本题中的状态包括三种(第几天从0开始,至今最多交易了几次,当天结束时是否持有股票)分别用(n,k,0 or 1)表示 0代表未持有股票
 * 程序中可以定义一个三维数组int[] dp=new int[n,k,2]来表示这个状态例如dp[1,1,1] 表示当前第二天，至今最多交易了一次，当天结束时持有股票
 * 相应的选择行为也有三种 买  卖  没有任何操作
 * <p>
 * 2、分析所有的行为会对状态产生什么影响  这里规定一旦买了股票就是进行了一次交易k-1
 * 如果今天是买 那么当天的利润dp[n,k,1]=dp[n-1,k-1,0] - prices(n) 昨天的利润减去今天买所花的钱 股票持有状态从0变为1 因为今天买了所以今天的最大交易次数比昨天的多1
 * 如果是卖 那么当天的利润dp[n,k,0]=dp[n-1,k,1] + prices(n) 昨天的利润加上今天卖股票得到的利润 股票持有状态从1变为0 进行了完整的一次交易k要-1
 * 如果是不操作 那么当天的利润dp[n,k,0]=dp[n-1,k,0] 或 dp[n,k,1]=dp[n-1,k,1] 除了天数其余都不变
 * <p>
 * 3、分析所需要的最终结果(状态)
 * 最终状态肯定是没有持有股票，最后一天结束后n=N-1,至今最多进行了k=K次交易,不持有股票即dp[N-1,K,0]
 * <p>
 * 4、分析最基本的状态 或是已知的几种状态下的利润值
 * dp[n,0,0] = 0 表示至今最多允许0次交易 没有一次交易所以一定为0
 * dp[n,0,1] = null 表示不允许交易 就不能买也不能卖 所以不可能出现
 * dp[-1,k,0] = 0 表示交易开始之前没有持有股票 利润一定为0
 * dp[-1,k,1] = null 表示交易开始之前就持有了股票 这种情况不存在
 * <p>
 * 5、总结状态转移方程 每天最大的收益情况
 * dp[n,k,1] = max(pd[n-1,k,1],dp[n-1,k-1,0] - prices(n))
 * = max(选择不操作   , 选择买 当前利润为昨天的减去花费的钱)
 * <p>
 * dp[n,k,0] = max(dp[n-1,k,0],dp[n-1,k,1] + prices(n))
 * = max(选择不操作   , 选择卖 当前的利润为昨天的加上收入的钱)
 * <p>
 * 6、当K = 1 时代入状态方程
 * dp[n,1,0] = max(dp[n-1,1,0],dp[n-1,1,1] + prices(n))
 * dp[n,1,1] = max(dp[n-1,1,1],dp[n-1,0,0] - prices(n))   其中dp[n-1][0][0] = 0
 * <p>
 * 这里k都是1表示k的值对状态没有影响可以直接省略掉
 * dp[n,0] = max(dp[n-1,0],dp[n-1,1] + prices(n))
 * dp[n,1] = max(dp[n-1,1],- prices(n))
 */
public class 股票最大收益 {

    public static void main(String[] args) {
        int[] prices = {1,2};
        System.out.println(maxProfit1_(prices));

        System.out.println(maxProfit2_(prices));

        System.out.println(maxProfit4_(prices));

        System.out.println(maxProfit5_(prices, 0.1));

        System.out.println(maxProfit6(prices, 2));
    }


    //使用DP Table的算法 当k=1时
    public static int maxProfit1(int[] prices) {
        int N = prices.length;
        int[][] dp = new int[N][2];
        for (int n = 0; n < prices.length; n++) {
            if (n == 0) {
                dp[0][0] = 0; //第一天什么也没干
                dp[0][1] = -prices[0]; //第一天就买了
            } else {
                dp[n][0] = Math.max(dp[n - 1][0], dp[n - 1][1] + prices[n]);
                dp[n][1] = Math.max(dp[n - 1][1], -prices[n]);
            }
        }
        return dp[N - 1][0];
    }

    //优化 空间复杂度，由于上一个算法中当天的状态只与前一天的状态有关 只需要保存前一天的状态即可 然后不断更新前一天的状态
    public static int maxProfit1_(int[] prices) {
        int N = prices.length;
        int dp_n_0 = 0;
        int dp_n_1 = -prices[0];
        for (int n = 1; n < N; n++) {
            dp_n_0 = Math.max(dp_n_0, dp_n_1 + prices[n]);
            dp_n_1 = Math.max(dp_n_1, -prices[n]);
        }
        return dp_n_0;
    }

    //第二题  当k为任意值的时候 即k为正无穷 这时可以认为K 和 K-1 是一样的

    /**
     * dp[n][k][1] = Max(dp[n-1][k][1],dp[n-1][k][0] - prices[n])
     * dp[n][k][0] = Max(dp[n-1][k][0],dp[n-1][k][1] + prices[n])
     * <p>
     * k 已经不会改变了，也就是说不需要记录 k 这个状态了
     * dp[n][1] = Max(dp[n-1][1],dp[n-1][0] - prices[n])
     * dp[n][0] = Max(dp[n-1][0],dp[n-1][1] + prices[n])
     */
    public static int maxProfit2(int[] prices) {
        int N = prices.length;
        int[][] dp = new int[N][2];

        for (int n = 0; n < N; n++) {
            if (n == 0) {
                dp[0][0] = 0; //第一天什么也没干
                dp[0][1] = -prices[0]; //第一天就买了
            } else {
                dp[n][1] = Math.max(dp[n - 1][1], dp[n - 1][0] - prices[n]);
                dp[n][0] = Math.max(dp[n - 1][0], dp[n - 1][1] + prices[n]);
            }
        }
        return dp[N - 1][0];
    }

    //  同样的优化
    public static int maxProfit2_(int[] prices) {
        int N = prices.length;
        int dp_n_0 = 0;
        int dp_n_1 = -prices[0];
        for (int n = 1; n < N; n++) {
            int tmp = dp_n_1;//保存昨天的状态
            dp_n_1 = Math.max(dp_n_1, dp_n_0 - prices[n]);

            dp_n_0 = Math.max(dp_n_0, tmp + prices[n]);
        }
        return dp_n_0;

    }

    //第三题 k = +infinity with cooldown 可以有无限次交易但是每次卖之后必须等一天才能买 即有一天的冷冻期

    /**
     * dp[n][0] = Max(dp[n-1][0],dp[n-1][1] + prices[n])
     * dp[n][1] = Max(dp[n-1][1],dp[n-2][0] - prices[n]) //即如果前天持有股票的话 昨天必须卖掉才能不持有股票，但是这样就没有冷冻期了 所以必须是前天就不在持有股票了
     */
    public static int maxProfit4_(int[] prices) {
        int N = prices.length;
        int dp_n_0 = 0;
        int dp_n_1 = -prices[0];
        int dp_n_2_0 = 0;//代表前天的状态
        for (int n = 1; n < N; n++) {
            int tmp = dp_n_0;//保存昨天没有持有股票的状态
            dp_n_0 = Math.max(dp_n_0, dp_n_1 + prices[n]);
            dp_n_1 = Math.max(dp_n_1, dp_n_2_0 - prices[n]);
            dp_n_2_0 = tmp; // 对于下一次循环来说 本次的昨天就是下次的前天
        }
        return dp_n_0;
    }

    //第四题 k = +infinity with fee 每次卖需要付手续费 只需要卖的时候减去手续费即可 手续费是卖出股票价格的比例

    /**
     * dp[n][1] = Max(dp[n-1][1],dp[n-1][0] - prices[n])
     * dp[n][0] = Max(dp[n-1][0],dp[n-1][1] + prices[n] - prices[n]*rate)
     */
    public static double maxProfit5_(int[] prices, double rate) {
        int N = prices.length;
        double dp_n_0 = 0;
        double dp_n_1 = -prices[0];
        for (int n = 1; n < N; n++) {
            dp_n_1 = Math.max(dp_n_1, dp_n_0 - prices[n]);
            dp_n_0 = Math.max(dp_n_0, dp_n_1 + prices[n] - prices[n] * rate);
        }
        return dp_n_0;
    }

    //第五题 当K= 2 或别的值时

    /**
     * dp[n][k][0] = Max(dp[n-1][k][0],dp[n-1][k][1] + price[n])
     * dp[n][k][1] = Max(dp[n-1][k][1],dp[n-1][k-1][0] - price[n])
     */
    public static int maxProfit6(int[] prices, int K) {
        if(prices == null || prices.length <2){
            return 0;
        }
        if(K == 0){
            return 0;
        }
        int N = prices.length;
        int i = N >> 1;
        //N天最多只会有 N/2 次交易
        if(i <= K){
            //此时相当于K为无限大 可以优化为题目2
            return maxProfit2_(prices);
        }
        int[][][] dp = new int[N][K + 1][2];
        for (int n = 0; n < N; n++) {
            for (int k = 1; k < K + 1; k++) {//k 最多允许k次交易 k必须大于0  否则么有意义
                if (n == 0) {
                    dp[n][k][1] = -prices[n];
                    dp[n][k][0] = 0;
                } else {
                    dp[n][k][0] = Math.max(dp[n - 1][k][0], dp[n - 1][k][1] + prices[n]);
                    dp[n][k][1] = Math.max(dp[n - 1][k][1], dp[n - 1][k - 1][0] - prices[n]);
                }
            }
        }
        return dp[N - 1][K][0];
    }


}
