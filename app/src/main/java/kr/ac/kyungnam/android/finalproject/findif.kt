package kr.ac.kyungnam.android.finalproject

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kr.ac.kyungnam.android.finalproject.AddSchedule.myDBHelper
class findid : AppCompatActivity(){
    lateinit var findname :EditText
    lateinit var findphone : EditText
    lateinit var findid2 : TextView
    lateinit var findpw : EditText
    lateinit var btnfindback : Button
    lateinit var btnfind : Button
    lateinit var btnfindrepw: Button

    lateinit var tvfindid : TextView
    lateinit var myDBHelper: myDBHelper
    lateinit var sqlDB : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.findid)
        myDBHelper = myDBHelper(this)
       findname = findViewById(R.id.edtfindname)
        findphone =findViewById(R.id.findphone)
        findid2 = findViewById(R.id.findid2)
        findpw = findViewById(R.id.findpw)
        btnfindback = findViewById(R.id.btnfindback)
        btnfind = findViewById(R.id.btnfind)
        btnfindrepw = findViewById(R.id.btnfindrepw)
        tvfindid = findViewById(R.id.tvfindid)


        btnfindback.setOnClickListener{
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        btnfind.setOnClickListener{
            if(!findname.text.toString().isEmpty()&& !findphone.text.toString().isEmpty()){
                findmyid()
            }else{
                Toast.makeText(applicationContext,"입력하지 않는 정보가 있습니다.",Toast.LENGTH_SHORT).show()
            }
        }



    }
    fun findmyid(){
        sqlDB = myDBHelper.readableDatabase
        var cursor: Cursor
        cursor = sqlDB.rawQuery("SELECT * FROM register;", null)
        var fname = mutableListOf<String>()
        var fphone = mutableListOf<String>()
        var fid = mutableListOf<String>()
        var rcnt = 0
        var cnt=0
        var check=0
        while(cursor.moveToNext()){
            fid +=cursor.getString(0)
            fname += cursor.getString(2)
            fphone += cursor.getString(3)
            cnt=rcnt++
        }
        cursor.close()
        sqlDB.close()
        var index = 0
        var instate=0
        var state=0

        for(i in 0..cnt){
            if(findname.text.toString() == fname.get(i)&& findphone.text.toString()==fphone.get(i)){
                index = i
                state=1
            }
        }
            when(state){
                1->{
                    tvfindid.visibility= View.VISIBLE
                    findid2.visibility = View.VISIBLE
                    findid2.setText(fid.get(index))
                }
                else ->{
                    Toast.makeText(applicationContext,"일치하는 ID없음",Toast.LENGTH_SHORT).show()
                }
            }
            cnt=0

            index = 0


    }
}