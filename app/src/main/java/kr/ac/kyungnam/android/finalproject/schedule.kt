package kr.ac.kyungnam.android.finalproject

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class schedule : AppCompatActivity() {


    lateinit var myHelper: AddSchedule.myDBHelper
    lateinit var sqlDB: SQLiteDatabase
    lateinit var btnadd: Button
    lateinit var btnre: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule)

        myHelper = AddSchedule.myDBHelper(this)
        sqlDB = myHelper.writableDatabase

        var mondayA = findViewById<AutoResizeTextView>(R.id.mondayA)
        var tuesdayA = findViewById<AutoResizeTextView>(R.id.tuesdayA)
        var wednesdayA = findViewById<AutoResizeTextView>(R.id.wednesdayA)
        var thursdayA = findViewById<AutoResizeTextView>(R.id.thursdayA)
        var fridayA = findViewById<AutoResizeTextView>(R.id.fridayA)

        var mondayB = findViewById<AutoResizeTextView>(R.id.mondayB)
        var tuesdayB = findViewById<AutoResizeTextView>(R.id.tuesdayB)
        var wednesdayB = findViewById<AutoResizeTextView>(R.id.wednesdayB)
        var thursdayB = findViewById<AutoResizeTextView>(R.id.thursdayB)
        var fridayB = findViewById<AutoResizeTextView>(R.id.fridayB)

        var mondayC = findViewById<AutoResizeTextView>(R.id.mondayC)
        var tuesdayC = findViewById<AutoResizeTextView>(R.id.tuesdayC)
        var wednesdayC = findViewById<AutoResizeTextView>(R.id.wednesdayC)
        var thursdayC = findViewById<AutoResizeTextView>(R.id.thursdayC)
        var fridayC = findViewById<AutoResizeTextView>(R.id.fridayC)

        var mondayD = findViewById<AutoResizeTextView>(R.id.mondayD)
        var tuesdayD = findViewById<AutoResizeTextView>(R.id.tuesdayD)
        var wednesdayD = findViewById<AutoResizeTextView>(R.id.wednesdayD)
        var thursdayD = findViewById<AutoResizeTextView>(R.id.thursdayD)
        var fridayD = findViewById<AutoResizeTextView>(R.id.fridayD)

        var mondayE = findViewById<AutoResizeTextView>(R.id.mondayE)
        var tuesdayE = findViewById<AutoResizeTextView>(R.id.tuesdayE)
        var wednesdayE = findViewById<AutoResizeTextView>(R.id.wednesdayE)
        var thursdayE = findViewById<AutoResizeTextView>(R.id.thursdayE)
        var fridayE = findViewById<AutoResizeTextView>(R.id.fridayE)

        var mondayF = findViewById<AutoResizeTextView>(R.id.mondayF)
        var tuesdayF = findViewById<AutoResizeTextView>(R.id.tuesdayF)
        var wednesdayF = findViewById<AutoResizeTextView>(R.id.wednesdayF)
        var thursdayF = findViewById<AutoResizeTextView>(R.id.thursdayF)
        var fridayF = findViewById<AutoResizeTextView>(R.id.fridayF)

        var mondayG = findViewById<AutoResizeTextView>(R.id.mondayG)
        var tuesdayG = findViewById<AutoResizeTextView>(R.id.tuesdayG)
        var wednesdayG = findViewById<AutoResizeTextView>(R.id.wednesdayG)
        var thursdayG = findViewById<AutoResizeTextView>(R.id.thursdayG)
        var fridayG = findViewById<AutoResizeTextView>(R.id.fridayG)

        var mondayH = findViewById<AutoResizeTextView>(R.id.mondayH)
        var tuesdayH = findViewById<AutoResizeTextView>(R.id.tuesdayH)
        var wednesdayH = findViewById<AutoResizeTextView>(R.id.wednesdayH)
        var thursdayH = findViewById<AutoResizeTextView>(R.id.thursdayH)
        var fridayH = findViewById<AutoResizeTextView>(R.id.fridayH)




        btnadd = findViewById(R.id.btnadd)
        btnre = findViewById(R.id.btnre)

    /*  sqlDB = myHelper.readableDatabase
        var cursor: Cursor
        var cname = mutableListOf<String>()
        var croom = mutableListOf<String>()
        var cweek = mutableListOf<String>()
        var ctime =mutableListOf<String>()

        var cnt =0
        var recnt =0
        cursor = sqlDB.rawQuery("SELECT * FROM scheduleDB;", null)
        while (cursor.moveToNext()) {
            cname += cursor.getString(0)
            croom += cursor.getString(1)
            cweek += cursor.getString(2)
            ctime += cursor.getString(3)
            recnt= cnt++
        }
        cursor.close()
        sqlDB.close()

        for(i in 0..recnt){
            when(setOf(cweek.get(i),ctime.get(i))){
                setOf("월","A") -> {
                    mondayA.setText(cname.get(i))
                    mondayA.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("월","B") -> {
                    mondayB.setText(cname.get(i))
                    mondayB.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("월","C") -> {
                    mondayC.setText(cname.get(i))
                    mondayC.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("월","D") -> {
                    mondayD.setText(cname.get(i))
                    mondayD.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("월","E") -> {
                    mondayE.setText(cname.get(i))
                    mondayE.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("월","F") -> {
                    mondayF.setText(cname.get(i))
                    mondayF.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("월","G") -> {
                    mondayG.setText(cname.get(i))
                    mondayG.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("월","H") -> {
                    mondayH.setText(cname.get(i))
                    mondayH.setBackgroundResource(R.drawable.cell_shape_update)
                }

                setOf("화","A") -> {
                    tuesdayA.setText(cname.get(i))
                    tuesdayA.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("화","B") -> {
                    tuesdayB.setText(cname.get(i))
                    tuesdayB.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("화","C") -> {
                    tuesdayC.setText(cname.get(i))
                    tuesdayC.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("화","D") -> {
                    tuesdayD.setText(cname.get(i))
                    tuesdayD.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("화","E") -> {
                    tuesdayE.setText(cname.get(i))
                    tuesdayE.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("화","F") -> {
                    tuesdayF.setText(cname.get(i))
                    tuesdayF.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("화","G") -> {
                    tuesdayG.setText(cname.get(i))
                    tuesdayG.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("화","H") -> {
                    tuesdayH.setText(cname.get(i))
                    tuesdayH.setBackgroundResource(R.drawable.cell_shape_update)
                }

                setOf("수","A") -> {
                    wednesdayA.setText(cname.get(i))
                    wednesdayA.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("수","B") -> {
                    wednesdayB.setText(cname.get(i))
                    wednesdayB.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("수","C") -> {
                    wednesdayC.setText(cname.get(i))
                    wednesdayC.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("수","D") -> {
                    wednesdayD.setText(cname.get(i))
                    wednesdayD.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("수","E") -> {
                    wednesdayE.setText(cname.get(i))
                    wednesdayE.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("수","F") -> {
                    wednesdayF.setText(cname.get(i))
                    wednesdayF.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("수","G") -> {
                    wednesdayG.setText(cname.get(i))
                    wednesdayG.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("수","H") -> {
                    wednesdayH.setText(cname.get(i))
                    wednesdayH.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("목","A") -> {
                    thursdayA.setText(cname.get(i))
                    thursdayA.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("목","B") -> {
                    thursdayB.setText(cname.get(i))
                    thursdayB.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("목","C") -> {
                    thursdayC.setText(cname.get(i))
                    thursdayC.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("목","D") -> {
                    thursdayD.setText(cname.get(i))
                    thursdayD.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("목","E") -> {
                    thursdayE.setText(cname.get(i))
                    thursdayE.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("목","F") -> {
                    thursdayF.setText(cname.get(i))
                    thursdayF.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("목","G") -> {
                    thursdayG.setText(cname.get(i))
                    thursdayG.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("목","H") -> {
                    thursdayH.setText(cname.get(i))
                    thursdayH.setBackgroundResource(R.drawable.cell_shape_update)
                }

                setOf("금","A") -> {
                    fridayA.setText(cname.get(i))
                    fridayA.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("금","B") -> {
                    fridayB.setText(cname.get(i))
                    fridayB.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("금","C") -> {
                    fridayC.setText(cname.get(i))
                    fridayC.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("금","D") -> {
                    fridayD.setText(cname.get(i))
                    fridayD.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("금","E") -> {
                    fridayE.setText(cname.get(i))
                    fridayE.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("금","F") -> {
                    fridayF.setText(cname.get(i))
                    fridayF.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("금","G") -> {
                    fridayG.setText(cname.get(i))
                    fridayG.setBackgroundResource(R.drawable.cell_shape_update)
                }
                setOf("금","H") -> {
                    fridayH.setText(cname.get(i))
                    fridayH.setBackgroundResource(R.drawable.cell_shape_update)
                }
            }
            cnt = 0
        }

*/







        btnadd.setOnClickListener {
            val intent = Intent(applicationContext, AddSchedule::class.java)
            startActivity(intent)
        }



        btnre.setOnClickListener {
            sqlDB = myHelper.readableDatabase
            var cursor: Cursor
            var cname = mutableListOf<String>()
            var croom = mutableListOf<String>()
            var cweek = mutableListOf<String>()
            var ctime =mutableListOf<String>()
            var recnt =0
            cursor = sqlDB.rawQuery("SELECT * FROM scheduleDB;", null)

            var cnt =0
            while (cursor.moveToNext()) {
                cname += cursor.getString(0)
                croom += cursor.getString(1)
                cweek += cursor.getString(2)
                ctime += cursor.getString(3)
                recnt= cnt++
            }
            cursor.close()
            sqlDB.close()
            for(i in 0..recnt){
                when(setOf(cweek.get(i),ctime.get(i))){
                    setOf("월","A") -> {
                        mondayA.setText(cname.get(i))
                        mondayA.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("월","B") -> {
                        mondayB.setText(cname.get(i))
                        mondayB.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("월","C") -> {
                        mondayC.setText(cname.get(i))
                        mondayC.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("월","D") -> {
                        mondayD.setText(cname.get(i))
                        mondayD.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("월","E") -> {
                        mondayE.setText(cname.get(i))
                        mondayE.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("월","F") -> {
                        mondayF.setText(cname.get(i))
                        mondayF.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("월","G") -> {
                        mondayG.setText(cname.get(i))
                        mondayG.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("월","H") -> {
                        mondayH.setText(cname.get(i))
                        mondayH.setBackgroundResource(R.drawable.cell_shape_update)
                    }

                    setOf("화","A") -> {
                        tuesdayA.setText(cname.get(i))
                        tuesdayA.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("화","B") -> {
                        tuesdayB.setText(cname.get(i))
                        tuesdayB.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("화","C") -> {
                        tuesdayC.setText(cname.get(i))
                        tuesdayC.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("화","D") -> {
                        tuesdayD.setText(cname.get(i))
                        tuesdayD.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("화","E") -> {
                        tuesdayE.setText(cname.get(i))
                        tuesdayE.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("화","F") -> {
                        tuesdayF.setText(cname.get(i))
                        tuesdayF.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("화","G") -> {
                        tuesdayG.setText(cname.get(i))
                        tuesdayG.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("화","H") -> {
                        tuesdayH.setText(cname.get(i))
                        tuesdayH.setBackgroundResource(R.drawable.cell_shape_update)
                    }

                    setOf("수","A") -> {
                        wednesdayA.setText(cname.get(i))
                        wednesdayA.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("수","B") -> {
                        wednesdayB.setText(cname.get(i))
                        wednesdayB.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("수","C") -> {
                        wednesdayC.setText(cname.get(i))
                        wednesdayC.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("수","D") -> {
                        wednesdayD.setText(cname.get(i))
                        wednesdayD.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("수","E") -> {
                        wednesdayE.setText(cname.get(i))
                        wednesdayE.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("수","F") -> {
                        wednesdayF.setText(cname.get(i))
                        wednesdayF.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("수","G") -> {
                        wednesdayG.setText(cname.get(i))
                        wednesdayG.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("수","H") -> {
                        wednesdayH.setText(cname.get(i))
                        wednesdayH.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("목","A") -> {
                        thursdayA.setText(cname.get(i))
                        thursdayA.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("목","B") -> {
                        thursdayB.setText(cname.get(i))
                        thursdayB.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("목","C") -> {
                        thursdayC.setText(cname.get(i))
                        thursdayC.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("목","D") -> {
                        thursdayD.setText(cname.get(i))
                        thursdayD.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("목","E") -> {
                        thursdayE.setText(cname.get(i))
                        thursdayE.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("목","F") -> {
                        thursdayF.setText(cname.get(i))
                        thursdayF.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("목","G") -> {
                        thursdayG.setText(cname.get(i))
                        thursdayG.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("목","H") -> {
                        thursdayH.setText(cname.get(i))
                        thursdayH.setBackgroundResource(R.drawable.cell_shape_update)
                    }

                    setOf("금","A") -> {
                        fridayA.setText(cname.get(i))
                        fridayA.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("금","B") -> {
                        fridayB.setText(cname.get(i))
                        fridayB.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("금","C") -> {
                        fridayC.setText(cname.get(i))
                        fridayC.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("금","D") -> {
                        fridayD.setText(cname.get(i))
                        fridayD.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("금","E") -> {
                        fridayE.setText(cname.get(i))
                        fridayE.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("금","F") -> {
                        fridayF.setText(cname.get(i))
                        fridayF.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("금","G") -> {
                        fridayG.setText(cname.get(i))
                        fridayG.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("금","H") -> {
                        fridayH.setText(cname.get(i))
                        fridayH.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                }
                cnt = 0
            }
        }


    }



}

