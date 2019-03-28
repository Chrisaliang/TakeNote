package com.chris.eban.domain;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Single result emitter
 */
public abstract class SingleUseCase<T> implements Interactor<Single<Result<T>>> {


    @Override
    public Single<Result<T>> execute() {
        final Single<T> real = buildSingle();
        if (real == null)
            throw new IllegalArgumentException("Build a UseCase with any params! ");
        return real.map(new Function<T, Result<T>>() {
            @Override
            public Result<T> apply(T t) {
                return new Result<>(t);
            }
        });
    }

    protected abstract Single<T> buildSingle();
}
