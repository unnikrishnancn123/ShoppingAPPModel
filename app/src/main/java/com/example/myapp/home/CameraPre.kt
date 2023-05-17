package com.example.myapp.home

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CaptureRequest
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.util.*

class CameraPre(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    private var cameraDevice: CameraDevice? = null
    private val cameraManager: CameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

    init {
        holder.addCallback(this)
    }
    override fun surfaceCreated(holder: SurfaceHolder) {
        try {
            val cameraId = getCameraId()
            cameraManager.openCamera(cameraId, object : CameraDevice.StateCallback() {
                override fun onOpened(camera: CameraDevice) {
                    cameraDevice = camera
                    startPreview(camera)
                }

                override fun onDisconnected(camera: CameraDevice) {
                    camera.close()
                    cameraDevice = null
                }

                override fun onError(camera: CameraDevice, error: Int) {
                    camera.close()
                    cameraDevice = null
                    Log.e("CameraPreview", "Camera error: $error")
                }
            }, null)
        } catch (e: CameraAccessException) {
            Log.e("CameraPreview", "Error setting up camera: ${e.message}")
        } catch (e: SecurityException) {
            Log.e("CameraPreview", "Permission denied: ${e.message}")
        }
    }


    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // No implementation needed for surfaceChanged
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        cameraDevice?.close()
        cameraDevice = null
    }

    private fun startPreview(camera: CameraDevice) {
        try {
            val surface = holder.surface

            val captureRequestBuilder = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
            captureRequestBuilder.addTarget(surface)

            val captureRequest = captureRequestBuilder.build()
            val captureCallback = object : CameraCaptureSession.StateCallback() {
                override fun onConfigured(session: CameraCaptureSession) {
                    try {
                        session.setRepeatingRequest(captureRequest, null, null)
                    } catch (e: CameraAccessException) {
                        Log.e("CameraPreview", "Error starting camera preview: ${e.message}")
                    }
                }

                override fun onConfigureFailed(session: CameraCaptureSession) {
                    Log.e("CameraPreview", "Camera preview configuration failed")
                }
            }

            camera.createCaptureSession(Collections.singletonList(surface), captureCallback, null)
        } catch (e: CameraAccessException) {
            Log.e("CameraPreview", "Error starting camera preview: ${e.message}")
        }
    }

    private fun getCameraId(): String {
        val cameraIds = cameraManager.cameraIdList
        for (id in cameraIds) {
            val characteristics = cameraManager.getCameraCharacteristics(id)
            val facing = characteristics.get(CameraCharacteristics.LENS_FACING)
            if (facing == CameraCharacteristics.LENS_FACING_BACK) {
                return id
            }
        }
        return cameraIds[0] // Return the first available camera by default
    }


}

