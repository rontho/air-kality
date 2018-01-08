package com.splashcode.airkality.domain.usecase

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

open class AbstractUseCase<T> {
    fun run(usecase: () -> T, ui: (T) -> Unit) {
        doAsync {

            val model = usecase()

            uiThread {
                ui(model)
            }
        }
    }
}