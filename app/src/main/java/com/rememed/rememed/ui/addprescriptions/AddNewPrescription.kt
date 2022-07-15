package com.rememed.rememed.ui.addprescriptions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import com.google.android.material.chip.Chip
import com.rememed.rememed.R
import com.rememed.rememed.RemeMedApplication
import com.rememed.rememed.ViewModelFactory
import com.rememed.rememed.databinding.ActivityAddNewPrescriptionBinding
import com.rememed.rememed.ui.prescriptions.Prescriptions
import com.rememed.rememed.ui.timepicker.TimePicker


class AddNewPrescription : AppCompatActivity() {
    private lateinit var binding: ActivityAddNewPrescriptionBinding
    val app by lazy{
        application as RemeMedApplication
    }
    private val viewModelFactory by lazy {
        ViewModelFactory(app.getPrescriptionRepository())
    }

    private val viewModel: AddNewPrescriptionViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_prescription)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_prescription)
        binding.viewModel = viewModel
        //listener para que se abra el timepicker
        val etTime = findViewById<EditText>(R.id.etTime)
        etTime.setOnClickListener{ showTimePickerDialog()}

        val spnMedicaments = findViewById<Spinner>(R.id.spnMedicaments)
        //lista de medicamentos
        val listMedicaments = listOf("Acetaminofen","Aspirina","Loratadina","Virogrip","Amoxicilina",
            "Neumosil","Diclofenaco","Vitamina C","Vitamina B","Dextrometorfano","Pespto Bismol","Omeprazol",
            "Simvastatina","Ramipril","Salbutamol","Amlodipina","Aderall","Ventolin","Losartan", "Enalapril")
        val adapterMedicaments = ArrayAdapter(this, android.R.layout.simple_spinner_item, listMedicaments)
        spnMedicaments.adapter = adapterMedicaments

        val spnDose = findViewById<Spinner>(R.id.spnDose)
        //lista de unidades de dosis
        val listDose = listOf("mg","ml","tableta(s)", "aplicacion(es)","otro")
        val adapterDose = ArrayAdapter(this,android.R.layout.simple_spinner_item, listDose)
        spnDose.adapter = adapterDose

        val spnPerWeek = findViewById<Spinner>(R.id.spnPerWeek)
        //lista de veces por semana
        val listPerWeek = resources.getStringArray(R.array.perWeek)
        val adapterPerWeek = ArrayAdapter(this, android.R.layout.simple_spinner_item, listPerWeek)
        spnPerWeek.adapter = adapterPerWeek

        val spnDaysOfWeek = findViewById<Spinner>(R.id.spnDaysOfWeek)
        //Dias de la semana
        val listDaysOfWeek = resources.getStringArray(R.array.DaysOfWeek)
        val adapterDaysOfWeek = ArrayAdapter(this, android.R.layout.simple_spinner_item, listDaysOfWeek)
        spnDaysOfWeek.adapter = adapterDaysOfWeek

        val chipYes = findViewById<Chip>(R.id.chipYes)

        binding.etnDose.doAfterTextChanged { s ->
            viewModel.dosisField.value = s.toString()
        }

        binding.etnHoras.doAfterTextChanged { s ->
            viewModel.hoursField.value = s.toString()
        }

        binding.etTime.doAfterTextChanged { s ->
            viewModel.timeField.value = s.toString()
        }

        viewModel.status.observe(this){ status ->
            handleUiState(status)

        }

        val user = app.getUsername()

        var medicament = ""
        var dosisType = ""
        var daysPerWeek = ""
        var day = ""
        var food = ""

        val btnAdd: Button = findViewById(R.id.btnAddNewPrescription)
        btnAdd.setOnClickListener {
            medicament = spnMedicaments.selectedItem.toString()
            dosisType = spnDose.selectedItem.toString()
            daysPerWeek = spnPerWeek.selectedItem.toString()
            day = spnDaysOfWeek.selectedItem.toString()
            if(chipYes.isChecked){
                food = "Con comida"
            }else{
                food = "Sin comida"
            }
            viewModel.onSave(medicament, dosisType, daysPerWeek, day, food, user)
        }

        val btnGoBack: ImageButton = findViewById(R.id.ibBack)
        btnGoBack.setOnClickListener{
            finish()
        }
    }

    //funcion que abre el timepicker
    private fun showTimePickerDialog(){
        val timePicker = TimePicker{onTimeSelected(it)}
        timePicker.show(supportFragmentManager,"time")
    }

    //funcion para hacer que lo seleccionado en el timepicker se muestre en el editText
    private fun onTimeSelected(time:String){
        val etTime = findViewById<EditText>(R.id.etTime)
        etTime.setText("$time")
    }

    private fun handleUiState(status: AddNewPrescriptionUIState){
        when(status){
            is AddNewPrescriptionUIState.Error -> Log.d("Login List Status","Error")
            AddNewPrescriptionUIState.Loading -> Log.d("Login List Status","Loading")
            AddNewPrescriptionUIState.Resume -> Log.d("Login List Status","Resume")
            is AddNewPrescriptionUIState.Sucess -> {
                Intent(this, Prescriptions::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }

}