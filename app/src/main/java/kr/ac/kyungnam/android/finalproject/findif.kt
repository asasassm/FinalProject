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
import org.w3c.dom.Text

class findid : AppCompatActivity(){
    lateinit var findname :EditText
    lateinit var findphone : EditText
    lateinit var findid2 : EditText
    lateinit var resetpw : EditText
    lateinit var btnfindback : Button
    lateinit var btnfind : Button
    lateinit var btnfindrepw: Button
    lateinit var btnfindrepwch : Button
    lateinit var tvrepw : TextView
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
        resetpw = findViewById(R.id.resetpw)
        btnfindback = findViewById(R.id.btnfindback)
        btnfind = findViewById(R.id.btnfind)
        btnfindrepw = findViewById(R.id.btnfindrepw)
        tvfindid = findViewById(R.id.tvfindid)
        btnfindrepwch = findViewById(R.id.btnfindrepwch)
        tvrepw = findViewById(R.id.tvrepw)
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
        btnfindrepw.setOnClickListener{
            tvrepw.visibility = View.VISIBLE
            resetpw.visibility=View.VISIBLE
            btnfindrepwch.visibility=View.VISIBLE

        }
        btnfindrepwch.setOnClickListener{
            updateregister()
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
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
            fphone += cursor.getString(5)
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
                    btnfindrepw.visibility=View.VISIBLE
                }
                else ->{
                    Toast.makeText(applicationContext,"일치하는 ID없음",Toast.LENGTH_SHORT).show()
                }
            }
            cnt=0

            index = 0


    }
    fun updateregister(){
        sqlDB=myDBHelper.writableDatabase
       if(findid2.text.toString().isEmpty()){
           Toast.makeText(applicationContext,"텍스트 인식안됨",Toast.LENGTH_SHORT).show()
       }else{
           sqlDB.execSQL("UPDATE register SET Password='"+resetpw.text.toString()+"'WHERE ID='"+findid2.text.toString()+"';")
           sqlDB.close()
           Toast.makeText(applicationContext,"수정완료",Toast.LENGTH_SHORT).show()
       }
    }


}