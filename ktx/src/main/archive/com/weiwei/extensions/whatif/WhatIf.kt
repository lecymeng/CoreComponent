@file:Suppress("unused")
@file:JvmName("WhatIf")
@file:JvmMultifileClass

package com.weiwei.ktx.whatif

/**
 * WhatIf is kotlin extensions for expressing a single if-else statement, nullable and boolean.
 */

/**
 * An expression for invoking [whatIf] when the [given] boolean is true.
 *
 * @param given A given condition for executing the [whatIf] lambda.
 * @param whatIf An executable lambda function if the [given] condition pass.
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun <T> T.whatIf(
  given: (T) -> Boolean?,
  whatIf: () -> Unit
): T {

  if (given(this) == true) {
    whatIf()
  }
  return this
}

/**
 * An expression for invoking [whatIf] when the [given] boolean is true.
 * If the [given] boolean is false, [whatIfNot] will be invoked instead of the [whatIf].
 *
 * @param given A given condition for executing the [whatIf] or [whatIfNot] lambda.
 * @param whatIf An executable lambda if the [given] condition pass.
 * @param whatIfNot An executable lambda function if the [given] condition would not pass.
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun <T> T.whatIf(
  given: (T) -> Boolean?,
  whatIf: () -> Unit,
  whatIfNot: () -> Unit
): T {

  if (given(this) == true) {
    whatIf()
  } else {
    whatIfNot()
  }
  return this
}

/**
 * An expression for invoking [whatIf] when the [given] boolean is true.
 * So it is useful when using with a chaining function like builder pattern or [apply] expression in kotlin.
 *
 * @param given A given condition for executing the [whatIf] lambda.
 * @param whatIf An executable lambda if the [given] condition pass.
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun <T> T.whatIf(
  given: Boolean?,
  whatIf: T.() -> Unit
): T {

  if (given == true) {
    this.apply { whatIf() }
  }
  return this
}

/**
 * An expression for invoking [whatIf] when the [given] boolean is true.
 * If the [given] boolean is false, [whatIfNot] will be invoked instead of the [whatIf].
 * So it is useful when using with a chaining function like builder pattern or [apply] expression in kotlin.
 *
 * @param given A given condition for executing the [whatIf] or [whatIfNot] lambda.
 * @param whatIf An executable lambda if the [given] condition pass.
 * @param whatIfNot An executable lambda function if the [given] condition would not pass.
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun <T> T.whatIf(
  given: Boolean?,
  whatIf: T.() -> Unit,
  whatIfNot: T.() -> Unit
): T {

  if (given == true) {
    this.apply { whatIf() }
  } else {
    this.apply { whatIfNot() }
  }
  return this
}

/**
 * An expression for invoking [whatIf] when the [given] lambda's return value is true.
 * So it is useful when using with a chaining function like builder pattern or [apply] expression in kotlin.
 *
 * @param given A given condition for executing the [whatIfDo] lambda.
 * @param whatIfDo An executable lambda if the [given] condition pass.
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun <T> T.whatIf(
  given: () -> Boolean?,
  whatIfDo: T.() -> Unit
): T {

  return this.whatIf(
    given = given,
    whatIfDo = whatIfDo,
    whatIfNot = { }
  )
}

/**
 * An expression for invoking [whatIf] when the [given] lambda's return value is true.
 * If the [given] boolean is false, [whatIfNot] will be invoked instead of the [whatIfDo].
 * So it is useful when using with a chaining function like builder pattern or [apply] expression in kotlin.
 *
 * @param given A given condition for executing the [whatIfDo] or [whatIfNot] lambda.
 * @param whatIfDo An executable lambda if the [given] condition pass.
 * @param whatIfNot An executable lambda function if the [given] condition would not pass.
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun <T> T.whatIf(
  given: () -> Boolean?,
  whatIfDo: T.() -> Unit,
  whatIfNot: T.() -> Unit
): T {

  if (given() == true) {
    this.whatIfDo()
  } else {
    this.whatIfNot()
  }
  return this
}

/**
 * An expression for invoking [whatIf] when the [T] target object is not null.
 * It is useful when the receiver [T] and the result [R] should be different.
 *
 * @param default An executable default value if the [T] target object is not null.
 * @param whatIf An executable lambda if the [T] target object is not null.
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun <T, R> T.whatIfMap(
  default: R,
  whatIf: (T) -> R
): R {

  return this.whatIfMap(
    whatIf = whatIf,
    whatIfNot = { default }
  )
}

/**
 * An expression for invoking [whatIf] when the [T] target object is not null.
 * It is useful when the receiver [T] and the result [R] should be different.
 *
 * @param whatIf An executable lambda if the [T] target object is not null.
 * @param whatIfNot An executable lambda function if the [T] target object is null.
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun <T, R> T.whatIfMap(
  whatIf: (T) -> R,
  whatIfNot: (T) -> R
): R {

  if (this != null) {
    return whatIf(this)
  }
  return whatIfNot(this)
}

/**
 * An expression for invoking [whatIf] when the [given] boolean value.
 * If the [given] boolean value is false, the result value is the [default].
 * It is useful when the receiver [T] and the result [R] should be different.
 *
 * @param given A given condition for executing the [whatIf] lambda.
 * @param default An executable default value if the [given] condition would not pass.
 * @param whatIf An executable lambda if the [given] condition pass.
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun <T, R> T.whatIfMap(
  given: Boolean?,
  default: R,
  whatIf: (T) -> R
): R {

  return this.whatIfMap(
    given = given,
    whatIf = whatIf,
    whatIfNot = { default }
  )
}

/**
 * An expression for invoking [whatIf] when the [given] boolean value.
 * If the [given] boolean is false, [whatIfNot] will be invoked instead of the [whatIf].
 * It is useful when the receiver [T] and the result [R] should be different.
 *
 * @param given A given condition for executing the [whatIf] lambda.
 * @param whatIf An executable lambda if the [given] condition pass.
 * @param whatIfNot An executable lambda function if the [given] condition would not pass.
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun <T, R> T.whatIfMap(
  given: Boolean?,
  whatIf: (T) -> R,
  whatIfNot: (T) -> R
): R {

  if (given == true) {
    return whatIf(this)
  }
  return whatIfNot(this)
}

/**
 * An expression for invoking [whatIf] when the [given] boolean value.
 * If the [given] boolean value is false, the result value is the [default].
 * It is useful when the receiver [T] and the result [R] should be different.
 *
 * @param given A given condition for executing the [whatIf] lambda.
 * @param default An executable default value if the [given] condition would not pass.
 * @param whatIf An executable lambda if the [given] condition pass.
 *
 * @return Returns the desired type of object.
 */
