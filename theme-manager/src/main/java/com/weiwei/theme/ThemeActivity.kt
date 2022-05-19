/*
 * Copyright (c) 2022 Weiwei. https://github.com/lecymeng
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.weiwei.theme

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * @author weiwei
 * @date 2022.02.28
 */
@Suppress("MemberVisibilityCanBePrivate")
abstract class ThemeActivity : AppCompatActivity() {

  protected val themeViewDelegate by lazy(LazyThreadSafetyMode.NONE) {
    ThemeViewDelegate()
  }

  private fun createThemeView() {
    ThemeManager.instance.init(this, getStartTheme())
    themeViewDelegate.createThemeView(this)
    super.setContentView(themeViewDelegate.root)
  }

  override fun setContentView(@LayoutRes layoutResID: Int) {
    themeViewDelegate.setContentView(this, layoutResID)
  }

  override fun setContentView(view: View?) {
    themeViewDelegate.setContentView(view)
  }

  override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
    themeViewDelegate.setContentView(view, params)
  }

  override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
    super.onCreate(savedInstanceState, persistentState)

    createThemeView()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    createThemeView()
  }

  override fun onResume() {
    super.onResume()

    ThemeManager.instance.setActivity(this)
    ThemeManager.instance.getCurrentTheme()?.let { syncTheme(it) }
  }

  fun changeTheme(newTheme: AppTheme, sourceCoordinate: Coordinate, animDuration: Long, isReverse: Boolean) {
    themeViewDelegate.changeTheme(newTheme, sourceCoordinate, animDuration, isReverse) {
      syncTheme(newTheme)
    }
  }

  // to sync ui with selected theme
  abstract fun syncTheme(appTheme: AppTheme)

  // to save the theme for the next time, save it in onDestroy() (exp: in pref or DB) and return it here.
  // it just used for the first time (first activity).
  abstract fun getStartTheme(): AppTheme

}