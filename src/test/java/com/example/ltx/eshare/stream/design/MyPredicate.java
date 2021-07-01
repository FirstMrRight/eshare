package com.example.ltx.eshare.stream.design;

/**
 * @Author: LiuTX
 * @Date: 2021/6/30 10:21
 */
public interface MyPredicate<T> {
    /**
     * 对传递过来的T类型的数据进行过滤
     * 符合规则返回true,不符合规则返回false
     * @param t
     * @return
     */
    boolean filter(T t);
}
