package com.example.aidar_badirov_hw_3_6

import android.app.Application
import com.example.aidar_badirov_hw_3_6.model.UsersService

class App : Application(){
    val usersService = UsersService()
}