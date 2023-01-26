package dev.nmgalo.benchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppStartupBenchmark {

    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startUpCompilationModelNone() = startup(CompilationMode.None())

    @Test
    fun startUpCompilationModelPartial() = startup(CompilationMode.Partial())

    private fun startup(mode: CompilationMode) = benchmarkRule.measureRepeated(
        packageName = "dev.nmgalo.katana",
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD,
        compilationMode = mode
    ) {
        pressHome()
        startActivityAndWait()
    }
}
