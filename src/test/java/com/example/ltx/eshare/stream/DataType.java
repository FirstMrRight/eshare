package com.example.ltx.eshare.stream;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author Liutx
 * @date 2021/4/22 16:03
 * @Description
 */
public class DataType {
    public static void main(String[] args) {
//        System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));
//        System.out.println(new BigDecimal("4.015").multiply(BigDecimal.valueOf(100)));
        //3.350000000000000088817841970012523233890533447265625
        double num1 = 3.35;
        //3.349999904632568359375
        float num2 = 3.35f;
//        System.out.println(String.format("%.1f", num1));//四舍五入
//        System.out.println(String.format("%.1f", num2));

        DecimalFormat format = new DecimalFormat("#.##");
        //四舍五入策略   StringFormat使用的是HALF_UP
        format.setRoundingMode(RoundingMode.DOWN);
        System.out.println(format.format(num1));
        format.setRoundingMode(RoundingMode.DOWN);
        System.out.println(format.format(num2));

        //正确示例:
        BigDecimal numRight1 = new BigDecimal("3.35");
        BigDecimal numRight2 = numRight1.setScale(1,BigDecimal.ROUND_DOWN);
        System.out.println(numRight2);
        BigDecimal numRight3 = numRight1.setScale(1,BigDecimal.ROUND_HALF_UP);
        System.out.println(numRight3);
    }
}
