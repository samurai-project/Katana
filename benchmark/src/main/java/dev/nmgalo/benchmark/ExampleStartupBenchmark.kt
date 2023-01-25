package dev.nmgalo.benchmark

import androidx.benchmark.macro.junit4.BaselineProfileRule
import org.junit.Rule
import org.junit.Test

class ExampleStartupBenchmark {

    @get:Rule
    val baselineProfileRule = BaselineProfileRule()

    @Test
    fun generate() = baselineProfileRule.collectBaselineProfile(
        packageName = "dev.nmgalo.katana",
        iterations = 10,
        profileBlock = {
            pressHome()
            startActivityAndWait()
        }
    )
}
