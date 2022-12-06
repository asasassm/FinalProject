package kr.ac.kyungnam.android.finalproject

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class schedule : AppCompatActivity() {


    lateinit var myHelper: AddSchedule.myDBHelper
    lateinit var sqlDB: SQLiteDatabase
    lateinit var btnadd: Button
    lateinit var btnre: Button

   lateinit var mondayA : AutoResizeTextView
   lateinit var tuesdayA : AutoResizeTextView
    lateinit var wednesdayA : AutoResizeTextView
    lateinit var thursdayA : AutoResizeTextView
    lateinit var fridayA : AutoResizeTextView

    lateinit  var mondayB : AutoResizeTextView
    lateinit  var tuesdayB: AutoResizeTextView
    lateinit  var wednesdayB : AutoResizeTextView
    lateinit var thursdayB : AutoResizeTextView
    lateinit  var fridayB  : AutoResizeTextView

    lateinit  var mondayC : AutoResizeTextView
    lateinit  var tuesdayC : AutoResizeTextView
    lateinit  var wednesdayC : AutoResizeTextView
    lateinit  var thursdayC : AutoResizeTextView
    lateinit  var fridayC : AutoResizeTextView

    lateinit  var mondayD : AutoResizeTextView
    lateinit   var tuesdayD : AutoResizeTextView
    lateinit  var wednesdayD : AutoResizeTextView
    lateinit var thursdayD : AutoResizeTextView
    lateinit var fridayD : AutoResizeTextView

    lateinit   var mondayE : AutoResizeTextView
    lateinit   var tuesdayE : AutoResizeTextView
    lateinit  var wednesdayE: AutoResizeTextView
    lateinit var thursdayE : AutoResizeTextView
    lateinit  var fridayE : AutoResizeTextView

    lateinit   var mondayF : AutoResizeTextView
    lateinit   var tuesdayF : AutoResizeTextView
    lateinit  var wednesdayF : AutoResizeTextView
    lateinit   var thursdayF : AutoResizeTextView
    lateinit  var fridayF : AutoResizeTextView

    lateinit  var mondayG : AutoResizeTextView
    lateinit   var tuesdayG : AutoResizeTextView
    lateinit  var wednesdayG : AutoResizeTextView
    lateinit  var thursdayG : AutoResizeTextView
    lateinit var fridayG : AutoResizeTextView

    lateinit  var mondayH : AutoResizeTextView
    lateinit var tuesdayH : AutoResizeTextView
    lateinit  var wednesdayH : AutoResizeTextView
    lateinit  var thursdayH : AutoResizeTextView
    lateinit   var fridayH : AutoResizeTextView
    lateinit var tvtemp : AutoResizeTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule)

        myHelper = AddSchedule.myDBHelper(this)
        sqlDB = myHelper.writableDatabase

        mondayA = findViewById(R.id.mondayA)
         tuesdayA = findViewById(R.id.tuesdayA)
         wednesdayA = findViewById(R.id.wednesdayA)
         thursdayA = findViewById(R.id.thursdayA)
         fridayA = findViewById(R.id.fridayA)

         mondayB = findViewById(R.id.mondayB)
         tuesdayB = findViewById(R.id.tuesdayB)
         wednesdayB = findViewById(R.id.wednesdayB)
         thursdayB = findViewById(R.id.thursdayB)
         fridayB = findViewById(R.id.fridayB)

         mondayC = findViewById(R.id.mondayC)
         tuesdayC = findViewById(R.id.tuesdayC)
         wednesdayC = findViewById(R.id.wednesdayC)
         thursdayC = findViewById(R.id.thursdayC)
         fridayC = findViewById(R.id.fridayC)

         mondayD = findViewById(R.id.mondayD)
         tuesdayD = findViewById(R.id.tuesdayD)
         wednesdayD = findViewById(R.id.wednesdayD)
         thursdayD = findViewById(R.id.thursdayD)
         fridayD = findViewById(R.id.fridayD)

         mondayE = findViewById(R.id.mondayE)
         tuesdayE = findViewById(R.id.tuesdayE)
         wednesdayE = findViewById(R.id.wednesdayE)
         thursdayE = findViewById(R.id.thursdayE)
         fridayE = findViewById(R.id.fridayE)

         mondayF = findViewById(R.id.mondayF)
         tuesdayF = findViewById(R.id.tuesdayF)
         wednesdayF = findViewById(R.id.wednesdayF)
         thursdayF = findViewById(R.id.thursdayF)
         fridayF = findViewById(R.id.fridayF)

         mondayG = findViewById(R.id.mondayG)
         tuesdayG = findViewById(R.id.tuesdayG)
         wednesdayG = findViewById(R.id.wednesdayG)
         thursdayG = findViewById(R.id.thursdayG)
         fridayG = findViewById(R.id.fridayG)

         mondayH = findViewById(R.id.mondayH)
         tuesdayH = findViewById(R.id.tuesdayH)
         wednesdayH = findViewById(R.id.wednesdayH)
         thursdayH = findViewById(R.id.thursdayH)
         fridayH = findViewById(R.id.fridayH)
        tvtemp = findViewById(R.id.tvtemp)


        btnadd = findViewById(R.id.btnadd)
        btnre = findViewById(R.id.btnre)



      //  tvtemp.setText(idk)

        sqlDB = myHelper.readableDatabase
        var cursor: Cursor
        var cid = mutableListOf<String>()
        var cname = mutableListOf<String>()
        var croom = mutableListOf<String>()
        var cweek = mutableListOf<String>()
        var ctime =mutableListOf<String>()

        var cnt =0
        var recnt =0

        cursor = sqlDB.rawQuery("SELECT * FROM scheduleDB;", null)
        while (cursor.moveToNext()) {
            cid += cursor.getString(0)
            cname += cursor.getString(1)
            croom += cursor.getString(2)
            cweek += cursor.getString(3)
            ctime += cursor.getString(4)
            recnt= cnt++
        }
        cursor.close()
        sqlDB.close()

       read()

       btnadd.setOnClickListener {
            val intent = Intent(applicationContext, AddSchedule::class.java)
            startActivity(intent)

        }

        btnre.visibility= View.VISIBLE

        btnre.setOnClickListener {

        }


    }
    fun read(){
        sqlDB = myHelper.readableDatabase
        var cursor: Cursor
        var cid = mutableListOf<String>()
        var cname = mutableListOf<String>()
        var croom = mutableListOf<String>()
        var cweek = mutableListOf<String>()
        var ctime =mutableListOf<String>()
        var index = mutableListOf<Int>()
        var cnt =0
        var recnt =0
        var incnt=0
        cursor = sqlDB.rawQuery("SELECT * FROM scheduleDB;", null)
        while (cursor.moveToNext()) {
            cid += cursor.getString(0)
            cname += cursor.getString(1)
            croom += cursor.getString(2)
            cweek += cursor.getString(3)
            ctime += cursor.getString(4)
            recnt= cnt++
        }
        cursor.close()
        sqlDB.close()
    if(cname.get(0).isEmpty()){
      Toast.makeText(applicationContext,"시간표를 등록하시오.",Toast.LENGTH_SHORT).show()
    }else{
        for(i in 0..recnt){
            var icnt =0
            if(cid.get(i)==tvtemp.text.toString()){
                index.add(i)
                incnt = icnt++
            }
        }
    /*    for(i in 0..incnt){
            when(setOf(cweek.get(index.get(i)),ctime.get(index.get(i)))){
                setOf("월","A") -> {
                    mondayA.setText(cname.get(index.get(i)))
                    mondayA.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("월","B") -> {
                    mondayB.setText(cname.get(index.get(i)))
                    mondayB.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("월","C") -> {
                    mondayC.setText(cname.get(index.get(i)))
                    mondayC.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("월","D") -> {
                    mondayD.setText(cname.get(index.get(i)))
                    mondayD.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("월","E") -> {
                    mondayE.setText(cname.get(index.get(i)))
                    mondayE.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("월","F") -> {
                    mondayF.setText(cname.get(index.get(i)))
                    mondayF.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("월","G") -> {
                    mondayG.setText(cname.get(index.get(i)))
                    mondayG.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("월","H") -> {
                    mondayH.setText(cname.get(index.get(i)))
                    mondayH.setBackgroundResource(R.drawable.cell_shape_update)
                }

                setOf("화","A") -> {
                    tuesdayA.setText(cname.get(index.get(i)))
                    tuesdayA.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("화","B") -> {
                    tuesdayB.setText(cname.get(index.get(i)))
                    tuesdayB.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("화","C") -> {
                    tuesdayC.setText(cname.get(index.get(i)))
                    tuesdayC.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("화","D") -> {
                    tuesdayD.setText(cname.get(index.get(i)))
                    tuesdayD.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("화","E") -> {
                    tuesdayE.setText(cname.get(index.get(i)))
                    tuesdayE.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("화","F") -> {
                    tuesdayF.setText(cname.get(index.get(i)))
                    tuesdayF.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("화","G") -> {
                    tuesdayG.setText(cname.get(index.get(i)))
                    tuesdayG.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("화","H") -> {
                    tuesdayH.setText(cname.get(index.get(i)))
                    tuesdayH.setBackgroundResource(R.drawable.cell_shape_update)
                }

                setOf("수","A") -> {
                    wednesdayA.setText(cname.get(index.get(i)))
                    wednesdayA.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("수","B") -> {
                    wednesdayB.setText(cname.get(index.get(i)))
                    wednesdayB.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("수","C") -> {
                    wednesdayC.setText(cname.get(index.get(i)))
                    wednesdayC.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("수","D") -> {
                    wednesdayD.setText(cname.get(index.get(i)))
                    wednesdayD.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("수","E") -> {
                    wednesdayE.setText(cname.get(index.get(i)))
                    wednesdayE.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("수","F") -> {
                    wednesdayF.setText(cname.get(index.get(i)))
                    wednesdayF.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("수","G") -> {
                    wednesdayG.setText(cname.get(index.get(i)))
                    wednesdayG.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("수","H") -> {
                    wednesdayH.setText(cname.get(index.get(i)))
                    wednesdayH.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("목","A") -> {
                    thursdayA.setText(cname.get(index.get(i)))
                    thursdayA.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("목","B") -> {
                    thursdayB.setText(cname.get(index.get(i)))
                    thursdayB.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("목","C") -> {
                    thursdayC.setText(cname.get(index.get(i)))
                    thursdayC.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("목","D") -> {
                    thursdayD.setText(cname.get(index.get(i)))
                    thursdayD.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("목","E") -> {
                    thursdayE.setText(cname.get(index.get(i)))
                    thursdayE.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("목","F") -> {
                    thursdayF.setText(cname.get(index.get(i)))
                    thursdayF.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("목","G") -> {
                    thursdayG.setText(cname.get(index.get(i)))
                    thursdayG.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("목","H") -> {
                    thursdayH.setText(cname.get(index.get(i)))
                    thursdayH.setBackgroundResource(R.drawable.cell_shape_update)
                }

                setOf("금","A") -> {
                    fridayA.setText(cname.get(index.get(i)))
                    fridayA.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("금","B") -> {
                    fridayB.setText(cname.get(index.get(i)))
                    fridayB.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("금","C") -> {
                    fridayC.setText(cname.get(index.get(i)))
                    fridayC.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("금","D") -> {
                    fridayD.setText(cname.get(index.get(i)))
                    fridayD.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("금","E") -> {
                    fridayE.setText(cname.get(index.get(i)))
                    fridayE.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("금","F") -> {
                    fridayF.setText(cname.get(index.get(i)))
                    fridayF.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("금","G") -> {
                    fridayG.setText(cname.get(index.get(i)))
                    fridayG.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("금","H") -> {
                    fridayH.setText(cname.get(index.get(i)))
                    fridayH.setBackgroundResource(R.drawable.cell_shape_update)
                }
            }
            cnt = 0
            incnt=0
        }*/
    }


    }



}

