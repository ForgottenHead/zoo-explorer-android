package com.mendelu.xstast12.zooexplorer.utils

import com.google.ar.core.Pose
import kotlin.math.sqrt

object ARUtils {

    fun getDistanceToObject(object1Pose: Pose, object2Pose: Pose): Float {
        val distanceX = object1Pose.tx() - object2Pose.tx()
        val distanceY = object1Pose.ty() - object2Pose.ty()
        val distanceZ = object1Pose.tz() - object2Pose.tz()
        return sqrt((distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ).toDouble())
            .toFloat()
    }
}