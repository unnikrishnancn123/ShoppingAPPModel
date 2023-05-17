package com.example.myapp.home

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.Camera
import android.os.Environment
import android.util.Log
import android.view.SurfaceHolder
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CameraHelper(private val context: Context) {

    private var camera: Camera? = null

    fun captureImage(callback: (Bitmap?) -> Unit) {
        camera?.takePicture(null, null, Camera.PictureCallback { data, _ ->
            val imageFile = createImageFile()
            if (imageFile != null) {
                saveImageToFile(data, imageFile)
                val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
                callback(bitmap)
            } else {
                callback(null)
            }
        })
    }

    private fun createImageFile(): File? {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return try {
            File.createTempFile("IMG_$timeStamp", ".jpg", storageDir)
        } catch (e: IOException) {
            Log.e("CameraHelper", "Error creating image file: ${e.message}")
            null
        }
    }

    private fun saveImageToFile(data: ByteArray, file: File) {
        try {
            val fos = FileOutputStream(file)
            fos.write(data)
            fos.close()
        } catch (e: IOException) {
            Log.e("CameraHelper", "Error saving image to file: ${e.message}")
        }
    }

    fun releaseCamera() {
        camera?.release()
        camera = null
    }

    fun openCamera() {
        releaseCamera()
        camera = Camera.open()
        camera?.setDisplayOrientation(90)
    }

    fun startPreview(surfaceHolder: SurfaceHolder) {
        camera?.setPreviewDisplay(surfaceHolder)
        camera?.startPreview()
    }

    fun stopPreview() {
        camera?.stopPreview()
    }
}
