package kr.ac.kyungnam.android.finalproject

import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kr.ac.kyungnam.android.finalproject.AddSchedule.myDBHelper



class schedule : AppCompatActivity() {


    lateinit var myHelper: myDBHelper
    lateinit var sqlDB: SQLiteDatabase
    lateinit var btnadd: Button
    lateinit var btndel2 : Button
    lateinit var btnlogout: Button

    lateinit var mondayA: AutoResizeTextView
    lateinit var tuesdayA: AutoResizeTextView
    lateinit var wednesdayA: AutoResizeTextView
    lateinit var thursdayA: AutoResizeTextView
    lateinit var fridayA: AutoResizeTextView

    lateinit var mondayB: AutoResizeTextView
    lateinit var tuesdayB: AutoResizeTextView
    lateinit var wednesdayB: AutoResizeTextView
    lateinit var thursdayB: AutoResizeTextView
    lateinit var fridayB: AutoResizeTextView

    lateinit var mondayC: AutoResizeTextView
    lateinit var tuesdayC: AutoResizeTextView
    lateinit var wednesdayC: AutoResizeTextView
    lateinit var thursdayC: AutoResizeTextView
    lateinit var fridayC: AutoResizeTextView

    lateinit var mondayD: AutoResizeTextView
    lateinit var tuesdayD: AutoResizeTextView
    lateinit var wednesdayD: AutoResizeTextView
    lateinit var thursdayD: AutoResizeTextView
    lateinit var fridayD: AutoResizeTextView

    lateinit var mondayE: AutoResizeTextView
    lateinit var tuesdayE: AutoResizeTextView
    lateinit var wednesdayE: AutoResizeTextView
    lateinit var thursdayE: AutoResizeTextView
    lateinit var fridayE: AutoResizeTextView

    lateinit var mondayF: AutoResizeTextView
    lateinit var tuesdayF: AutoResizeTextView
    lateinit var wednesdayF: AutoResizeTextView
    lateinit var thursdayF: AutoResizeTextView
    lateinit var fridayF: AutoResizeTextView

    lateinit var mondayG: AutoResizeTextView
    lateinit var tuesdayG: AutoResizeTextView
    lateinit var wednesdayG: AutoResizeTextView
    lateinit var thursdayG: AutoResizeTextView
    lateinit var fridayG: AutoResizeTextView

