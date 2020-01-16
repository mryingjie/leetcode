package com.github.myyingjie.design.strategy;


import com.github.myyingjie.design.strategy.impl.OrgPrice;

/**
 * @Author ZhengYingjie
 * @Date 2018/10/26
 * @Description 玩家
 */
public class Player {
    /**
     * 玩家消费总额
     */
    private double totalAmount;

    /**
     * 单次消费的金额
     */
    private double amount;

    /**
     * 每个客户都有一个计算价格的策略，初始都是普通计算，即原价
     */
    private CalPriceStrategy calPriceStrategy = new OrgPrice();

    /**
     * 策略工厂
     */
    private CalPriceStrategyFactory instance = CalPriceStrategyFactory.getInstance();

    /**
     * 客户购买商品会增加消费总额 并且判断总额大小自动升级会员
     */
    public void buyGood(Double amount){
        this.amount = amount;
        this.totalAmount+=amount;

//        //总额超过30000升级为金牌会员
//        if(totalAmount>=30000){
//            this.calPriceStrategy = new GoldPrice();
//        }else if(totalAmount>=20000){
//            //超过20000升级为超级会员
//            this.calPriceStrategy = new SuoerVipPrice();
//        }else if(totalAmount>=10000){
//            //超过10000升级为普通会员
//            this.calPriceStrategy = new VipPrice();
//        }

        //使用简单工厂优化
        try {

            this.calPriceStrategy = instance.createCalPriceStategy(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 计算用户最后要付的钱
     * @return
     */
    public Double calLastPrice(){
        return this.calPriceStrategy.calPrice(this.amount);
    }


    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

//    public CalPriceStrategy getCalPriceStrategy() {
//        return calPriceStrategy;
//    }
//
//    public void setCalPriceStrategy(CalPriceStrategy calPriceStrategy) {
//        this.calPriceStrategy = calPriceStrategy;
//    }
}
