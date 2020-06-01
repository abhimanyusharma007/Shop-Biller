package com.example.shopbiller

class products{
    var productno :Int = 0
    var productid : String = ""
    var productname : String =""
    var productprice : Int = 0

    constructor(productid:String,productname:String,productprice:Int){

        this.productid = productid
        this.productname = productname
        this.productprice =productprice
    }

    constructor(){

    }

}