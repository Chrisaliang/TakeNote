package com.chris.eban.domain

import androidx.annotation.IntDef

/**
 * UseCase result, has four status:
 */
class Result<T>(var content: T) {
    @Status
    var status: Int = 0
    var error: ResultException? = null

    @IntDef(STATUS_FILL, STATUS_ERROR)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Status

    companion object {

        const val STATUS_FILL = 0
        const val STATUS_ERROR = 1
    }
}
