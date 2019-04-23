package com.chris.eban.domain


/**
 * generally result exception
 */
class ResultException(message: String, cause: Throwable, // debug message
                      private val debugMessage: String) : Exception(message, cause)
