package id.ac.unpas.showroom.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unpas.showroom.model.SR_Mobil
import id.ac.unpas.showroom.repositories.SR_MobilRepository
import javax.inject.Inject

@HiltViewModel
class PengelolaanMobilViewModel  @Inject constructor(private val sr_MobilRepository: SR_MobilRepository) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _success: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val success: LiveData<Boolean> get() = _success
    private val _toast: MutableLiveData<String> =
        MutableLiveData()
    val toast: LiveData<String> get() = _toast
    private val _list: MutableLiveData<List<SR_Mobil>> =
        MutableLiveData()
    val list: LiveData<List<SR_Mobil>> get() = _list

    suspend fun loadItems()
    {
        _isLoading.postValue(true)
        sr_MobilRepository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _list.postValue(it)
        }, onError = { list, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _list.postValue(list)
        })
        }

    suspend fun insert(merk: String,
                       model: String,
                       bahanBakar: String,
                       dijual: String,
                       deskripsi: String){
            _isLoading.postValue(true)
            sr_MobilRepository.insert(merk, model, bahanBakar, dijual, deskripsi,
                onError = { item, message ->
                    _toast.postValue(message)
                    _isLoading.postValue(false)
                }, onSuccess = {
                    _isLoading.postValue(false)
                    _success.postValue(true)
                })
        }

    suspend fun loadItem(id: String, onSuccess: (SR_Mobil?)
    -> Unit) {
        val item = sr_MobilRepository.find(id)
        onSuccess(item)
    }
    suspend fun update(id: String,
                       merk: String,
                       model: String,
                       bahanBakar: String,
                       dijual: String,
                       deskripsi: String){
        _isLoading.postValue(true)
        sr_MobilRepository.update(id, merk, model, bahanBakar, dijual, deskripsi,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }

}