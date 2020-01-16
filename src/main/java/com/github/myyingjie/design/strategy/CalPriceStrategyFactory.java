package com.github.myyingjie.design.strategy;


import com.github.myyingjie.design.strategy.annotation.PriceAnnotation;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ZhengYingjie
 * @Date 2018/10/26
 * @Description 收费策略的工厂类  决定具体使用哪种策略
 */
public class CalPriceStrategyFactory {

    //要扫描的注解的包
    private static final String CAL_PRICE_PACKAGE = "com.github.myyingjie.design.strategy.impl";

    private ClassLoader classLoader = getClass().getClassLoader();

    private List<Class<? extends CalPriceStrategy>> calPriceStategyList;

    private static volatile CalPriceStrategyFactory calPriceStrategyFactory;


    private CalPriceStrategyFactory(){
        init();
    }

    public static CalPriceStrategyFactory getInstance(){
        if(calPriceStrategyFactory == null){
            synchronized (CalPriceStrategyFactory.class){
                if(calPriceStrategyFactory == null){
                    calPriceStrategyFactory = new CalPriceStrategyFactory();
                }
            }
        }
        return calPriceStrategyFactory;
    }



    //工厂初始化要初始化策略列表
    private void init(){
        calPriceStategyList = new ArrayList<>();
        File[] resources = getResources();//获取包下所有的class文件
        try {
            Class<?> calPriceStrategyClass = classLoader.loadClass(CalPriceStrategy.class.getName());
            for (int i = 0; i < resources.length; i++) {
                File resource = resources[i];
                System.out.println((i+1)+":"+resource);
                //载入包下的策略类
                Class<?> aClass = classLoader.loadClass(CAL_PRICE_PACKAGE + "." + resource.getName().replace(".class", ""));
                //判断是否是CalPriceStrategy的实现类 并且不是CalPriceStrategy它本身，满足的话加入到策略列表
                if(calPriceStrategyClass.isAssignableFrom(aClass) && aClass!=calPriceStrategyClass){
                    calPriceStategyList.add((Class<? extends CalPriceStrategy>) aClass);
                }

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取扫描包下所有的class文件
    private File[] getResources() {
        try {
            File file = new File(classLoader.getResource(CAL_PRICE_PACKAGE.replace(".", "/")).toURI());
            return file.listFiles(pathname -> pathname.getName().endsWith(".class"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //处理注解,传入一个策略类，返回他的注解
    private PriceAnnotation handleAnnotation(Class<? extends CalPriceStrategy> clazz){
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        if(annotations == null || annotations.length==0){
            return null;
        }
        for (int i = 0; i < annotations.length; i++) {
            Annotation annotation = annotations[i];
            if(annotation instanceof PriceAnnotation){
                return (PriceAnnotation) annotation;
            }
        }
        return null;
    }

    //根据玩家消费的总金额产生相应的策略
    public CalPriceStrategy createCalPriceStategy(Player player) throws Exception {
        //在策略列表查找策略
        for (Class<? extends CalPriceStrategy> clazz : calPriceStategyList) {
            //获取该策略的注解
            PriceAnnotation priceAnnotation = handleAnnotation(clazz);
            int max = priceAnnotation.max();
            int min = priceAnnotation.min();
            if(player.getTotalAmount()>=min && player.getTotalAmount()<=max){
                try {
                    return clazz.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new Exception("策略获取失败");
    }

}
