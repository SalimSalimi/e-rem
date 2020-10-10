package dz.salim.salimi.e_rem.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun getCurrentTime(): String {

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm")
        current.format(formatter)
    } else {
        val date = Date()
        val formatter = SimpleDateFormat("dd.MM.yyyy. HH:mm")
        formatter.format(date)
    }
}