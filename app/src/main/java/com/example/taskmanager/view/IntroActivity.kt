package com.example.taskmanager.view

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.taskmanager.R
import com.example.taskmanager.adapter.IntroSliderAdapter
import com.example.taskmanager.model.IntroSlide
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {
    private  val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide("Görev Yöneticisi","Bu uygulamanın amacı yapılacakları oluşturup takip edebilmemizi sağlar.",R.drawable.bg0),
            IntroSlide("Peki Neler Var?","Yapacaklarınızı günlük haftalık ve aylık olarak ayarlaya bilmenizi sağlar.",R.drawable.bg01),
            IntroSlide("Hadi başlayalım","Görevlerinizi oluşturup hemen kullanmaya başlayın!",R.drawable.bg03)
        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        introSliderViewPager.adapter = introSliderAdapter

        setupIndicators()
        setCurrentIndicator(0)
        introSliderViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        nextButton.setOnClickListener {
            if(introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount){
                introSliderViewPager.currentItem +=1
            }else{
                Intent(applicationContext,MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
        skipIntroText.setOnClickListener {
            Intent(applicationContext,MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    private fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8,0,8,0)
        for(i in indicators.indices){
            indicators[i]= ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext,R.drawable.indicator_inactive)
                )
                this?.layoutParams=layoutParams
            }
            indicatorsContainer.addView(indicators[i])
        }
    }
    private fun setCurrentIndicator(index:Int){
        val childCount = indicatorsContainer.childCount

        for(i in 0 until childCount){
            val imageView = indicatorsContainer[i] as ImageView

            if(i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.indicator_active))
            }else{
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.indicator_inactive))
            }
        }
    }


}