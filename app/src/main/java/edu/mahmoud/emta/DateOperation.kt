package edu.mahmoud.emta

import java.text.SimpleDateFormat
import java.util.*

object DateOperation {


    /**
     * string date like "20 8 1996" returns it as Date object
     */
    fun strToDate(strDate: String): Date? {
        val parts = strDate.trim().split(" ")
        if (parts.size != 3)
            return null

        val cal = Calendar.getInstance()
        cal.set(parts[2].toInt(), parts[1].toInt() - 1, parts[1].toInt())

        return cal.time
    }

    /**
     * string date like "20 8 1996" returns it as Date object
     */
    fun dateToString(date: Date): String? {
        val dateFormat = SimpleDateFormat("dd MM yyyy")
        return dateFormat.format(date)
    }


    fun differenceBetweenDatesInDays(startDate: Date, endDate2: Date): Long {
        val diffMillis = endDate2.time - startDate.time
        return diffMillis / 1000 / 60 / 60 / 24
    }

    fun getDifferenceTillNow(date: Date) = differenceBetweenDatesInDays(getCurrentDate(), date)

    fun getCurrentDate(): Date {
        return Calendar.getInstance().time
    }


    fun getDateFromMillis(millis: Long): Date {
        return Date(millis)
    }


}