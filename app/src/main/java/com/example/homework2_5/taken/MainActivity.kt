package com.example.homework2_5.taken

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.homework2_5.R
import com.example.homework2_5.databinding.ActivityMainBinding
import com.example.homework2_5.sculpture.SculptImageActivity
import com.example.homework2_5.ui.ImagesGallery

class MainActivity : AppCompatActivity(), ImageAdapter.OnItemClick {


    private lateinit var viewBinding: ActivityMainBinding

    private var imageAdapter = ImageAdapter()
    private var images = arrayListOf<ImageModel>()
    private var selectedImages: ArrayList<ImageModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                90
            )
        } else {
            loadImages()
        }


    }

    private fun loadImages() {
        images = ImagesGallery.listOfImages(this)
        imageAdapter.setImageList(images)
        imageAdapter.setListener(this)
        viewBinding.recycler.adapter = imageAdapter
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 90 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadImages()
        } else {
            finish()
        }
    }

    override fun onClicked(image: ImageModel, position: Int) {
        (!image.isSelected).also { image.isSelected = it }
        images[position] = image
        imageAdapter.setImageList(images)
        selectedImages.clear()
        selectedImages.addAll(images.filter { it.isSelected })
        viewBinding.tvCounter.text = "Выбрано ${selectedImages.size} фотографии"
        viewBinding.btnGet.setOnClickListener {
            sendSelectedImages()
        }
    }


    private fun sendSelectedImages() {
        Intent(this@MainActivity, SculptImageActivity::class.java).apply {
            putExtra(IMAGE, selectedImages)
            startActivity(this)
        }
    }

    companion object {
        const val IMAGE = "image"
    }


}
