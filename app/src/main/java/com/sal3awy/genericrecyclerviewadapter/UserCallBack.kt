package com.sal3awy.genericrecyclerviewadapter

import com.sal3awy.genericrecyclerviewadapter.binding.RecyclerViewCallback

interface UserCallBack : RecyclerViewCallback {
    fun onUserClicked(user: User)
}