package com.example.taskmanager.util

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import android.widget.Toast


fun alertMessage(view: View) {
    val positiveButton = { dialog:DialogInterface,which : Int ->
        Toast.makeText(view.context,"Yes",Toast.LENGTH_SHORT).show()
    }
    val negativeButton = { dialog:DialogInterface,which : Int ->
        Toast.makeText(view.context,"No",Toast.LENGTH_SHORT).show()
    }

    val builder = AlertDialog.Builder(view.context)
    builder.setTitle("Alert Message!")
    builder.setMessage("We have a message")
    builder.setPositiveButton("OK",DialogInterface.OnClickListener(function = positiveButton))
    builder.setNegativeButton("No",DialogInterface.OnClickListener(function = negativeButton))
    builder.show()


}

