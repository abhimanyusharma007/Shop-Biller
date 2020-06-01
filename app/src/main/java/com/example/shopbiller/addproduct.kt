package com.example.shopbiller

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_addproduct.*
import android.R.attr.data
import android.widget.Toast
import com.google.zxing.integration.android.IntentResult
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.text.TextUtils


class addproduct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addproduct)

        barcodescan.setOnClickListener {

            val scanner = IntentIntegrator(this)

            scanner.initiateScan()
        }

        addproductbtn.setOnClickListener{

            if (barcodescan.text.toString().length>0&& productname.text.toString().length>0&&
                    productprice.text.toString().length>0){
                var pro = products(barcodescan.text.toString(),productname.text.toString(),productprice.text.toString().toInt())
                var db = productdatabasehandler(this)
                db.insertData(pro)

                var intent = Intent(this,Homepage::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"fill all data",Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onActivityResult(requestCode:Int,resultCode: Int, data: Intent?) {
        if (resultCode==Activity.RESULT_OK){
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    barcodescan.setText(result.contents)
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
   }
}
