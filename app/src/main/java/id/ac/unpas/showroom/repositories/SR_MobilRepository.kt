package id.ac.unpas.showroom.repositories

import com.benasher44.uuid.uuid4
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import id.ac.unpas.showroom.model.SR_Mobil
import id.ac.unpas.showroom.networks.SR_MobilApi
import id.ac.unpas.showroom.persistences.SR_MobilDao
import javax.inject.Inject

class SR_MobilRepository  @Inject constructor(
    private val api: SR_MobilApi,
    private val dao: SR_MobilDao) : Repository
{
    suspend fun loadItems(
        onSuccess: (List<SR_Mobil>) -> Unit,
        onError: (List<SR_Mobil>, String) -> Unit
    ) {
        val list: List<SR_Mobil> = dao.getList()
        api.all()
            // handle the case when the API request gets a success response.
            .suspendOnSuccess {
                data.whatIfNotNull {
                    it.data?.let { list ->
                        dao.insertAll(list)
                        val items: List<SR_Mobil> =
                            dao.getList()
                        onSuccess(items)
                    }
                }
            }
            // handle the case when the API request gets anerror response.
            // e.g. internal server error.
            .suspendOnError {
                onError(list, message())
            }
            // handle the case when the API request gets anexception response.
            // e.g. network connection error.
            .suspendOnException {
                onError(list, message())
            }
    }

    suspend fun insert(
        merk: String,
        model: String,
        bahanBakar: String,
        dijual: String,
        deskripsi: String,
        onSuccess: (SR_Mobil) -> Unit,
        onError: (SR_Mobil?, String) -> Unit
    ) {
        val id = uuid4().toString()
        val item = SR_Mobil(id, merk, model, bahanBakar, dijual, deskripsi)
        dao.insertAll(item)
        api.insert(item)
            // handle the case when the API request gets asuccess response.
            .suspendOnSuccess {
                onSuccess(item)
            }
            // handle the case when the API request gets anerror response.
            // e.g. internal server error.
            .suspendOnError {
                onError(item, message())
            }
            // handle the case when the API request gets anexception response.
            // e.g. network connection error.
            .suspendOnException {
                onError(item, message())
            }
    }

    suspend fun update(
        id: String,
        merk: String,
        model: String,
        bahanBakar: String,
        dijual: String,
        deskripsi: String,
        onSuccess: (SR_Mobil) -> Unit,
        onError: (SR_Mobil?, String) -> Unit
    ) {
        val item = SR_Mobil(id, merk, model, bahanBakar, dijual, deskripsi)
        dao.insertAll(item)
        api.update(id,item)
            // handle the case when the API request gets asuccess response.
            .suspendOnSuccess {
                onSuccess(item)
            }
            // handle the case when the API request gets anerror response.
            // e.g. internal server error.
            .suspendOnError {
                onError(item, message())
            }
            // handle the case when the API request gets anexception response.
            // e.g. network connection error.
            .suspendOnException {
                onError(item, message())
            }
    }

    suspend fun delete(
        id: String, onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        dao.delete(id)
        api.delete(id)
            // handle the case when the API request gets asuccess response.
            .suspendOnSuccess {
                data.whatIfNotNull {
                    onSuccess()
                }
            }
            // handle the case when the API request gets anerror response.
            // e.g. internal server error.
            .suspendOnError {
                onError(message())
            }
            // handle the case when the API request gets anexception response.
            // // e.g. network connection error.
            .suspendOnException {
                onError(message())
            }
    }

    suspend fun find(id: String) : SR_Mobil? {
        return dao.find(id)
    }

}