@Deprecated(
  message = "use whatIfMap instead.",
  replaceWith = ReplaceWith(
    "whatIfMap(given, default, whatIf)",
    imports = ["com.weiwei.extensions.whatIf.whatIfMap"]
  )
)
@JvmSynthetic
@WhatIfInlineOnly
inline fun <T, R> T.whatIfLet(
  given: Boolean?,
  default: R,
  whatIf: (T) -> R
): R {

  if (given == true) {
    return whatIf(this)
  }
  return default
}

/**
 * An expression for invoking [whatIf] when the [given] boolean value.
 * If the [given] boolean is false, [whatIfNot] will be invoked instead of the [whatIf].
 * It is useful when the receiver [T] and the result [R] should be different.
 *
 * @param given A given condition for executing the [whatIf] lambda.
 * @param whatIf An executable lambda if the [given] condition pass.
 * @param whatIfNot An executable lambda function if the [given] condition would not pass.
 *
 * @return Returns the desired type of object.
 */
@Deprecated(
  message = "use whatIfMap instead.",
  replaceWith = ReplaceWith(
    "whatIfMap(given, default, whatIf, whatIfNot)",
    imports = ["com.weiwei.extensions.whatIf.whatIfMap"]
  )
)
@JvmSynthetic
@WhatIfInlineOnly
inline fun <T, R> T.whatIfLet(
  given: Boolean?,
  whatIf: (T) -> R,
  whatIfNot: (T) -> R
): R {

  if (given == true) {
    return whatIf(this)
  }
  return whatIfNot(this)
}

