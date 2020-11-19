package com.ehmaugbogo.zarisms.model

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 2020-11-17
 */

data class MainItem(
    val title: String,
    val desc: String = "",
    val icon: Int,
    val destination: Int
)