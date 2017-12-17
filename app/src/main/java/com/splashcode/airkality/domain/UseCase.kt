package com.splashcode.airkality.domain

interface UseCase<T> {
    fun execute(): T
}