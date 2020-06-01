package com.example.shopbiller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_finalbill.*

class finalbill : AppCompatActivity() {

     private var adpater: ProductlistAdpater?= null
     private var Productlist: ArrayList<productsbill>? = null
     private var Productlistitem:ArrayList<productsbill>?= null
     private var layoutManager: RecyclerView.LayoutManager?= null
     var dbHandler: productdatabasehandler?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finalbill)

        dbHandler = productdatabasehandler(this)
        Productlist = ArrayList()
        Productlistitem = ArrayList()
        layoutManager= LinearLayoutManager(this)
        adpater = ProductlistAdpater(Productlistitem!!,this)


        rvid.layoutManager= layoutManager // recyclerViewid is id of recyclerview
        rvid.adapter = adpater

         //load our products
         Productlist = dbHandler!!.readdatabill()





        for(c in Productlist!!.iterator()){

            val Productsbill = productsbill()
            Productsbill.productname = c.productname
            Productsbill.productprice  =  c.productprice
            Productsbill.productamount =  c.productamount
            Productsbill.productsquantity = c.productsquantity

            Productlistitem!!.add(Productsbill)
        }
        adpater!!.notifyDataSetChanged()


        var productcount = dbHandler!!.countproduct()
        var productamount = dbHandler!!.amountproduct()

        productno.setText(productcount.toString())

        totalamount.setText("₹"+productamount.toString())

        otherbill.setOnClickListener {

            dbHandler!!.deleteproduct()

            var intent= Intent(this,Homepage::class.java)
            startActivity(intent)
        }





    }
}
