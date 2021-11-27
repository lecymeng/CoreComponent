/*
 * Copyright (c) 2021 Weiwei. https://github.com/lecymeng
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

package com.weicools.ktx.core

import android.content.Context
import android.util.Log
import androidx.startup.Initializer

/**
 * @author weiwei
 * @date 2021.11.27
 */
class KtxInitializer : Initializer<Unit> {
  override fun create(context: Context) {
    Log.d("KtxInitializer", "create: ")
    KtxGlobal.appContext = context
  }

  override fun dependencies(): List<Class<out Initializer<*>>> {
    return emptyList()
  }
}