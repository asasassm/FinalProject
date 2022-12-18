package kr.ac.kyungnam.android.finalproject

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kr.ac.kyungnam.android.finalproject.AddSchedule.myDBHelper

class delete : AppCompatActivity() {
    lateinit var btncan : Button
    lateinit var btndelete : Button
    lateinit var edtnamed : EditText
    lateinit var tvid : TextView
    lateinit var myHelper: myDBHelper
    lateinit var sqlDB : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delete)
        btncan = findViewById(R.id.btncan)
        btndelete = findViewById(R.id.btndelete)
        edtnamed = findViewById(R.id.edtnamed)
        myHelper = myDBHelper(this)
        tvid = findViewById(R.id.tvid)
        tvid.setText(App.prefs.getString("id", ""))
        btndelete.setOnClickListener{
                checkname()
        }
        btncan.setOnClickListener{
            val intent = Intent(applicationContext,schedule::class.java)
            startActivity(intent)
        }
/*sqlDB = myHelper.writableDatabase
            sqlDB.execSQL("DELETE FROM scheduleDB WHERE ClassName = '"+edtnamed.text.toString()+"';")
            sqlDB.close()
            Toast.makeText(applicationContext," 삭제했습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext,schedule::class.java)
            startActivity(intent)*/

    }
    fun checkname(){
        sqlDB = myHelper.readableDatabase
        var cursor: Cursor
        var did = mutableListOf<String>()
        var dname = mutableListOf<String>()
        var dcnt = 0
        var drcnt=0
        var ch=0
        var dre=0
        cursor = sqlDB.rawQuery(
            "SELECT * FROM scheduleDB WHERE Id = '" + tvid.text.toString() + "';", null)
        while (cursor.moveToNext()) {
            did += cursor.getString(1)
            dname += cursor.getString(2)
            drcnt = dcnt++
        }
        cursor.close()
        sqlDB.close()
        for(i in 0..drcnt){
           if(dname.get(i) == edtnamed.text.toString()){
               ch = 1
               dre = ch
               break
           }else{
               ch=0
               dre = ch
               break
           }
        }
        when(dre){
            0-> Toast.makeText(applicationContext," 일치하는 시간표가 없습니다.", Toast.LENGTH_SHORT).show()
            1-> {
                sqlDB = myHelper.writableDatabase
                sqlDB.execSQL("DELETE FROM scheduleDB WHERE Id = '" +tvid.text.toString() +"' AND ClassName = '"+edtnamed.text.toString()+"';")
                sqlDB.close()
               dre =0
                Toast.makeText(applicationContext," 삭제했습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext,schedule::class.java)
                startActivity(intent)
            }
        }
    }

}