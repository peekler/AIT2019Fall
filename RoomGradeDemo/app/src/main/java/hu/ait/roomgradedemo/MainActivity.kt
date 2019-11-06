package hu.ait.roomgradedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.roomgradedemo.data.AppDatabase
import hu.ait.roomgradedemo.data.Grade
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave.setOnClickListener {
            var newGrade = Grade(null,
                etStudent.text.toString(),
                etGrade.text.toString())

            saveGrade(newGrade)
        }

        btnQuery.setOnClickListener {
            queryAllGrades()
        }
    }

    fun saveGrade(grade: Grade) {
        /* var saveThread = Thread() {
             fun run() {
                 //....
             }
         }
         saveThread.start()*/

        Thread {
            // this is the body of the run method
            AppDatabase.getInstance(this@MainActivity).gradeDao().insertGrades(grade)
        }.start()
    }


    fun queryAllGrades(){
        Thread {
            val allGrades = AppDatabase.getInstance(this@MainActivity).
                gradeDao().getSpecificGrades("A+")

            runOnUiThread {
                tvResult.text = ""
                allGrades.forEach {
                    tvResult.append("${it.studentName} ${it.grade}\n")
                }
            }
        }.start()
    }
}
