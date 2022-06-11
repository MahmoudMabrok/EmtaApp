package edu.mahmoud.emta

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.util.*

class DateOperationTest {

    @Test
    fun strToDate() {
        val dateString = "22 5 2022"
        val actual = DateOperation.strToDate(dateString)
        assertNotNull(actual)
    }

    @Test
    fun dateToString() {
        val cal = Calendar.getInstance()
        cal.set(2022, 4, 22)
        val date = cal.time

        val expected = "22 05 2022"
        val actual = DateOperation.dateToString(date)
        assertEquals(expected, actual)
    }

    @Test
    fun differenceBetweenDatesInDays() {
        val cal = Calendar.getInstance()
        cal.set(2022, 4, 22)
        val date1 = cal.time
        cal.set(2022, 4, 19)
        val date2 = cal.time
        val actual = DateOperation.differenceBetweenDatesInDays(date1, date2)
        val expected = -3L
        assertEquals(expected, actual)
    }


}