package com.xbook.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CalcUtil {

    /**
     * 获取价格，保留两位小数
     *
     * @param price
     * @return
     */
    public static double getPrice(double price) {
        BigDecimal bg = new BigDecimal(price);
        return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 格式化价格，保留2位小数
     *
     * @param price
     * @return
     */
    public static String formatData(double price){
        BigDecimal bg = new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP);

        return bg.toString();
    }

    /**
     * 获取价格,保留2位小数
     *
     * @param price
     * @return
     */
    public static double getPrice(String price){
        BigDecimal bg = new BigDecimal(price);
        return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 获取价格，保留2位小数
     *
     * @param price
     * @return
     */
    public static double formatPrice(double price){
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.valueOf(df.format(price));
    }

    /**
     * 增加
     *
     * @param num
     * @param add
     * @return
     */
    public static double add(double num, double add) {
        BigDecimal numDec = new BigDecimal(num);

        numDec = numDec.add(BigDecimal.valueOf(add));

        return numDec.doubleValue();
    }


    public static BigDecimal addBig(double num, double add) {
        BigDecimal numDec = new BigDecimal(num);

        numDec = numDec.add(BigDecimal.valueOf(add));

        return numDec;
    }


    /**
     * 减少
     *
     * @param num
     * @param sub
     * @return
     */
    public static double sub(double num, double sub) {
        BigDecimal numDec = new BigDecimal(num);

        numDec = numDec.subtract(BigDecimal.valueOf(sub));

        return numDec.doubleValue();
    }

    /**
     * 乘以
     *
     * @param num
     * @param add
     * @return
     */
    public static double multiply(double num, double add) {
        BigDecimal numDec = new BigDecimal(num);

        numDec = numDec.multiply(BigDecimal.valueOf(add));

        return numDec.doubleValue();
    }

    /**
     * 乘以
     * @param num
     * @param add
     * @return
     */
    public static BigDecimal multiplyBig(double num, double add) {
        BigDecimal numDec = new BigDecimal(num);

        numDec = numDec.multiply(BigDecimal.valueOf(add));

        return numDec;
    }

    /**
     * 除法
     *
     * @param num
     * @param divisor
     * @return
     */
    public static double divide(double num,double divisor){
        BigDecimal numDec = new BigDecimal(num);

        numDec = numDec.divide(BigDecimal.valueOf(divisor),5,BigDecimal.ROUND_HALF_UP);

        return numDec.doubleValue();
    }
}
