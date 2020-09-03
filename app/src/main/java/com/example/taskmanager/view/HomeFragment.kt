package com.example.taskmanager.view

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.R
import com.example.taskmanager.adapter.TaskAylikRecyclerAdapter
import com.example.taskmanager.adapter.TaskGunlukRecyclerAdapter
import com.example.taskmanager.adapter.TaskHaftalikRecyclerAdapter
import com.example.taskmanager.model.Task
import com.example.taskmanager.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private  lateinit var viewModel: HomeViewModel
    private  val recyclerTaskHaftalikAdapter = TaskHaftalikRecyclerAdapter(arrayListOf())
    private  val recyclerTaskGunlukAdapter =TaskGunlukRecyclerAdapter(arrayListOf())
    private  val recyclerTaskAylikAdapter = TaskAylikRecyclerAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.verileriAl()

        observeLiveData()

        addTaskButton.setOnClickListener {
           val action = HomeFragmentDirections.actionHomeFragmentToAddTaskFragment()
            Navigation.findNavController(it).navigate(action)

     }

    }
    fun observeLiveData(){
        viewModel.tasks.observe(viewLifecycleOwner, Observer { tasks->

            tasks?.let {
               for (task in tasks){
                    when(task.selectItem){
                       "Günlük"->{
                           gunlukRecyclerView.layoutManager = LinearLayoutManager(context?.applicationContext)
                           gunlukRecyclerView.adapter = recyclerTaskGunlukAdapter
                           recyclerTaskGunlukAdapter.taskGunlukGuncelle(tasks)
                       }
                        "Haftalık" ->{
                            haftalikRecyclerView.layoutManager = LinearLayoutManager(context?.applicationContext)
                            haftalikRecyclerView.adapter = recyclerTaskHaftalikAdapter
                            recyclerTaskHaftalikAdapter.taskHaftalikGuncelle(tasks)
                        }
                        "Aylık" -> {
                            aylikRecyclerView.layoutManager = LinearLayoutManager(context?.applicationContext)
                            aylikRecyclerView.adapter = recyclerTaskAylikAdapter
                            recyclerTaskAylikAdapter.taskAylikGuncelle(tasks)
                        }
                    }
               }
            }
        })
        viewModel.gorevTablosu.observe(viewLifecycleOwner, Observer { tablo->

            tablo?.let{
                if(it){
                    gorevTablo.visibility =View.VISIBLE
                    bilgiTextView.visibility = View.GONE
                }else{
                    gorevTablo.visibility =View.GONE
                }
            }
        })
        viewModel.bilgiMesaji.observe(viewLifecycleOwner, Observer { mesaj->
            mesaj?.let {
                if(it){
                    bilgiTextView.visibility=View.VISIBLE
                }else{
                    bilgiTextView.visibility=View.GONE
                }
            }

        })
        viewModel.yukleniyor.observe(viewLifecycleOwner, Observer { yukleniyor->
            yukleniyor?.let {
                if(it){
                    taskYukleniyor.visibility =View.VISIBLE
                    gorevTablo.visibility = View.GONE
                    bilgiTextView.visibility = View.GONE
                }else{
                    taskYukleniyor.visibility=View.GONE
                }
            }

        })
    }
}