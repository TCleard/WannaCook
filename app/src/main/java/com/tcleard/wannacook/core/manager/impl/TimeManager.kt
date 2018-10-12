package com.tcleard.wannacook.core.manager.impl

import com.tcleard.wannacook.core.manager.ITimeManager
import java.util.concurrent.TimeUnit

class TimeManager : ITimeManager {

    override fun getDuration(duration: Long): String =
            if (duration < 1000 * 60) {
                String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(duration))
            } else if (duration < 1000 * 60 * 60) {
                String.format("%d min", TimeUnit.MILLISECONDS.toMinutes(duration))
            } else {
                String.format("%d h, *d min", TimeUnit.MILLISECONDS.toHours(duration), TimeUnit.MILLISECONDS.toMinutes(duration) - TimeUnit.MILLISECONDS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration)))
            }

}