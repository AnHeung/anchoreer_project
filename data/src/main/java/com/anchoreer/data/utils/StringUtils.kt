package com.anchoreer.data.utils

fun String?.deleteBracket() = this?.replace(Regex("(<([^>]+)>)"), "") ?: ""

fun String?.changePipe(changeItem: String) = this?.replace("|" , changeItem)?.run {
    val lastItem = lastOrNull()
    if(changeItem.isNotEmpty() && lastItem ==changeItem[0]) return@run substring(0,lastIndex-1)
    return@run this
}?:""