/**
 * An expression for invoking [whatIf] when the [T] target object is not null.
 *
 * @param whatIf An executable lambda if a target object is not null.
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun <T> T?.whatIfNotNull(
  whatIf: (T) -> Unit
): T? {

  return this.whatIfNotNull(
    whatIf = whatIf,
    whatIfNot = { }
  )
}

/**
 * An expression for invoking [whatIf] when the [T] target object is not null.
 * If the [T] target is null, [whatIfNot] will be invoked instead of the [whatIf].
 *
 * @param whatIf An executable lambda if a target object is not null.
 * @param whatIfNot An executable lambda if a target object is null.
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun <T> T?.whatIfNotNull(
  whatIf: (T) -> Unit,
  whatIfNot: () -> Unit
): T? {

  if (this != null) {
    whatIf(this)
    return this
  }
  whatIfNot()
  return this
}

/**
 * An expression for invoking [whatIf] when the target object is not null.
 * If the target is not null and the target can be cast by the desired type [R],
 * the receiver will get a casted [R] type.
 *
 * ```
 * parcelable.whatIfNotNullAs<Poster> { poster ->
 *  log(poster.name)
 * }
 * ```
 *
 * @param whatIf An executable lambda will receive a casted [R]
 * if a target object is not null and the target can be cast by the desired type [R].
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun <reified R> Any?.whatIfNotNullAs(
  whatIf: (R) -> Unit
): Any? {

  return whatIfNotNullAs(
    whatIf = whatIf,
    whatIfNot = { }
  )
}

/**
 * An expression for invoking [whatIf] when the target object is not null.
 * If the target is not null and the target can be cast by the desired type [R],
 * the receiver will get a casted [R] type.
 * If the target is null, [whatIfNot] will be invoked instead of the [whatIf] without casting.
 *
 * ```
 *  serializable.whatIfNotNullAs<Poster>(
 *  whatIf = { poster -> log(poster.name) },
 *  whatIfNot = {
 *    // do something
 *  })
 * ```
 *
 * @param whatIf @param whatIf An executable lambda will receive a casted [R]
 * if a target object is not null and the target can be cast by the desired type [R].
 * @param whatIfNot An executable lambda if a target object is null or can not be cast by the desired type [R].
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun <reified R> Any?.whatIfNotNullAs(
  whatIf: (R) -> Unit,
  whatIfNot: () -> Unit
): Any? {

  if (this != null && this is R) {
    whatIf(this as R)
    return this
  }
  whatIfNot()
  return this
}

/**
 * An expression for invoking [whatIf] when the [T] target object is not null.
 * If the [T] target is null, [whatIfNot] will be invoked instead of the [whatIf].
 * It is useful when the receiver [T] and the result [R] should be different.
 *
 * @param whatIf An executable lambda if a target object is not null and returns a different type [R].
 * @param whatIfNot An executable lambda if a target object is null and returns a different type [R].
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun <T, R> T?.whatIfNotNullWith(
  whatIf: (T) -> R,
  whatIfNot: (T?) -> R
): R {

  if (this != null) {
    return whatIf(this)
  }
  return whatIfNot(this)
}

/**
 * An expression for invoking [whatIf] when the target [Boolean] is not null and true.
 *
 * @param whatIf An executable lambda function if the [Boolean] is not null and true.
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun Boolean?.whatIf(
  whatIf: () -> Unit
): Boolean? {

  return this.whatIf(
    whatIf = whatIf,
    whatIfNot = { }
  )
}

/**
 * An expression for invoking [whatIf] when the target object is not null and true.
 * If the target is null or false, [whatIfNot] will be invoked instead of the [whatIf].\
 *
 * @param whatIf An executable lambda function if the [Boolean] is not null and true.
 * @param whatIfNot An executable lambda function if the [Boolean] is null or false.
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun Boolean?.whatIf(
  whatIf: () -> Unit,
  whatIfNot: () -> Unit
): Boolean? {

  if (this == true) {
    whatIf()
  } else {
    whatIfNot()
  }
  return this
}

/**
 * An expression for invoking [whatIf] when the target object is not null and false.
 *
 * @param whatIf An executable lambda function if the [Boolean] is null or false.
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun Boolean?.whatIfElse(
  whatIf: () -> Unit
): Boolean? {

  if (this == false) {
    whatIf()
  }
  return this
}

/**
 * An expression for invoking [whatIf] when the target Boolean is true and the [predicate] is also true.
 *
 * @param predicate A predicate value
 * @param whatIf An executable lambda function if the [Boolean] and predicate are both not null and true.
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun Boolean?.whatIfAnd(
  predicate: Boolean?,
  whatIf: () -> Unit
): Boolean? {

  if (this == true && predicate == true) {
    whatIf()
  }
  return this
}

/**
 * An expression for invoking [whatIf] when the target Boolean is true or the [predicate] is true.
 *
 * @param predicate A predicate value.
 * @param whatIf An executable lambda function if the [Boolean] or predicate is not null and true.
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
inline fun Boolean?.whatIfOr(
  predicate: Boolean?,
  whatIf: () -> Unit
): Boolean? {

  if (this == true || predicate == true) {
    whatIf()
  }
  return this
}
