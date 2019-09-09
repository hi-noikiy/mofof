package com.mofof.repository;

/**
 * Created by ChenErliang on 2017/8/6.
 */
public interface FinanceRepositoryCustom<T> {

    void detach(T t);
}
