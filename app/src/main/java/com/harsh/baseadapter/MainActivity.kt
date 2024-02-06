package com.harsh.baseadapter

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.widget.Button
import android.widget.EditText
import android.widget.ListAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.harsh.baseadapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var array = arrayListOf<String>("Android","Class","11-1")
    var baseAdapter: BaseAdapterClass = BaseAdapterClass(array)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lv.adapter = baseAdapter
        binding.lv.setOnItemClickListener { adapterView, view, position, l ->
           // Toast.makeText(this,"Clicked $position",Toast.LENGTH_SHORT).show()
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_alert_dialogue)
            var etEnterUpdate = dialog.findViewById<EditText>(R.id.etEnterupdate)
            var btnUpdate = dialog.findViewById<Button>(R.id.btnUpdate)
            etEnterUpdate.setText(array[position])
            btnUpdate.setOnClickListener(){

                if (etEnterUpdate.text.toString().isNullOrEmpty()){
                    etEnterUpdate.error = "Enter Text"
                    return@setOnClickListener
                }
                array.set(position,etEnterUpdate.text.toString())
                baseAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            dialog.show()


        }
        binding.lv.setOnItemLongClickListener { adapterView, view, position, itemId ->


            val builder = AlertDialog.Builder(this)
            builder.setTitle("Are you sure you want to delete")
            builder.setPositiveButton("Yes",{ _,_ ->
                array.removeAt(position)
                baseAdapter.notifyDataSetChanged()
            })
            builder.setNegativeButton("No",{_,_ ->

            }).show()
            return@setOnItemLongClickListener true
        }
    binding.fab.setOnClickListener(){
        var dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_alert_dialogue)
        var etEnterUpdate = dialog.findViewById<EditText>(R.id.etEnterupdate)
        var btnUpdate = dialog.findViewById<Button>(R.id.btnUpdate)
        btnUpdate.setOnClickListener(){
            if (etEnterUpdate.text.toString().isNullOrEmpty()){
                etEnterUpdate.error = "Enter Text"
                return@setOnClickListener
            }
            array.add(etEnterUpdate.text.toString())
            baseAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        dialog.show()

    }

    }
}