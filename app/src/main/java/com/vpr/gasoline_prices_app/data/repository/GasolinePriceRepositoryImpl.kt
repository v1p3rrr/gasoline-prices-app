package com.vpr.gasoline_prices_app.data.repository

import com.vpr.gasoline_prices_app.data.local.GasolinePriceDao
import com.vpr.gasoline_prices_app.data.remote.GasolinePricesService
import com.vpr.gasoline_prices_app.data.remote.dto.CityWithPriceListDTO
import com.vpr.gasoline_prices_app.domain.model.GasolinePrice
import com.vpr.gasoline_prices_app.domain.repository.GasolinePriceRepository
import com.vpr.gasoline_prices_app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GasolinePriceRepositoryImpl @Inject constructor(
    private val api: GasolinePricesService,
    private val dao: GasolinePriceDao
) : GasolinePriceRepository {
    override fun getGasolinePricesByCityAndDate(city: String, date: String): Flow<Resource<GasolinePrice>> = flow {
        emit(Resource.Loading())

        val localGasolinePrice : GasolinePrice? = dao.getGasolinePriceByCityAndDate(city = city, date = date)?.toGasolinePrice()

        emit(Resource.Loading(localGasolinePrice))

        try {
            val apiGasolinePriceList: CityWithPriceListDTO = api.getPriceByCityAndDate(city = city, date = date)
            dao.insertGasolinePriceList(apiGasolinePriceList.toGasolinePriceEntitiesList())
            dao.getGasolinePriceByCityAndDate(city = city, date = date)
        } catch (e: HttpException) {
            emit(Resource.Error(
                data = localGasolinePrice,
                message = "Something went wrong. Please try again later." //todo string resource
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                data = localGasolinePrice,
                message = "Please check your internet connection." //todo string resource
            ))
        } catch (e: NullPointerException) {
            emit(Resource.Error(
                data = localGasolinePrice,
                message = "Data error" //todo string resource
            ))
        }

        val newLocalGasolinePrice: GasolinePrice? = dao.getGasolinePriceByCityAndDate(city = city, date = date)?.toGasolinePrice()
        emit(Resource.Success(newLocalGasolinePrice))
    }

    override fun getCities(): Flow<Resource<List<String>>> {
        TODO("Not yet implemented")
    }


}