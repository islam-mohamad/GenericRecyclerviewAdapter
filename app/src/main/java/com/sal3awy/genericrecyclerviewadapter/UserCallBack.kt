package com.sal3awy.genericrecyclerviewadapter

import com.sal3awy.ItemRecyclerViewCallback

interface UserCallBack : ItemRecyclerViewCallback {
    fun onUserClicked(user: User)
}