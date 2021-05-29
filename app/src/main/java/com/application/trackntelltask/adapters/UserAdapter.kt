package com.application.trackntelltask.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.application.trackntelltask.R
import com.application.trackntelltask.retro.User
import com.application.trackntelltask.retro.UserDetails
import com.application.trackntelltask.viewmodels.UserViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.images_view_pager.view.*
import kotlinx.android.synthetic.main.list_item.view.*

class UserAdapter(private val context: Context, private val userViewModel: UserViewModel) :
    ListAdapter<User, UserAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = getItem(position)
        holder.itemView.userId.text = item.userid
        holder.itemView.userName.text = item.name
        holder.itemView.setOnClickListener {
            userViewModel.getUserById(item.userid)
            userViewModel.user.observe(context as LifecycleOwner, Observer {
                if (it != null) {
                    Log.d("user images", it.pic_1)
                    var images = arrayListOf(it.pic_1, it.pic_2, it.pic_3, it.pic_4)
                    var view = LayoutInflater.from(context)
                        .inflate(R.layout.images_view_pager, null)
                    var adapter = ViewPagerAdapter(context, images)
                    var viewPager = view.findViewById<ViewPager>(R.id.imageViewPager)
                    view.pagerUserName.text = "Username : " + item.name
                    view.pagerUserId.text = "User ID : " + item.userid
                    viewPager.adapter = adapter
                    MaterialAlertDialogBuilder(context)
                        .setPositiveButton("Close") { _, _ ->
                        }
                        .setView(view)
                        .show()
                }
            })
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.userid == newItem.userid
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}