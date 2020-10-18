package com

import com.example.tandemtest.App

class TestApplication: App() {
    override fun getApiUrl(): String {
        return "http://127.0.0.1:8080"
    }
}