package com.example.shopbiller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class ProductlistAdpater(private val list:ArrayList<productsbill>,
                         private val context: Context):RecyclerView.Adapter<ProductlistAdpater.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup,position: Int):ViewHolder {

        val view = LayoutInflater.from(context)
            .inflate(R.layout.list_row,parent,false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindViews(list[position])
    }


    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var Productname =itemView.findViewById(R.id.pname) as TextView
        var Productqty = itemView.findViewById(R.id.pQty) as TextView
        var Productrate = itemView.findViewById(R.id.prate) as TextView
        var Productamount = itemView.findViewById(R.id.pamount) as TextView

        fun bindViews(productsbill: productsbill){

            Productname.text = productsbill.productname
            Productqty.text = productsbill.productsquantity.toString()
            Productrate.text = productsbill.productprice.toString()
            Productamount.text = productsbill.productamount.toString()

        }

    }




}