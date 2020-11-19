package com.ehmaugbogo.zarisms.util.mail_helper

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 2020-11-18
 */

sealed class Result {
    data class Success(val msg: String): Result()
    data class Failure(val error: Throwable): Result()
}