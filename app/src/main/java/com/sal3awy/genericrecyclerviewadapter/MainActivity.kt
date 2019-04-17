package com.sal3awy.genericrecyclerviewadapter

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.sal3awy.genericrecyclerviewadapter.binding.AppRecyclerViewAdapter
import com.sal3awy.genericrecyclerviewadapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), UserCallBack {

    private var binding: ActivityMainBinding? = null
    private val users: ArrayList<User>? = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


       // adapter without callback interface for clicks handling

        val adapter1: AppRecyclerViewAdapter<User> = AppRecyclerViewAdapter(object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(p0: User, p1: User): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun areContentsTheSame(p0: User, p1: User): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

        // adapter with callback interface for clicks handling
        val adapter2: AppRecyclerViewAdapter<User> =
            AppRecyclerViewAdapter(this, object : DiffUtil.ItemCallback<User>() {
                override fun areItemsTheSame(p0: User, p1: User): Boolean {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun areContentsTheSame(p0: User, p1: User): Boolean {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })

        populateData()

        adapter2.submitList(users)
        binding!!.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter2.setItemViewType(R.layout.item_user)
        binding!!.recyclerView.adapter = adapter2
        adapter2.notifyDataSetChanged()

    }

    private fun populateData() {
        users!!.add(User("Islam"))
        users.add(User("Mohamed"))
        users.add(User("Mahmoud"))
        users.add(User("Ibrahim"))
    }

    override fun onUserClicked(user: User) {
        Toast.makeText(this, user.name, Toast.LENGTH_SHORT).show()
    }
}
