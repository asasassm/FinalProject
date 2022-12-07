package kr.ac.kyungnam.android.finalproject

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kr.ac.kyungnam.android.finalproject.regist.reDBHelper
import kr.ac.kyungnam.android.finalproject.AddSchedule.myDBHelper

class MainActivity : AppCompatActivity() {
    lateinit var reHelper: reDBHelper
    lateinit var sqlDB : SQLiteDatabase
    lateinit var edtid : EditText
    lateinit var edtpw : EditText
    lateinit var btnfindid : Button
    lateinit var myHelper : myDBHelper
    lateinit var sqlDB2 : SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btnregist = findViewById<Button>(R.id.btnregist)
        var btnlog = findViewById<Button>(R.id.btnlog)
        btnfindid = findViewById(R.id.btnfindid)
        edtid = findViewById(R.id.edtid)
        edtpw = findViewById(R.id.edtpw)
        reHelper = reDBHelper(this)
        myHelper=myDBHelper(this)
        sqlDB2 = myHelper.writableDatabase

        btnregist.setOnClickListener {
            val intent = Intent(applicationContext, regist::class.java)
            startActivity(intent)
        }
        btnlog.setOnClickListener {


             logcheck()
        }
        btnfindid.setOnClickListener{
            val intent = Intent(applicationContext, findid::class.java)
            startActivity(intent)
        }
    }
    fun logcheck(){
        sqlDB = reHelper.readableDatabase
        sqlDB2 = myHelper.writableDatabase
        var cursor: Cursor
        cursor = sqlDB.rawQuery("SELECT * FROM register;", null)
        var cid = mutableListOf<String>()
        var cpw = mutableListOf<String>()
        var rcnt = 0
        var cnt=0
        var check=0
        while(cursor.moveToNext()){
            cid += cursor.getString(0)
            cpw += cursor.getString(1)
            cnt=rcnt++
        }
        cursor.close()
        sqlDB.close()
        if(cid.get(0).isEmpty()){
            cid.add("")
            cpw.add("")
            Toast.makeText(applicationContext,"회원가입 먼저 해주세요 .",Toast.LENGTH_SHORT).show()
        }else if(edtid.text.toString().isEmpty()&&edtpw.text.toString().isEmpty()){
            Toast.makeText(applicationContext,"아이디 비밀번호를 입력하시오 .",Toast.LENGTH_SHORT).show()
        }else{
            for(i in 0..cnt){
                if(edtid.text.toString() == cid.get(i) && edtpw.text.toString() == cpw.get(i)){
                    check=1
                    break
                }else if(edtid.text.toString() == cid.get(i) && edtpw.text.toString() != cpw.get(i)){
                    check=2
                    break
                }else {
                    check = 3
                }
            }
            cnt = 0
            val sID = edtid.text.toString()
            when(check){
                1->{
                    Toast.makeText(applicationContext,"로그인 되었습니다.",Toast.LENGTH_SHORT).show()
                    sqlDB2.execSQL("INSERT INTO scheduleDB (Id,ClassName,ClassRoom,ClassDay,ClassTime) VALUES ('"+edtid.text.toString()+"','N','N','N','N');")
                    sqlDB2.close()

                    App.prefs.setString("id",edtid.text.toString())
                    val intent = Intent(applicationContext,schedule::class.java)

                    startActivity(intent)
                }
                2->{
                    Toast.makeText(applicationContext,"아이디/비밀번호가 맞지 않습니다.",Toast.LENGTH_SHORT).show()
                }
                3->{
                    Toast.makeText(applicationContext,"존재 하지 않는 아이디입니다.",Toast.LENGTH_SHORT).show()
                }
                else->  Toast.makeText(applicationContext,"오류.",Toast.LENGTH_SHORT).show()

            }
            check=0

        }

    }

}