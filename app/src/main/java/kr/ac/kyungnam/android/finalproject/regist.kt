package kr.ac.kyungnam.android.finalproject

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ThemedSpinnerAdapter

class regist : AppCompatActivity() {
    lateinit var reHelper : reDBHelper
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
        reHelper = reDBHelper(this)

        registid = findViewById(R.id.registid)
         registpw = findViewById(R.id.registpw)
         registpwch = findViewById(R.id.registpwch)
         registname = findViewById(R.id.registname)
        registnum = findViewById(R.id.registnum)
         registaddress = findViewById(R.id.registaddress)
         registpnum = findViewById(R.id.registPnum)
         btnback = findViewById(R.id.registback)
        btnch = findViewById (R.id.registch)
        var registre = findViewById<Button>(R.id.registre)

        btnback.setOnClickListener{
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
        btnch.setOnClickListener{
           registcheck()

        }
        registre.setOnClickListener{
           sqlDB = reHelper.writableDatabase
            reHelper.onUpgrade(sqlDB,1,2)
            sqlDB.close()
        }

    }
    fun registcheck(){
        sqlDB = reHelper.writableDatabase
        var re=idcheck()
        if(registid.text.toString().isEmpty() || registpw.text.toString().isEmpty() || registname.text.toString().isEmpty()||registnum.text.toString().isEmpty() || registaddress.text.toString().isEmpty()||registpnum.text.toString().isEmpty()){
            Toast.makeText(applicationContext,"입력하지않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
        }else if (registpw.text.toString() != registpwch.text.toString()){
            Toast.makeText(applicationContext,"비빌먼호가 일치 하지 않습니다.", Toast.LENGTH_SHORT).show()
            registpwch.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            when(re){
                0->{
                    sqlDB = reHelper.writableDatabase
                    sqlDB.execSQL("INSERT INTO register VALUES('"+ registid.text.toString()+"','"+registpw.text.toString()+"','"+registname.text.toString()+"','"+registnum.text.toString()+ "','"+registaddress.text.toString()+"','"+registpnum.text.toString()+"');")
                    sqlDB.close()
                    Toast.makeText(applicationContext,"저장완료", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                }
                1->Toast.makeText(applicationContext,"이미 사용 중인 ID 입니다.",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun idcheck() : Int{
        sqlDB = reHelper.readableDatabase
        var cursor: Cursor
        cursor = sqlDB.rawQuery("SELECT * FROM register;", null)
        var rid = mutableListOf<String>()
        var re = 0
        var rcnt = 0
        var cnt = 0
        while (cursor.moveToNext()) {
            rid += cursor.getString(0)
            cnt = rcnt++
        }
        cursor.close()
        sqlDB.close()
        for(i in 0 ..cnt){
            if(rid.get(i) == registid.text.toString()){
                re=1
                break
            }else{
                re =0
            }
        }
        return re
    }
    class reDBHelper(context: Context) : SQLiteOpenHelper(context, "register", null, 1) {
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL("CREATE TABLE register(ID CHAR(20) PRIMARY KEY ,Password CHAR(20),Name CHAR(10),Number CHAR(14),Address CHAR(50),PhoneNumber CHAR(14));")
            db.execSQL("INSERT INTO register VALUES ('admin','123','admin','0','0','0');")
        }
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS register")
            onCreate(db)
        }
    }
}

