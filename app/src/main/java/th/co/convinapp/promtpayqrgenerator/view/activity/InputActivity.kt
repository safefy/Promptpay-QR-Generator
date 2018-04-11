/*
 * Copyright (c) 2018. Pudit Prasert
 * safefy@gmail.com
 */

package th.co.convinapp.promtpayqrgenerator.view.activity

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_input.*
import th.co.convinapp.promtpayqrgenerator.R

class InputActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        btn_scan.setOnClickListener(this)
        btn_generate.setOnClickListener(this)
    }

    private fun performScan() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {

                    }

                    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
                        token?.continuePermissionRequest()
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {

                    }
                }).check()
    }

    private fun performQRGenerate() {
        val intent = Intent(this, GenerateQRCodeActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        if (btn_scan == v) {
            performScan()
            return
        }
        if (btn_generate == v) {
            performQRGenerate()
            return
        }
    }
}
