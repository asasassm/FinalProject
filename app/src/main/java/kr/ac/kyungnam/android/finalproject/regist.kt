package kr.ac.kyungnam.android.finalproject

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class regist : AppCompatActivity() {
    lateinit var myHelper: AddSchedule.myDBHelper
   lateinit var sqlDB : SQLiteDatabase
   lateinit var registid : EditText
    lateinit var registpw : EditText
    lateinit var registpwch : EditText
    lateinit var registname : EditText
    lateinit var registnum : EditText
    lateinit var registaddress : EditText
    lateinit var registpnum : EditText
    lateinit var btnback : Button
    lateinit var btnch : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.regist)
        myHelper = AddSchedule.myDBHelper(this)





        registid = findViewById(R.id.registid)
         registpw = findViewById(R.id.registpw)
         registpwch = findViewById(R.id.registpwch)
         registname = findViewById(R.id.registname)
        registnum = findViewById(R.id.registnum)
         registaddress = findViewById(R.id.registaddress)
         registpnum = findViewById(R.id.registPnum)
         btnback = findViewById(R.id.registback)
        btnch = findViewById (R.id.registch)



        btnch.setOnClickListener{
           registcheck()



        }








    }
    fun registcheck(){
        sqlDB = myHelper.writableDatabase
        if(registid.text.toString().isEmpty() || registpw.text.toString().isEmpty() || registname.text.toString().isEmpty()||registnum.text.toString().isEmpty() || registaddress.text.toString().isEmpty()||registpnum.text.toString().isEmpty()){
            Toast.makeText(applicationContext,"입력하지않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
        }else if (registpw.text.toString() != registpwch.text.toString()){
            Toast.makeText(applicationContext,"비빌먼호가 일치 하지 않습니다.", Toast.LENGTH_SHORT).show()
            registpwch.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            sqlDB.execSQL("INSERT INTO register VALUES('"+ registid.text.toString()+"','"+registpw.text.toString()+"','"+registname.text.toString()+"','"+registnum.text.toString()+ "','"+registaddress.text.toString()+"','"+registpnum.text.toString()+"');")
            sqlDB.close()
            Toast.makeText(applicationContext,"저장완료", Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }


    }
}
