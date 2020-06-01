package com.example.shopbiller

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME ="MyDB"
val TABLE_NAME = "Products"
val PRODUCT_NO= "productno"
val PRODUCT_ID = "productid"
val PRODUCT_NAME ="productname"
val PRODUCT_PRICE ="productprice"

val TABLE_NAME2 = "Bills"
val PRODUCT_AMOUNT = " productamount"
val PRODUCT_QUANTITY = "productsquantity"



class productdatabasehandler( var context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable ="CREATE TABLE "+ TABLE_NAME +" ("+ PRODUCT_NO +" INTEGER PRIMARY KEY AUTOINCREMENT,"+ PRODUCT_ID + " VARCHAR(250),"+
                PRODUCT_NAME+" VARCHAR(256),"+ PRODUCT_PRICE +" INTEGER "+");"

        db?.execSQL(createTable)

        val createTable2 = " CREATE TABLE " + TABLE_NAME2 +" (" + PRODUCT_NO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_NAME + " VARCHAR(256), " +
                PRODUCT_PRICE + " INTEGER, " + PRODUCT_AMOUNT + " INTEGER, " + PRODUCT_QUANTITY + " INTEGER "+
                ");"

        db?.execSQL(createTable2)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertData(products: products){

        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(PRODUCT_ID,products.productid)
        cv.put(PRODUCT_NAME,products.productname)
        cv.put(PRODUCT_PRICE,products.productprice)
        var result =db.insert(TABLE_NAME,null,cv)
        if(result==-1.toLong()){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()}


    }


    fun insertData2 (productsbill: productsbill){

        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(PRODUCT_NAME,productsbill.productname)
        cv.put(PRODUCT_PRICE,productsbill.productprice)
        cv.put(PRODUCT_AMOUNT,productsbill.productamount)
        cv.put(PRODUCT_QUANTITY,productsbill.productsquantity)
        var result = db.insert(TABLE_NAME2,null,cv)
        if(result==-1.toLong()){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()

        }
    }

//
//    fun readsdata(bills: bills){
//
//        var barcode = bills.barcode
//        readdata(barcode,sqLiteDatabase)
//
//    }

    fun readdata(barcode: String,sqLiteDatabase: SQLiteDatabase): android.database.Cursor? {




        val selection = "$PRODUCT_ID=?"
        val selectionArgs = arrayOf(barcode)
        return sqLiteDatabase.query(TABLE_NAME, arrayOf(PRODUCT_NAME, PRODUCT_PRICE),selection,selectionArgs,null,null,null)



    }


    fun readdatabill():ArrayList<productsbill>{ // this function for read all data from table Bills for make bills

        var list : ArrayList<productsbill> = ArrayList()

        val db= this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME2
        var result = db.rawQuery(query,null)
        if (result.moveToFirst()){
            do {
                var productsbill = productsbill()
                productsbill.productname = result.getString(1)
                productsbill.productprice = result.getInt(2)
                productsbill.productamount = result.getInt(3)
                productsbill.productsquantity = result.getInt(4)
                list.add(productsbill)

            }while (result.moveToNext())

        }
        return list

    }

   fun countproduct():Int{  // this function for count of product in Bills table for make bill

       var productcount:Int= 0

       var query = "SELECT SUM(productsquantity) FROM Bills "
       val db= this.readableDatabase
       val result = db.rawQuery(query,null)

       if (result.columnCount>0){
           result.moveToFirst()
          productcount = result .getInt(0)
       }

       return productcount
   }

    fun amountproduct():Int{

        var productamount:Int =0

        var query = "SELECT SUM(productamount) FROM Bills "
        val db = this.readableDatabase
        val result = db.rawQuery(query,null)

        if (result.columnCount>0){
            result.moveToNext()
            productamount= result.getInt(0)
        }

        return productamount
    }


    fun deleteproduct(){

        val db = this.writableDatabase
        db.delete(TABLE_NAME2,null,null)
    }


}