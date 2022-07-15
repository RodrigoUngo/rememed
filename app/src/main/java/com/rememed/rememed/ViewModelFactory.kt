package com.rememed.rememed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rememed.rememed.repository.*
import com.rememed.rememed.ui.addmanagers.AddManagersViewModel
import com.rememed.rememed.ui.addmedmanagers.AddMedManagerViewModel
import com.rememed.rememed.ui.addprescriptions.AddNewPrescriptionViewModel
import com.rememed.rememed.ui.addsymptoms.AddSymptomsViewModel
import com.rememed.rememed.ui.login.LoginViewModel
import com.rememed.rememed.ui.managers.ManagerViewModel
import com.rememed.rememed.ui.medmanagers.DoctorViewModel
import com.rememed.rememed.ui.prescriptions.PrescriptionViewModel
import com.rememed.rememed.ui.register.RegisterViewModel
import com.rememed.rememed.ui.symptoms.SymptomsViewModel

class ViewModelFactory<R>(private val repository: R):
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(DoctorViewModel::class.java)){
            return DoctorViewModel(repository as DoctorRepository) as T
        }
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(repository as UserRepository) as T
        }
        if(modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            return RegisterViewModel(repository as UserRepository) as T
        }
        if(modelClass.isAssignableFrom(ManagerViewModel::class.java)){
            return ManagerViewModel(repository as ManagerRepository) as T
        }
        if(modelClass.isAssignableFrom(AddManagersViewModel::class.java)){
            return AddManagersViewModel(repository as ManagerRepository) as T
        }
        if(modelClass.isAssignableFrom(AddMedManagerViewModel::class.java)){
            return AddMedManagerViewModel(repository as DoctorRepository) as T
        }
        if(modelClass.isAssignableFrom(SymptomsViewModel::class.java)){
            return SymptomsViewModel(repository as SymptomRepository) as T
        }
        if(modelClass.isAssignableFrom(AddSymptomsViewModel::class.java)){
            return AddSymptomsViewModel(repository as SymptomRepository) as T
        }
        if(modelClass.isAssignableFrom(AddNewPrescriptionViewModel::class.java)){
            return AddNewPrescriptionViewModel(repository as PrescriptionRepository) as T
        }
        if(modelClass.isAssignableFrom(PrescriptionViewModel::class.java)){
            return PrescriptionViewModel(repository as PrescriptionRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}