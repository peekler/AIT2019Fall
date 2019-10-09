package hu.autsoft.viewdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val cities = arrayOf(
        "Budapest", "Bukarest", "New York", "New Delhi", "New Orleans", "New Year")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cityAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            cities
        )

        autoCompleteCities.setAdapter(cityAdapter)


        rbBlack.setOnClickListener {
            layoutContent.setBackgroundColor(Color.BLACK)
        }
        rbBlue.setOnClickListener {
            layoutContent.setBackgroundColor(Color.BLUE)
        }
        rbWhite.setOnClickListener {
            layoutContent.setBackgroundColor(Color.WHITE)
        }


        rgBackgroundColors.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)

                Toast.makeText(applicationContext," On checked change : ${radio.text}",
                    Toast.LENGTH_SHORT).show()
            })


        var fruitsAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.fruits_array,
            android.R.layout.simple_spinner_item
        )
        fruitsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFruits.adapter = fruitsAdapter

        spinnerFruits.onItemSelectedListener = this

        btnSubmit.setOnClickListener {
            Toast.makeText(this@MainActivity,
                spinnerFruits.selectedItem.toString(),
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        Toast.makeText(this, parent?.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()
    }

}
