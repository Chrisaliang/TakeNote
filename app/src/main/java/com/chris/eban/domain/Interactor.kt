package com.chris.eban.domain

/**
 * Basic Executable Unit in business logic.
 * Use SingleUseCase brevity instead of use this class directly.
 * Interactor not hold Presenter or Data layer's status, process and produce data itself.
 *
 * @param <Result>
 * @see SingleUseCase
</Result> */
interface Interactor<Result> {

    /**
     * business logic exe
     */
    fun execute(): Result
}
