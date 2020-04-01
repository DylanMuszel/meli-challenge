package com.dylanmuszel.melichallenge

import java.util.concurrent.Executors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class CoroutineTestRule : TestRule {

    override fun apply(base: Statement, description: Description) = MyStatement(base)

    class MyStatement(private val base: Statement) : Statement() {

        private val mainThreadSurrogate = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

        @ExperimentalCoroutinesApi
        @Throws(Throwable::class)
        override fun evaluate() {

            Dispatchers.setMain(mainThreadSurrogate)

            try {
                base.evaluate() // This executes your tests
            } finally {
                Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
                mainThreadSurrogate.close()
            }
        }
    }
}
