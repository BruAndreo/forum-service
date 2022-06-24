package dev.bruno.forum.exceptions

class NotFoundException(override val message: String?): RuntimeException(message) {
}