    lateinit var mondayH: AutoResizeTextView
    lateinit var tuesdayH: AutoResizeTextView
    lateinit var wednesdayH: AutoResizeTextView
    lateinit var thursdayH: AutoResizeTextView
    lateinit var fridayH: AutoResizeTextView
    lateinit var tvtemp: AutoResizeTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule)

        myHelper = myDBHelper(this)
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
        btndel2 = findViewById(R.id.btndel2)
        btnlogout = findViewById(R.id.btnlogout)

        tvtemp.setText(App.prefs.getString("id", ""))
        read()
        btnadd.setOnClickListener {
            val intent = Intent(applicationContext, AddSchedule::class.java)
            startActivity(intent)
        }
        btndel2.setOnClickListener{
            val intent = Intent(applicationContext,delete::class.java)
            startActivity(intent)
        }
        btnlogout.setOnClickListener { view ->
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("로그아웃을 하시겠습니까?")
            dialog.setMessage("저희 Daily를 이용해주셔서 감사합니다.")
            fun toast_p() {
                Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            var dialog_listener = object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE ->
                            toast_p()
                    }
                }
            }
            dialog.setPositiveButton("YES", dialog_listener)
            dialog.setNegativeButton("NO", null)
            dialog.show()
        }
    }
    fun read() {
        sqlDB = myHelper.readableDatabase
        var cursor: Cursor
        var cid = mutableListOf<String>()
        var cname = mutableListOf<String>()
        var croom = mutableListOf<String>()
        var cweek = mutableListOf<String>()
        var ctime = mutableListOf<String>()
        var cidnum = mutableListOf<Int>()
        var cnt = 0
        var recnt = 0
        var nullch=0
        cursor = sqlDB.rawQuery(
            "SELECT * FROM scheduleDB WHERE Id = '" + tvtemp.text.toString() + "';",
            null
        )
        while (cursor.moveToNext()) {
            cid += cursor.getString(1)
            cname += cursor.getString(2)
            croom += cursor.getString(3)
            cweek += cursor.getString(4)
            ctime += cursor.getString(5)
            recnt = cnt++
            nullch++
        }
        cursor.close()
        sqlDB.close()

     if (nullch==0) {
            Toast.makeText(applicationContext,"시간표를 추가 하세요",Toast.LENGTH_SHORT).show()
        }   else {
            for (i in 0..recnt) {
                when (setOf(cweek.get(i), ctime.get(i))) {
                    setOf("월", "A") -> {
                        mondayA.setText(cname.get(i))
                        mondayA.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("월", "B") -> {
                        mondayB.setText(cname.get(i))
                        mondayB.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("월", "C") -> {
                        mondayC.setText(cname.get(i))
                        mondayC.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("월", "D") -> {
                        mondayD.setText(cname.get(i))
                        mondayD.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("월", "E") -> {
                        mondayE.setText(cname.get(i))
                        mondayE.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("월", "F") -> {
                        mondayF.setText(cname.get(i))
                        mondayF.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("월", "G") -> {
                        mondayG.setText(cname.get(i))
                        mondayG.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("월", "H") -> {
                        mondayH.setText(cname.get(i))
                        mondayH.setBackgroundResource(R.drawable.cell_shape_update)
                    }

                    setOf("화", "A") -> {
                        tuesdayA.setText(cname.get(i))
                        tuesdayA.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("화", "B") -> {
                        tuesdayB.setText(cname.get(i))
                        tuesdayB.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("화", "C") -> {
                        tuesdayC.setText(cname.get(i))
                        tuesdayC.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("화", "D") -> {
                        tuesdayD.setText(cname.get(i))
                        tuesdayD.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("화", "E") -> {
                        tuesdayE.setText(cname.get(i))
                        tuesdayE.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("화", "F") -> {
                        tuesdayF.setText(cname.get(i))
                        tuesdayF.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("화", "G") -> {
                        tuesdayG.setText(cname.get(i))
                        tuesdayG.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("화", "H") -> {
                        tuesdayH.setText(cname.get(i))
                        tuesdayH.setBackgroundResource(R.drawable.cell_shape_update)
                    }

                    setOf("수", "A") -> {
                        wednesdayA.setText(cname.get(i))
                        wednesdayA.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("수", "B") -> {
                        wednesdayB.setText(cname.get(i))
                        wednesdayB.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("수", "C") -> {
                        wednesdayC.setText(cname.get(i))
                        wednesdayC.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("수", "D") -> {
                        wednesdayD.setText(cname.get(i))
                        wednesdayD.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("수", "E") -> {
                        wednesdayE.setText(cname.get(i))
                        wednesdayE.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("수", "F") -> {
                        wednesdayF.setText(cname.get(i))
                        wednesdayF.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("수", "G") -> {
                        wednesdayG.setText(cname.get(i))
                        wednesdayG.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("수", "H") -> {
                        wednesdayH.setText(cname.get(i))
                        wednesdayH.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("목", "A") -> {
                        thursdayA.setText(cname.get(i))
                        thursdayA.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("목", "B") -> {
                        thursdayB.setText(cname.get(i))
                        thursdayB.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("목", "C") -> {
                        thursdayC.setText(cname.get(i))
                        thursdayC.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("목", "D") -> {
                        thursdayD.setText(cname.get(i))
                        thursdayD.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("목", "E") -> {
                        thursdayE.setText(cname.get(i))
                        thursdayE.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("목", "F") -> {
                        thursdayF.setText(cname.get(i))
                        thursdayF.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("목", "G") -> {
                        thursdayG.setText(cname.get(i))
                        thursdayG.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("목", "H") -> {
                        thursdayH.setText(cname.get(i))
                        thursdayH.setBackgroundResource(R.drawable.cell_shape_update)
                    }

                    setOf("금", "A") -> {
                        fridayA.setText(cname.get(i))
                        fridayA.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("금", "B") -> {
                        fridayB.setText(cname.get(i))
                        fridayB.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("금", "C") -> {
                        fridayC.setText(cname.get(i))
                        fridayC.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("금", "D") -> {
                        fridayD.setText(cname.get(i))
                        fridayD.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("금", "E") -> {
                        fridayE.setText(cname.get(i))
                        fridayE.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("금", "F") -> {
                        fridayF.setText(cname.get(i))
                        fridayF.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("금", "G") -> {
                        fridayG.setText(cname.get(i))
                        fridayG.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    setOf("금", "H") -> {
                        fridayH.setText(cname.get(i))
                        fridayH.setBackgroundResource(R.drawable.cell_shape_update)
                    }
                    else -> {}
                }
                cnt = 0

            }
        }


    }
}







