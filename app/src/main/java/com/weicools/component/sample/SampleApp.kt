package com.weicools.component.sample

import android.app.Application
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.weicools.component.sample.preferences.PreferencesProvider
import com.weicools.utils.process.ProcessUtils

/**
 * @author Weicools
 *
 * @date 2021.07.04
 */
class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (mainProcess()) {
            Log.d("SampleApp", "onCreate: mainProcess")
            contentResolver.call(PreferencesProvider.getPreferenceUri(), "GET_NAME", null, Bundle())
        } else if (workProcess()) {
            Log.d("SampleApp", "onCreate: workProcess")
        }
    }

    private fun mainProcess(): Boolean {
        val name = ProcessUtils.getProcessName()
        return packageName.equals(name) || TextUtils.isEmpty(name)
    }

    private fun workProcess(): Boolean {
        val name = ProcessUtils.getProcessName()
        return name.endsWith("work")
    }
}