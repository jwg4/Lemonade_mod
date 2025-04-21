package com.github.jwg4.lemonade_mod.errors

class CommandError(message: String, cause: Throwable) : Error(message, cause)