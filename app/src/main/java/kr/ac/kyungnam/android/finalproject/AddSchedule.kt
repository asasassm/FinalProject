package kr.ac.kyungnam.android.finalproject

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddSchedule : AppCompatActivity() {

    lateinit var sptextday : TextView
    lateinit var sptexttime : TextView

    lateinit var edtName : EditText
    lateinit var edtRoom : EditText
    lateinit var edtDay : Spinner
    lateinit var edtTime : Spinner
    lateinit var saveID : TextView
    lateinit var myHelper: myDBHelper

    lateinit var btninsert : Button
    lateinit var sqlDB : SQLiteDatabase

    lateinit var btnback : Button
    lateinit var btnreset : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addschedule)
        title = "시간표 추가"

        sptextday = findViewById(R.id.sptextday)
        sptexttime = findViewById(R.id.sptexttime)
        edtName = findViewById(R.id.edtName)
        edtRoom=findViewById(R.id.edtRoom)
        edtDay = findViewById(R.id.edtDay)
        edtTime = findViewById(R.id.edttime)
        btnback = findViewById(R.id.btnback)
        btnreset = findViewById(R.id.btnreset)
        btninsert = findViewById(R.id.btninsert)
        saveID =findViewById(R.id.saveID)

        saveID.setText(App.prefs.getString("id",""))

        edtDay.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                p2: Int,
                p3: Long
            ) {
                sptextday.text ="" +edtDay.selectedItem
                sptextday.visibility=View.INVISIBLE
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        edtTime.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                p2: Int,
                p3: Long
            ) {
                sptexttime.text ="" +edtTime.selectedItem
                sptexttime.visibility=View.INVISIBLE
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        myHelper = myDBHelper(this)
        btnback.setOnClickListener{
            val intent = Intent(applicationContext,schedule::class.java)
            startActivity(intent)
        }
        btninsert.setOnClickListener{

            insert()

        }
        btnreset.setOnClickListener{
            sqlDB = myHelper.writableDatabase
            myHelper.onUpgrade(sqlDB,1,2)
           // sqlDB.execSQL("INSERT INTO scheduleDB (Id,ClassName,ClassRoom,ClassDay,ClassTime) VALUES ('"+saveID.text.toString()+"','N','N','N','N');")
            sqlDB.close()
        }
    }
    private fun insert(){
        sqlDB = myHelper.writableDatabase
        if(edtName.text.toString().isEmpty() || edtRoom.text.toString().isEmpty() || sptextday.text.toString().isEmpty()||sptexttime.text.toString().isEmpty()){
            Toast.makeText(applicationContext,"입력하지않은 정보가 있습니다.",Toast.LENGTH_SHORT).show()
        }else{
            sqlDB.execSQL("INSERT INTO scheduleDB (Id,ClassName,ClassRoom,ClassDay,ClassTime) VALUES('"+ saveID.text.toString()+"','"+ edtName.text.toString()+"','"+edtRoom.text.toString()+"','"+sptextday.text.toString()+"','"+sptexttime.text.toString()+ "');")
           // sqlDB.execSQL("DELETE FROM scheduleDB WHERE Id = '"+saveID.text.toString()+"' AND ClassName= 'N';")
            sqlDB.close()
            Toast.makeText(applicationContext,"저장완료",Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext,schedule::class.java)
            startActivity(intent)
        }
    }
    class myDBHelper(context: Context) : SQLiteOpenHelper(context, "scheduleDB", null, 1) {
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL("CREATE TABLE scheduleDB(Inumber INTEGER PRIMARY KEY AUTOINCREMENT,Id CHAR(10) ,ClassName CHAR(20)  ,ClassRoom CHAR(20),ClassDay CHAR(1),ClassTime CHAR(1));")
           db!!.execSQL("INSERT INTO scheduleDB (Id,ClassName,ClassRoom,ClassDay,ClassTime) VALUES ('admin','123','0','0','0');")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS scheduleDB")
            onCreate(db)
        }
    }
}