package com.example.taskmanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.db.TaskDatabase
import com.example.taskmanager.model.Task
import com.example.taskmanager.view.HomeFragmentDirections
import kotlinx.android.synthetic.main.card_layout.view.*
import kotlinx.android.synthetic.main.fragment_home.*

class TaskGunlukRecyclerAdapter(var gunlukList:ArrayList<Task>):RecyclerView.Adapter<TaskGunlukRecyclerAdapter.TaskGunlukHolder>() {
    class TaskGunlukHolder(view:View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskGunlukHolder {
        val inflater =LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_layout,parent,false)
        return TaskGunlukHolder(view)
    }

    override fun onBindViewHolder(holder: TaskGunlukHolder, position: Int) {

        holder.itemView.testIdTextView.text = gunlukList.get(position).title
        holder.itemView.setOnClickListener {
            var action = HomeFragmentDirections.actionHomeFragmentToAddTaskFragment(gunlukList.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return gunlukList.size
    }
    fun taskGunlukGuncelle(yeniTaskList :List<Task>){
        gunlukList.clear()

        for (task in yeniTaskList){
            when(task.selectItem){
                "Günlük"->{
                     gunlukList.add(task)
                }
            }
        }
        notifyDataSetChanged()
    }
}