package com.mendelu.xstast12.zooexplorer.architecture

data class CommunicationError(
    val code: Int,
    var message: String?,
    var secondaryMessage: String? = null)