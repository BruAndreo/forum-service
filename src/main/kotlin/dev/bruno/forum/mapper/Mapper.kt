package dev.bruno.forum.mapper

interface Mapper<T, U> {
    fun map(t: T): U
}