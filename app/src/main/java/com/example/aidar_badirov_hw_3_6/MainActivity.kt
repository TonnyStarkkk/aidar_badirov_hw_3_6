package com.example.aidar_badirov_hw_3_6

import com.example.aidar_badirov_hw_3_6.databinding.ActivityMainBinding
import com.example.aidar_badirov_hw_3_6.model.User
import com.example.aidar_badirov_hw_3_6.model.UsersListener
import com.example.aidar_badirov_hw_3_6.model.UsersService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UsersAdapter

    private val usersService: UsersService
        get() = (applicationContext as App).usersService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UsersAdapter(object : UserActionListener{
            override fun onUserMove(user: User, moveBy: Int){
                usersService.moveUser(user, moveBy)
            }
            override fun onUserDelete(user: User){
                usersService.deleteUser(user)
            }
            override fun onUserDetails(user: User){
                Toast.makeText(this@MainActivity, "User: ${user.name}", Toast.LENGTH_SHORT).show()
            }
        })

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        usersService.addListener(usersListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        usersService.removeListener(usersListener)
    }

    private val usersListener: UsersListener = {
        adapter.users = it
    }
}