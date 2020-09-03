package com.example.taskmanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.model.Task
import com.example.taskmanager.view.HomeFragmentDirections
import kotlinx.android.synthetic.main.card_layout.view.*

class TaskHaftalikRecyclerAdapter(var taskList:ArrayList<Task>):RecyclerView.Adapter<TaskHaftalikRecyclerAdapter.TaskManagerHolder>() {
    class TaskManagerHolder(view:View):RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskManagerHolder {
        val inflater =LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_layout,parent,false)
        return TaskManagerHolder(view)
    }

    override fun onBindViewHolder(holder: TaskManagerHolder, position: Int) {
        holder.itemView.testIdTextView.text= taskList.get(position).title
        holder.itemView.setOnClickListener {
            var action = HomeFragmentDirections.actionHomeFragmentToAddTaskFragment(taskList.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
       return taskList.size
    }
   fun taskHaftalikGuncelle(yeniTaskList :List<Task>){
       taskList.clear()

       for (task in yeniTaskList){
           when(task.selectItem){
               "Haftalık"->{
                   taskList.add(task)
               }
           }
       }
        notifyDataSetChanged()
   }

}