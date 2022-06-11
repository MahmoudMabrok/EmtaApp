package edu.mahmoud.emta

import java.text.SimpleDateFormat
import java.util.*

object DateOperation {



    /**
     * string date like "20 8 1996" returns it as Date object
     */
    fun strToDate(strDate: String): Date? {
        val dateFormat = SimpleDateFormat("dd MM yyyy")
        return try {
            dateFormat.parse(strDate)
        } catch (e: Exception) {
            null
        }
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