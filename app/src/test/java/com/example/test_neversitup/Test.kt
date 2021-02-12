package com.example.test_neversitup

import com.example.test_neversitup.extention.validateEmail
import org.hamcrest.core.Is
import org.junit.Assert
import org.junit.Test

class Test {
    @Test
    fun testEmailValidityPartTwo() {
        val testEmail = "test@gmail.com"
        Assert.assertThat(
            testEmail.validateEmail(),
            Is.`is`(false)
        )
    }
}