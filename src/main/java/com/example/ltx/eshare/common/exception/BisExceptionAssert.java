package com.example.ltx.eshare.common.exception;


public interface BisExceptionAssert extends IResponseEnum, Assert {

    @Override
    default BisException newException(String... args) {
        String msg = "";
        if (args != null && args.length != 0) {
            msg = String.format("%s", args);
        }
        return new BisException(this, args, msg);
    }

    @Override
    default BisException newException(Throwable throwable, String... args) {
        String msg = "";
        if (args != null && args.length != 0) {
            msg = String.format("%s", args);
        }
        return new BisException(this, args, msg, throwable);
    }
}
