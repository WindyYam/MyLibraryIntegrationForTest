package com.example.myassessmentlibrary

import com.example.mylibrarytest.Utils
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    @Test
    fun date_tran() {
        assertEquals(Utils.getDateFromLong(1622118426534L), "27 May 2021");
    }

    @Test
    fun datetime_tran() {
        assertEquals(Utils.getDateTimeFromDate(Date(1622118426534L)), "22:27:06 2021 May 27");
    }
}