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

package com.weiwei.extensions.mmkv

import android.content.Context
import androidx.startup.Initializer
import com.tencent.mmkv.MMKV

/**
 * @author weiwei
 * @date 2022.01.13
 */
class MMKVInitializer : Initializer<Unit> {
  override fun create(context: Context) {
    MMKV.initialize(context)
  }

  override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}