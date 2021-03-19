package com.hasankaya.bahismakinesi

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hasankaya.bahismakinesi.ImageViewScrolling.IEventEnd
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), IEventEnd
{

    override fun eventEnd(result: Int, count: Int)
    {

        if (count_down < 2)
            count_down++ // If still have image is roling
        else
        {
            down.visibility = View.GONE
            up.visibility = View.VISIBLE

            count_down = 0

            if(image.value == image2.value && image2.value == image3.value)
            {
                Toast.makeText(this,"Büyük kazandın!", Toast.LENGTH_SHORT).show()
                Common.SCORE += 300
                txt_score.text = Common.SCORE.toString()
            }
            else if(image.value == image2.value || image2.value == image3.value
                || image.value == image3.value)
            {
                Toast.makeText(this,"Küçük kazandın!", Toast.LENGTH_SHORT).show()
                Common.SCORE += 200
                txt_score.text = Common.SCORE.toString()
            }
            else
                Toast.makeText(this,"KAYBETTİN!", Toast.LENGTH_SHORT).show()


        }
    }
        internal var count_down = 0
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        image.setEventEnd(this@MainActivity)
        image2.setEventEnd(this@MainActivity)
        image3.setEventEnd(this@MainActivity)



        up.setOnClickListener {
            if (Common.SCORE >= 50)
            {
                up.visibility = View.GONE
                down.visibility = View.VISIBLE

                image.setValueRandom(Random.nextInt(6),// because we have 6 images
                Random.nextInt(15-5+1)+5) // we will get random rotate from 5-15
                image2.setValueRandom(Random.nextInt(6),// because we have 6 images
                    Random.nextInt(15-5+1)+5) // we will get random rotate from 5-15
                image3.setValueRandom(Random.nextInt(6),// because we have 6 images
                    Random.nextInt(15-5+1)+5) // we will get random rotate from 5-15

                Common.SCORE = -50
                txt_score.text = Common.SCORE.toString()
            }
            else
            {
                Toast.makeText(this,"Paran kalmadı!", Toast.LENGTH_SHORT).show()


            }
        }


    }


    fun clickMethod(view:View)
    {

        onRestart()
        startActivity(Intent.makeRestartActivityTask(componentName))
        Common.SCORE = 1000
        onStart()
        //var SCORE = 1000
    }





}