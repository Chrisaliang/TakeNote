package com.chris.eban

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val pattern: String = "yyyyMMMdd"
    private val patternToday: String = "HH:mm:ss"

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testTime() {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        println(sdf.format(Date()))
        sdf.applyPattern(patternToday)
        println(sdf.format(Date()))


        val daySecond = 1000 * 60 * 60 * 24L
        val now = (System.currentTimeMillis() % daySecond) * daySecond
//        val dayTime = now - ((now + 8 * 3600) % daySecond)

//        sdf.applyPattern(pattern)
        println(sdf.format(Date(now)))


        val calendar = Calendar.getInstance()

        calendar.time = Date()
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        val start = calendar.time

        sdf.applyPattern(pattern)
        val ss: String = sdf.format(Date(start.time))
        println(ss)

        val days = ((Date().time - start.time) / (1000 * 60 * 60 * 24L))

        println(days)

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date(time);
//        Date old = sdf.parse(sdf.format(date));
//        Date now = sdf.parse(sdf.format(new Date()));
//        long oldTime = old.getTime();
//        long nowTime = now.getTime();
//
//        long day = (nowTime - oldTime) / (24 * 60 * 60 * 1000);
//
//        if (day < 1) {  //今天
//            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
//            return format.format(date);
//        } else if (day == 1) {     //昨天
//            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
//            return "昨天 " + format.format(date);
//        } else {    //可依次类推
//            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
//            return format.format(date);
//        }
    }
}
