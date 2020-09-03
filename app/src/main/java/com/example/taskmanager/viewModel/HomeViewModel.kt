package com.example.taskmanager.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.taskmanager.db.TaskDatabase
import com.example.taskmanager.model.Task
import kotlinx.coroutines.launch

class HomeViewModel(application: Application):BaseViewModel(application){

    val tasks = MutableLiveData<List<Task>>()
    val gorevTablosu = MutableLiveData<Boolean>()
    val bilgiMesaji = MutableLiveData<Boolean>()
    val yukleniyor = MutableLiveData<Boolean>()


    fun verileriAl(){
    launch {
        yukleniyor.value=true
        bilgiMesaji.value=false
        var allTask = TaskDatabase(getApplication()).getTaskDao().getAllTask()


        if(allTask.isNotEmpty()){
                gorevTablosu.value = true
                bilgiMesaji.value = false
                 yukleniyor.value=false
                tasks.value = allTask
        }else{
            gorevTablosu.value = false
            yukleniyor.value = false
            bilgiMesaji.value= true

        }
    }

    }
}