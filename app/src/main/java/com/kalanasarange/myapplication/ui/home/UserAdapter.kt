package com.kalanasarange.myapplication.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.kalanasarange.myapplication.data.model.User
import com.kalanasarange.myapplication.databinding.ItemUserBinding

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private val userList = arrayListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(userList[position])

    override fun getItemCount() = userList.size

    fun setUserList(userList: ArrayList<User>) {
        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }

    class UserViewHolder(
        private val binding: ItemUserBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(user: User){
            binding.apply {

                // Set User's full name by concatenating first name and last name
                val fullName = "${user.firstName} ${user.lastName}"
                txtUserName.text = fullName

                // Set user designation by concatenating company title and company name
                val position = "${user.company.title}, ${user.company.name}"
                txtUserPosition.text = position

                // Set user image using coin
                imgUser.apply {
                    load(user.image){
                        crossfade(true)
                        transformations(RoundedCornersTransformation(15f, 15f, 15f, 15f))
                        scale(Scale.FIT)
                        allowHardware(false)
                    }
                }
            }
        }
    }
}