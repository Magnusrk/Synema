package com.example.synema.model

class ApiResponse <T>(
    private var result: T?,
    private var error: Boolean = false,
    private var statusMessage: String = "Successful"
) {
    fun getResult() : T? {
        return result;
    }
    fun successful() : Boolean {
        return !error;
    }
    fun getStatus() : String {
        return statusMessage;
    }

}