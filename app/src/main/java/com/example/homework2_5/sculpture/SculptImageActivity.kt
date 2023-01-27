package com.example.homework2_5.sculpture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework2_5.databinding.ActivitySculptImageBinding
import com.example.homework2_5.taken.ImageModel
import com.example.homework2_5.taken.MainActivity.Companion.IMAGE

class SculptImageActivity : AppCompatActivity() {
    private var sculptAdapter = SculptAdapter()
    private lateinit var binding :  ActivitySculptImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySculptImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getImages()
                initClick()

            }

            private fun initClick() {
                binding.btnBack.setOnClickListener {
                    onBackPressed()
                }
            }

            private fun getImages() {
                val images = intent.getStringArrayListExtra(IMAGE) as ArrayList<ImageModel>
                sculptAdapter.setImageList(images)
                binding.selectedRecycler.adapter = sculptAdapter
            }
}