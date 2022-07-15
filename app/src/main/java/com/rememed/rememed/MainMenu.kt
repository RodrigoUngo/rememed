package com.rememed.rememed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.rememed.rememed.ui.addsymptoms.AddSymptoms
import com.rememed.rememed.ui.historymeds.HistoryMeds
import com.rememed.rememed.ui.login.MainActivity
import com.rememed.rememed.ui.managers.Managers
import com.rememed.rememed.ui.medmanagers.MedManager
import com.rememed.rememed.ui.symptoms.Symptoms
import com.rememed.rememed.ui.prescriptions.Prescriptions


class MainMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val app by lazy{
            application as RemeMedApplication
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val btnGoBack: ImageButton = findViewById(R.id.ibBack)
        btnGoBack.setOnClickListener{
            finish()
        }

        val btnLogout: ImageButton = findViewById(R.id.ibSettings)
        btnLogout.setOnClickListener{
            app.deleteUsername()
            app.deleteAuthToken()
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        val btnPrescriptions: ImageButton = findViewById(R.id.ibPrescriptions)
        btnPrescriptions.setOnClickListener{
            Intent(this, Prescriptions::class.java).also {
                startActivity(it)
            }
        }

        val btnManagers: ImageButton = findViewById(R.id.ibManagers)
        btnManagers.setOnClickListener{
            Intent(this, Managers::class.java).also {
                startActivity(it)
            }
        }

        val btnMedHistory: ImageButton = findViewById(R.id.ibHistMeds)
        btnMedHistory.setOnClickListener{
            Intent(this, HistoryMeds::class.java).also {
                startActivity(it)
            }
        }

        val btnMedManagers: ImageButton = findViewById(R.id.ibMedManager)
        btnMedManagers.setOnClickListener{
            Intent(this, MedManager::class.java).also {
                startActivity(it)
            }
        }

        val btnSymptoms: ImageButton = findViewById(R.id.ibHistSymp)
        btnSymptoms.setOnClickListener{
            Intent(this, Symptoms::class.java).also {
                startActivity(it)
            }
        }

        val btnAddSymptoms: ImageButton = findViewById(R.id.ibSymptoms)
        btnAddSymptoms.setOnClickListener{
            Intent(this, AddSymptoms::class.java).also {
                startActivity(it)
            }
        }

    }
}