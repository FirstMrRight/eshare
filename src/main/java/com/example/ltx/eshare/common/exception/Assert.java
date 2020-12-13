package com.example.ltx.eshare.common.exception;


import org.apache.commons.lang3.StringUtils;

/**
 * 业务断言
 */
public interface Assert {
    /**
     * 创建异常
     *
     * @param obj
     * @return BusinessException
     */
    BaseException newException(String... obj);

    /**
     * 创建异常
     *
     * @param throwable
     * @param obj
     * @return BusinessException
     */
    BaseException newException(Throwable throwable, String... obj);

    /**
     * 断言非空
     *
     * @param obj
     */
    default void assertNotNull(Object obj) {
        if (obj == null) {
            throw newException(null);
        }
    }

    default void assertNotNull(String str) {
        if (StringUtils.isBlank(str)) {
            throw newException(null);
        }
    }

    /**
     * <p>断言对象<code>obj</code>非空,如果对象<code>obj</code>为空,则抛出异常
     * <p>异常信息<code>msg</code>支持参数方式传递
     *
     * @param obj
     * @param args
     */
    default void assertNotNull(Object obj, String... args) {
        if (obj == null) {
            throw newException(args);
        }
    }

    default void assertEquals(boolean expected, boolean actual) {
        if (expected == actual) {
            throw newException(null);
        }
    }


    default void assertEquals(boolean expected, boolean actual, String... args) {
        if (expected == actual) {
            throw newException(args);
        }
    }


    default void assertEquals(Integer expected, Integer actual) {
        if (expected == actual) {
            throw newException(null);
        }
    }

    default void assertNotEquals(String expected, String actual) {
        if (!expected.equals(actual)) {
            throw newException(null);
        }
    }

    default void assertNotEquals(Long expected, Long actual) {
        if (!expected.equals(actual)) {
            throw newException(null);
        }
    }

}
