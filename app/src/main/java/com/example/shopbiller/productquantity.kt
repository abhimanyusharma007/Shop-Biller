package com.example.shopbiller

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_productquantity.*


class productquantity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productquantity)
          var data = intent.extras
           var productname = data!!.get("productname").toString()
           var productprice = data.get("productprice").toString().toInt()

            pname.setText(productname)
            pprice.setText("\u20B9"+ productprice.toString())

             var productq= pqantity.text



           billbutton.setOnClickListener {

               if(TextUtils.isEmpty(productq)){

                   Toast.makeText(this,"Enter the Quantity",Toast.LENGTH_SHORT).show()
               }
               else{
                   var intent = Intent(this,finalbill::class.java)
                   startActivity(intent)
                   var price = calculateprice(productq.toString().toInt(),productprice) // total amount
                   var bill = productsbill(productname,productprice,price,productq.toString().toInt())
                   var db = productdatabasehandler(this)
                   db.insertData2(bill)

               }

           }

             addmore.setOnClickListener {

                 if(TextUtils.isEmpty(productq)){

                     Toast.makeText(this,"Enter the Quantity",Toast.LENGTH_SHORT).show()
                 }
                 else{
                     val scanner = IntentIntegrator(this)

                     scanner.initiateScan()
                     var price = calculateprice(productq.toString().toInt(),productprice)

                     var bill = productsbill(productname,productprice,price,productq.toString().toInt())
                     var db = productdatabasehandler(this)
                     db.insertData2(bill)
                 }

             }


        }

    private fun calculateprice(productq: Int, productprice: Int): Int { // function for calculate price

         return productprice * productq
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

