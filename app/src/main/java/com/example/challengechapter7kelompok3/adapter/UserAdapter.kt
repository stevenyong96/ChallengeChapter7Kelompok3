package com.example.challengechapter7kelompok3.adapter

import android.app.AlertDialog
import android.service.autofill.UserData
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.challengechapter7kelompok3.RegisterActivity
import com.example.challengechapter7kelompok3.UserActivity
import com.example.challengechapter7kelompok3.database.UserDatabase
import com.example.challengechapter7kelompok3.databinding.ActivityUserBinding
import com.example.challengechapter7kelompok3.databinding.ItemUserBinding
import com.example.challengechapter7kelompok3.entity.Users
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserAdapter(private val listUsers: List<Users>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Users, item: Users) {

            //step 4 implement recyclerview, bind data ke view
            binding.tvId.text = "Id User : ${user.id}"
            binding.tvUsername.text = "Username : ${user.username}"
            binding.tvName.text = "Nama User : ${user.nama}"
            binding.tvEmail.text = "Email User : ${user.email}"

            binding.ivDelete.setOnClickListener {

                val database = UserDatabase.getInstance(it.context as UserActivity)

                AlertDialog.Builder(binding.root.context as UserActivity)
                    .setPositiveButton("Ya") { p0, p1 ->

                        GlobalScope.launch {

                            val result = database?.userDao()?.deleteUser(item)


                            (binding.root.context as RegisterActivity).runOnUiThread {
                                if (result != 0) {
                                    Toast.makeText(
                                        it.context, "Data ${item.username} berhasil dihapus",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        it.context, "Data ${item.username} Gagal dihapus",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                            (binding.root.context as UserActivity).fetchData()
                        }
                    }.setNegativeButton("Tidak") { p0, p1 ->
                        p0.dismiss()
                    }.setMessage("Apakah Anda Yakin ingin menghapus data ${item.username}")
                    .setTitle("Konfirmasi Hapus").create()
                    .show()


            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Users = listUsers[position]
        holder.bind(item,item)
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }
}