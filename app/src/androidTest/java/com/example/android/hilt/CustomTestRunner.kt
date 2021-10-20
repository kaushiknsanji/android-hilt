package com.example.android.hilt

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/**
 * A Custom test instrumentation runner to set up the [HiltTestApplication] class
 * for Instrumented Tests.
 */
class CustomTestRunner : AndroidJUnitRunner() {
    /**
     * Perform instantiation of the process's [Application] object.  The
     * default implementation provides the normal system behavior.
     *
     * @param cl The ClassLoader with which to instantiate the object.
     * @param className The name of the class implementing the Application
     * object.
     * @param context The context to initialize the application with
     *
     * @return The newly instantiated Application object.
     */
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}