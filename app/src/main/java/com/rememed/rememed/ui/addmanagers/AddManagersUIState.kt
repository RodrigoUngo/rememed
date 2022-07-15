package com.rememed.rememed.ui.addmanagers

import java.lang.Exception

sealed class AddManagersUIState {
    object Resume: AddManagersUIState()
    object Loading: AddManagersUIState()
    class Error(val exception: Exception): AddManagersUIState()
    data class Sucess(val token: String?): AddManagersUIState()
}