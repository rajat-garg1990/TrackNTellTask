package com.application.trackntelltask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.trackntelltask.adapters.UserAdapter
import com.application.trackntelltask.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var userVm:UserViewModel
    lateinit var userAdapter:UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userVm=ViewModelProvider(this).get(UserViewModel::class.java)
        userAdapter=UserAdapter(this,userVm)
        var userRecycler=findViewById<RecyclerView>(R.id.userRecycler)
        userRecycler.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=userAdapter
        }
        loadButton.setOnClickListener {
            loadButton.visibility=View.INVISIBLE
            userRecycler.visibility=View.VISIBLE
            termsConditions.visibility=View.VISIBLE
            userVm.getUsers()
            userVm.users.observe(this, Observer {
                userAdapter.submitList(it.users)
            })
        }
    }
}