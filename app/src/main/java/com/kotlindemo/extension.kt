package com.kotlindemo

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by ankit on 30/5/17.
 */
inline fun <reified T> Gson.fromJson(json: String): T {
    return this.fromJson<T>(json, object: TypeToken<T>() {}.type)
}
