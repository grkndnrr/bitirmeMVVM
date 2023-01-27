package com.seniorproject.appModules.network.errors

import java.lang.Exception

open class ErrorStates

object UnknownError: ErrorStates()
object UnAuthorizedToken: ErrorStates()
object JWTExpired: ErrorStates()
object StudentExists : ErrorStates()

object EmailNotConfirmed: ErrorStates()
object StudentNotFound: ErrorStates()
object LecturerNotFound: ErrorStates()
class NetworkError(body: String) : Exception(body)