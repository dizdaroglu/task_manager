package com.example.taskmanager.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.model.Task
import com.example.taskmanager.util.alertMessage
import com.example.taskmanager.viewModel.AddTaskViewModel
import kotlinx.android.synthetic.main.fragment_add_task.*


class AddTaskFragment : Fragment() {
    private lateinit var viewModel:AddTaskViewModel
    private var taskId = 0

    var radio:RadioButton?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            taskId = AddTaskFragmentArgs.fromBundle(it).taskId
        }

        viewModel=ViewModelProviders.of(this).get(AddTaskViewModel::class.java)

        if(taskId != 0){
            viewModel.veriyiGetir(taskId)
            observeLiveData()
        }
        selectButtonView.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                radio = selectButtonView.findViewById(checkedId)
            })

        saveButton.setOnClickListener {
         if(taskId == 0) {
             var titleText = konuText.text.toString()
             var descriptionText =aciklamaText.text.toString()
             var radioText=radio?.text.toString()

             var taskNesne = Task(titleText,descriptionText,radioText)

             viewModel.verileriKaydet(taskNesne)

             val action = AddTaskFragmentDirections.actionAddTaskFragmentToHomeFragment()
             Navigation.findNavController(it).navigate(action)
         }else{
                val title = konuText.text.toString()
                val description = aciklamaText.text.toString()
                var radioText=radio?.text.toString()

             viewModel.veriyiGuncelle(title,description,radioText,taskId)

             val action = AddTaskFragmentDirections.actionAddTaskFragmentToHomeFragment()
             Navigation.findNavController(it).navigate(action)
         }
        }

        deleteButton.setOnClickListener {
            val positiveButton = { _:DialogInterface, _: Int ->
                viewModel.veriyiSil(taskId,it)
                Toast.makeText(view.context,"Task Silindi", Toast.LENGTH_SHORT).show()
            }
            val negativeButton = { _: DialogInterface, _: Int ->
                Toast.makeText(view.context,"İptal edildi", Toast.LENGTH_SHORT).show()
            }

            val builder = AlertDialog.Builder(view.context)
            builder.setTitle("Uyarı Mesajı!")
            builder.setMessage("Silmek istediğinize emin misiniz?")
            builder.setPositiveButton("EVET", DialogInterface.OnClickListener(function = positiveButton))
            builder.setNegativeButton("HAYIR", DialogInterface.OnClickListener(function = negativeButton))
            builder.show()
        }

    }

    fun observeLiveData(){
        viewModel.tasks.observe(viewLifecycleOwner, Observer { tasks->
            tasks?.let {
                    when(it.selectItem){
                        "Günlük" ->{
                            selectButtonView.check(gunlukButton.id)
                        }
                        "Haftalık" ->{
                            selectButtonView.check(haftalikButton.id)
                        }
                        "Aylık" ->{
                            selectButtonView.check(aylikButton.id)
                        }
                    }
                    konuText.setText(it.title)
                    aciklamaText.setText(it.description)
            }
        })
        viewModel.textUpdate.observe(viewLifecycleOwner, Observer { textUpdate->
            textUpdate?.let {
              if(it){
                  saveButton.setText("UPDATE")
              }else{
                  saveButton.setText("SAVE")
              }
            }

        })
        viewModel.addYukleniyor.observe(viewLifecycleOwner, Observer {yukleniyor->
            yukleniyor?.let {
                if(it){
                    selectButtonView.visibility = View.GONE
                    editTextLayout.visibility = View.GONE
                    saveButton.visibility = View.GONE
                    addYukleniyor.visibility=View.VISIBLE
                }else{
                    selectButtonView.visibility = View.VISIBLE
                    editTextLayout.visibility = View.VISIBLE
                    saveButton.visibility = View.VISIBLE
                    addYukleniyor.visibility=View.GONE
                }
            }

        })
        viewModel.deleteButton.observe(viewLifecycleOwner, Observer {button->
            button?.let{
                if(it){
                    deleteButton.visibility = View.VISIBLE
                }else{
                    deleteButton.visibility=View.GONE
                }
            }
        })
    }
}