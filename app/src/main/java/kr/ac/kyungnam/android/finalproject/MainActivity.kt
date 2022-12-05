package kr.ac.kyungnam.android.finalproject

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kr.ac.kyungnam.android.finalproject.AddSchedule.myDBHelper
class MainActivity : AppCompatActivity() {
    lateinit var myHelper: myDBHelper
    lateinit var sqlDB : SQLiteDatabase
    lateinit var edtid : EditText
    lateinit var edtpw : EditText
    lateinit var btnfindid : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btnregist = findViewById<Button>(R.id.btnregist)
        var btnlog = findViewById<Button>(R.id.btnlog)
        btnfindid = findViewById(R.id.btnfindid)
        edtid = findViewById(R.id.edtid)
        edtpw = findViewById(R.id.edtpw)
        myHelper = myDBHelper(this)


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
        sqlDB = myHelper.readableDatabase
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
        if(edtid.text.toString().isEmpty()&&edtpw.text.toString().isEmpty()){
            Toast.makeText(applicationContext,"아이디 비밀번호를 입력하시오 .",Toast.LENGTH_SHORT).show()
        }else{
            for(i in 0..cnt){
                if(edtid.text.toString() == cid.get(i) && edtpw.text.toString() == cpw.get(i)){
                    check=1
                }else if(edtid.text.toString() == cid.get(i) && edtpw.text.toString() != cpw.get(i)){
                    check=2
                }else {
                    check = 3
                }
            }
            cnt = 0
            when(check){
                1->{
                    Toast.makeText(applicationContext,"로그인 되었습니다.",Toast.LENGTH_SHORT).show()
                    val nextintent = Intent(applicationContext,schedule::class.java)
                    startActivity(nextintent)
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