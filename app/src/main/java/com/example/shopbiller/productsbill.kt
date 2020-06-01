package com.example.shopbiller

class productsbill{

    var productno :Int = 0
    var productname : String =""
    var productprice : Int = 0
    var productamount: Int = 0
    var productsquantity :Int = 0

    constructor(productname:String,productprice:Int,productamount:Int,productsquantity:Int){


        this.productname = productname
        this.productprice =productprice
        this.productamount = productamount
        this.productsquantity = productsquantity
    }

    constructor(){

    }
}