package com.example.itext_7_pdf.SharedPreffernce

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val preference_Name = "My_Preference"
class SharedPrefference(context: Context) {
    val Context.datastore by preferencesDataStore(preference_Name)
    val pref = context.datastore

    suspend fun setDetails(details: InvoiceDetails){
        pref.edit {
            it[keys.createdDate] = details.createdDate
            it[keys.duedate] = details.dueDate
            it[keys.invoiceNumber] = details.invoiceNumber
            it[keys.totalInvoice] = details.totalInvoice
            it[keys.totalMOney] = details.totalMoney
        }
    }
    fun getDetailsfun(): Flow<InvoiceDetails> {
        return pref.data.map {
            InvoiceDetails(
                invoiceNumber = it[keys.invoiceNumber] ?: "",
                createdDate = it[keys.createdDate] ?: "",
                dueDate = it[keys.duedate] ?: "",
                totalInvoice = it[keys.totalInvoice] ?: "",
                totalMoney = it[keys.totalMOney] ?: ""
            )
        }
    }
    fun getDetailsSingle(key: Preferences.Key<String>): Flow<String?> {
        return pref.data.map {
            it[key] ?: "null"
        }
    }

    suspend fun setDetailsSingle(key : Preferences.Key<String>, data : String){
        pref.edit {

            it[key] = data
        }
    }

    fun getData(key : String): Flow<String?> {
       return getDetailsSingle(stringPreferencesKey(key))

    }
    suspend fun setData(key : String,Data : String){

        setDetailsSingle(stringPreferencesKey(key),Data)
    }


}



object keys{
    val invoiceNumber = stringPreferencesKey("invoiceNumber")
    val createdDate = stringPreferencesKey("createdDate")
    val duedate = stringPreferencesKey("duedate")
    val totalInvoice = stringPreferencesKey("totalInvoice")
    val totalMOney = stringPreferencesKey("totalMoney")
}