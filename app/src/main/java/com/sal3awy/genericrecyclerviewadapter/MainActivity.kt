package com.sal3awy.genericrecyclerviewadapter

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.sal3awy.GenericRecyclerViewAdapter
import com.sal3awy.genericrecyclerviewadapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), UserCallBack {

    private var binding: ActivityMainBinding? = null
    private val users: ArrayList<User> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        // adapter with callback interface for clicks handling
        val adapter =
            GenericRecyclerViewAdapter(list = users, layoutId =  R.layout.item_user)

        populateData()

        adapter.submitList(users)
        binding!!.recyclerView.layoutManager = LinearLayoutManager(this)
        binding!!.recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    private fun populateData() {
        users.add(User("Ahmed"))
        users.add(User("Mohamed"))
        users.add(User("Mahmoud"))
        users.add(User("Ibrahim"))
    }

    override fun onUserClicked(user: User) {
        Toast.makeText(this, user.name, Toast.LENGTH_SHORT).show()
    }
}
