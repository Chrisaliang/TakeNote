package com.chris.eban.presenter

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.chris.eban.R
import com.chris.eban.common.CameraSource
import com.chris.eban.presenter.face.FaceContourDetectorProcessor
import com.google.firebase.ml.common.FirebaseMLException
import kotlinx.android.synthetic.main.activity_face_detect.*
import timber.log.Timber
import java.io.IOException

class FaceDetectActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    private var cameraSource: CameraSource? = null
//    private var selectedModel = FACE_CONTOUR

    private val requiredPermissions: Array<String?>
        get() {
            return try {
                val info = this.packageManager
                        .getPackageInfo(this.packageName, PackageManager.GET_PERMISSIONS)
                val ps = info.requestedPermissions
                if (ps != null && ps.isNotEmpty()) {
                    ps
                } else {
                    arrayOfNulls(0)
                }
            } catch (e: Exception) {
                arrayOfNulls(0)
            }
        }


    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        Timber.tag(TAG).d("Set facing")

        cameraSource?.let {
            if (isChecked) {
                it.setFacing(CameraSource.CAMERA_FACING_FRONT)
            } else {
                it.setFacing(CameraSource.CAMERA_FACING_BACK)
            }
        }
        firePreview?.stop()
        startCameraSource()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_face_detect)

        if (firePreview == null) {
            Timber.tag(TAG).d("Preview is null")
        }

        if (fireFaceOverlay == null) {
            Timber.tag(TAG).d("graphicOverlay is null")
        }

        val facingSwitch = facingSwitch
        facingSwitch.setOnCheckedChangeListener(this)
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        Timber.tag(TAG).d("camera id list: %s", cameraManager.cameraIdList)
        // Hide the toggle button if there is only 1 camera
        if (cameraManager.cameraIdList.size == 1) {
            facingSwitch.visibility = View.GONE
        }

        if (allPermissionsGranted()) {
            createCameraSource()
        } else {
            getRuntimePermissions()
        }


    }


    private fun getRuntimePermissions() {
        val allNeededPermissions = arrayListOf<String>()
        for (permission in requiredPermissions) {
            if (!isPermissionGranted(this, permission!!)) {
                allNeededPermissions.add(permission)
            }
        }

        if (allNeededPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                    this, allNeededPermissions.toTypedArray(), PERMISSION_REQUESTS)
        }
    }


    private fun createCameraSource() {
        // If there's no existing cameraSource, create one.
        if (cameraSource == null) {
            cameraSource = CameraSource(this, fireFaceOverlay)
        }

        Timber.tag(TAG).i("Using Face Contour Detector Processor")
        cameraSource?.setMachineLearningFrameProcessor(FaceContourDetectorProcessor())
    }


    private fun createCameraSource(model: String) {
        // If there's no existing cameraSource, create one.
        if (cameraSource == null) {
            cameraSource = CameraSource(this, fireFaceOverlay)
        }

        try {
            when (model) {
                CLASSIFICATION_QUANT -> {
                    Timber.tag(TAG).i("Using Custom Image Classifier (quant) Processor")
//                    cameraSource?.setMachineLearningFrameProcessor(CustomImageClassifierProcessor(this, true))
                }
                CLASSIFICATION_FLOAT -> {
                    Timber.tag(TAG).i("Using Custom Image Classifier (float) Processor")
//                    cameraSource?.setMachineLearningFrameProcessor(CustomImageClassifierProcessor(this, false))
                }
                TEXT_DETECTION -> {
                    Timber.tag(TAG).i("Using Text Detector Processor")
//                    cameraSource?.setMachineLearningFrameProcessor(TextRecognitionProcessor())
                }
                FACE_DETECTION -> {
                    Timber.tag(TAG).i("Using Face Detector Processor")
//                    cameraSource?.setMachineLearningFrameProcessor(FaceDetectionProcessor(resources))
                }
                BARCODE_DETECTION -> {
                    Timber.tag(TAG).i("Using Barcode Detector Processor")
//                    cameraSource?.setMachineLearningFrameProcessor(BarcodeScanningProcessor())
                }
                IMAGE_LABEL_DETECTION -> {
                    Timber.tag(TAG).i("Using Image Label Detector Processor")
//                    cameraSource?.setMachineLearningFrameProcessor(ImageLabelingProcessor())
                }
                FACE_CONTOUR -> {
                    Timber.tag(TAG).i("Using Face Contour Detector Processor")
                    cameraSource?.setMachineLearningFrameProcessor(FaceContourDetectorProcessor())
                }
                else -> Timber.tag(TAG).e("Unknown model: $model")
            }
        } catch (e: FirebaseMLException) {
            Timber.tag(TAG).e("can not create camera source: $model")
        }
    }

    /**
     * Starts or restarts the camera source, if it exists. If the camera source doesn't exist yet
     * (e.g., because onResume was called before the camera source was created), this will be called
     * again when the camera source is created.
     */
    private fun startCameraSource() {
        cameraSource?.let {
            try {
                if (firePreview == null) {
                    Timber.tag(TAG).d("resume: Preview is null")
                }
                if (fireFaceOverlay == null) {
                    Timber.tag(TAG).d("resume: graphOverlay is null")
                }
                firePreview?.start(cameraSource, fireFaceOverlay)
            } catch (e: IOException) {
                Timber.tag(TAG).e(e, "Unable to start camera source.")
                cameraSource?.release()
                cameraSource = null
            }
        }
    }


    private fun allPermissionsGranted(): Boolean {
        for (permission in requiredPermissions) {
            if (!isPermissionGranted(this, permission!!)) {
                return false
            }
        }
        return true
    }


    companion object {

        private const val FACE_DETECTION = "Face Detection"
        private const val TEXT_DETECTION = "Text Detection"
        private const val BARCODE_DETECTION = "Barcode Detection"
        private const val IMAGE_LABEL_DETECTION = "Label Detection"
        private const val CLASSIFICATION_QUANT = "Classification (quantized)"
        private const val CLASSIFICATION_FLOAT = "Classification (float)"
        private const val FACE_CONTOUR = "Face Contour"
        private const val PERMISSION_REQUESTS = 1
        const val TAG = "FaceDetectActivity"


        private fun isPermissionGranted(context: Context, permission: String): Boolean {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
                Timber.tag(TAG).i("Permission granted: $permission")
                return true
            }
            Timber.tag(TAG).i("Permission NOT granted: $permission")
            return false
        }
    }
}
