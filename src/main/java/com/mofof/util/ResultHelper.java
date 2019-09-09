package com.mofof.util;


import com.mofof.util.enumeration.ResultCode;

/**
 * 返回结果封装工具类
 * @author hzh
 * @date 2019-06-07
 */
public class ResultHelper {
    /**
     * 操作成功(带返回数据)
     * @param object
     * @return
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getValue());
        result.setMsg(ResultCode.SUCCESS.getName());
        result.setData(object);
        return result;
    }

    /**
     * 操作成功(不带返回数据)
     * @param
     * @return
     */
    public static Result success() {
        return success(null);
    }


    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(Exception e) {
        Result result = new Result();
        result.setCode(ResultCode.ERROR.getValue());
        result.setMsg(ResultCode.ERROR.getName()+":"+e.getMessage()+" 异常类型:"+e.getClass().getName());
        return result;
    }
}
