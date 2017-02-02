package com.segueme.seguemepsul.mvp;

/**
 * Created by FernandoDias on 26/01/17.
 */

public interface BaseMVP<T> {

    void startMVP();
    void initialize(T requiredViewOps);
    void reInitialize(T requiredViewOps);
}
