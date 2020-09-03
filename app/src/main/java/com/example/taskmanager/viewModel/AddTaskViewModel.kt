package com.example.taskmanager.viewModel

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.taskmanager.db.TaskDatabase
import com.example.taskmanager.model.Task
import com.example.taskmanager.view.AddTaskFragmentDirections
import kotlinx.coroutines.launch

class AddTaskViewModel(application: Application):BaseViewModel(application) {

    val tasks = MutableLiveData<Task>()
    val textUpdate = MutableLiveData<Boolean>()
    val addYukleniyor = MutableLiveData<Boolean>()
    val deleteButton = MutableLiveData<Boolean>()

    fun verileriKaydet(task:Task){
            launch {
                    val dao =TaskDatabase(getApplication()).getTaskDao().addTask(task)
                    tasks.value = task
            }
    }

    fun veriyiGetir(id: Int){
        addYukleniyor.value=true
        launch {
            val dao = TaskDatabase(getApplication()).getTaskDao()
            val task = dao.getTask(id)
            tasks.value = task
            textUpdate.value = true
            deleteButton.value=true
            addYukleniyor.value=false

        }
    }

    fun veriyiGuncelle(newTitle:String,newDescription:String,newitem:String,id:Int){
        launch {
                val dao = TaskDatabase(getApplication()).getTaskDao()
                val guncel = dao.updateTask(newTitle,newDescription,newitem,id)
        }
    }

    fun veriyiSil(id:Int,view: View){
        launch {
            val dao = TaskDatabase(getApplication()).getTaskDao()
            dao.deleteTask(id)

            val action =AddTaskFragmentDirections.actionAddTaskFragmentToHomeFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }
}