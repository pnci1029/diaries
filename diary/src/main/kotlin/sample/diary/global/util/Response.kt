package sample.diary.global.util

import org.springframework.http.HttpStatus

class Response<T>(
    val httpStatus: HttpStatus,
    val statusNumber: Number,
    val data: T
){
    companion object {
        fun <T> result(data: T, status: HttpStatus): Response<T> {
            return Response(status, status.value(), data)
        }
    }
}
