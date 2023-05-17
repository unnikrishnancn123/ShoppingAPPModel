package com.example.myapp.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapp.R

class CameraPreviewActivity : AppCompatActivity() {

    private lateinit var cameraPreview: CameraPre
    private val cameraPermissionCode = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_pre)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val cameraPreviewContainer = findViewById<FrameLayout>(R.id.cameraPreviewContainer)
        cameraPreview = CameraPre(this)

        if (hasCameraPermission()) {
            startCamera()
        } else {
            requestCameraPermission()
        }

        cameraPreviewContainer.addView(cameraPreview)
    }

    private fun hasCameraPermission(): Boolean {
        val permission = Manifest.permission.CAMERA
        val result = ContextCompat.checkSelfPermission(this, permission)
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        val permission = Manifest.permission.CAMERA
        ActivityCompat.requestPermissions(this, arrayOf(permission), cameraPermissionCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == cameraPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else {
                // Handle the situation if the camera permission is denied
                // For example, you can display an error message or disable camera functionality
            }
        }
    }

    private fun startCamera() {
        // Code to start the camera goes here
    }
}
