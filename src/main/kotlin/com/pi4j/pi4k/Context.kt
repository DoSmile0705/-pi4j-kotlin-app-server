package com.pi4j.pi4k

import com.pi4j.Pi4J
import com.pi4j.config.Config
import com.pi4j.context.Context
import com.pi4j.context.ContextBuilder
import com.pi4j.io.IO
import com.pi4j.platform.Platform
import com.pi4j.provider.Provider
import java.io.File
import java.io.InputStream
import java.io.Reader
import java.util.*

/**
 * @author Muhammad Hashim (mhashim6) (<a href="https://mhashim6.me">https://mhashim6.me</a>) on 10/9/22
 */

@DslMarker
annotation class ContextBuilderMarker

@ContextBuilderMarker
 class KontextBuilder: ContextBuilder by ContextBuilder.newInstance() {
    operator fun Platform.unaryPlus() {
        add(this)
    }
    operator fun Provider<out Provider<*, *, *>, out IO<*, *, *>, out Config<*>>?.unaryPlus() {
        add(this)
    }

    operator fun Properties.unaryPlus() {
        add(this)
    }
    operator fun Pair<String, String>.unaryPlus() {
        addProperty(this.first, this.second)
    }
    operator fun File.unaryPlus() {
        addProperties(this)
    }
    operator fun Reader.unaryPlus() {
        addProperties(this)
    }
    operator fun InputStream.unaryPlus() {
        addProperties(this)
    }
}


inline fun withinAutoContext(block: Context.() -> Unit): Context {
    val context = Pi4J.newAutoContext()
    context.run(block)
    return context
}

inline fun buildContext(builder: KontextBuilder.() -> Unit): Context {
    return KontextBuilder().run {
        builder.invoke(this)
        build()
    }
}
