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

package com.weicools.core.app

import com.weicools.core.app.theme.AppTheme
import com.weicools.core.app.theme.ThemeManager

/**
 * @author weiwei
 * @date 2022.01.04
 */
abstract class BaseThemeFragment : BaseFragment() {

  override fun onResume() {
    getThemeManager().getCurrentLiveTheme().observe(this) {
      syncTheme(it)
    }

    super.onResume()
  }

  protected fun getThemeManager(): ThemeManager = ThemeManager.instance

  // to sync ui with selected theme
  abstract fun syncTheme(appTheme: AppTheme)
}