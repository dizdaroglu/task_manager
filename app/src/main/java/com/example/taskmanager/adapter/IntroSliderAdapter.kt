package com.example.taskmanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.model.IntroSlide

class IntroSliderAdapter(val introSlides: List<IntroSlide>):RecyclerView.Adapter<IntroSliderAdapter.IntroSliderHolder>() {
    class IntroSliderHolder(view:View):RecyclerView.ViewHolder(view) {
        private val textTitle = view.findViewById<TextView>(R.id.introTextTitleText)
        private val textDescription = view.findViewById<TextView>(R.id.introTextDescription)
        private val imageIcon = view.findViewById<ImageView>(R.id.imageSliderIcon)

        fun bind(introSlide:IntroSlide){
            textTitle.text = introSlide.title
            textDescription.text = introSlide.description
            imageIcon.setImageResource(introSlide.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSliderHolder {
        LayoutInflater.from(parent.context).also {
            return IntroSliderHolder( it.inflate(R.layout.slider_item_layout,parent,false))
        }
    }

    override fun onBindViewHolder(holder: IntroSliderHolder, position: Int) {
        holder.bind(introSlides[position])
    }

    override fun getItemCount(): Int {
        return introSlides.size
    }

}