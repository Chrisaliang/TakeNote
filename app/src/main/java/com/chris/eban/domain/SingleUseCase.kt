package com.chris.eban.domain

import io.reactivex.Single

/**
 * Single result emitter
 */
abstract class SingleUseCase<T> : Interactor<Single<Result<T>>> {


    override fun execute(): Single<Result<T>> {
        val real = buildSingle()
                ?: throw IllegalArgumentException("Build a UseCase with any params! ")
        return real.map { t -> Result(t) }
    }

    protected abstract fun buildSingle(): Single<T>?
}
