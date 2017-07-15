package io.github.ldhnam.roomrxjava2.mvp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import io.github.ldhnam.roomrxjava2.R
import io.github.ldhnam.roomrxjava2.common.bindView
import io.github.ldhnam.roomrxjava2.data.model.User

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var mUsers: ArrayList<User> = ArrayList()

    fun setUsers(users: ArrayList<User>) {
        mUsers.clear()
        mUsers.addAll(users)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mUsers.size

    override fun onBindViewHolder(holder: UserViewHolder?, position: Int) {
        val user = mUsers[position]
        holder?.bind(user)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mName by bindView<TextView>(R.id.name)
        val mDesc by bindView<TextView>(R.id.desc)
        val mAvatar by bindView<SimpleDraweeView>(R.id.avatar)

        fun bind(user: User) {
            mName.text = user.name
//            mDesc.text = "${user.gender},${user.age},${user.phone}"
            mAvatar.setImageURI(user.photo)
        }
    }
}