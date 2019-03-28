package com.chris.eban.domain;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

/**
 * UseCase result, has four status:
 */
@SuppressWarnings("WeakerAccess")
public class Result<T> {

    public static final int STATUS_FILL = 0;
    public static final int STATUS_ERROR = 1;
    @Status
    public int status;
    public T content;
    public ResultException error;

    public Result(T content) {
        this.content = content;
    }

    @IntDef({STATUS_FILL, STATUS_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
    }
}
