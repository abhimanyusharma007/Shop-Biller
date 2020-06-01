package com.example.shopbiller

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.shopbiller.addproduct
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_addproduct.*
import kotlinx.android.synthetic.main.activity_homepage.*

class Homepage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        addproduct.setOnClickListener {

            var intent = Intent(this, com.example.shopbiller.addproduct::class.java)
            startActivity(intent)
        }

        makebill.setOnClickListener {


            val scanner = IntentIntegrator(this)

            scanner.initiateScan()



        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    var barcode = result.contents.toString()
                    var db = productdatabasehandler(this)
                    val sqllitedatabase = db.readableDatabase
                    var datas = db.readdata(barcode, sqllitedatabase)
                    if (datas!!.moveToFirst()) {

                        var productname = datas.getString(0).toString()// getting product name
                        var productprice = datas.getString(1).toString() // getting product price
                        var intent = Intent(this,productquantity::class.java)
                        intent.putExtra("productname",productname)
                        intent.putExtra("productprice",productprice)
                        startActivity(intent)


                    } else {
                        super.onActivityResult(requestCode, resultCode, data)
                    }
                }
            }
        }
    }
}
