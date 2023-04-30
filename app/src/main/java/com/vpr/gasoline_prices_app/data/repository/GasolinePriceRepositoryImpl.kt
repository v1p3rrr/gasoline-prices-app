package com.vpr.gasoline_prices_app.data.repository

import com.vpr.gasoline_prices_app.data.local.GasolinePriceDao
import com.vpr.gasoline_prices_app.data.local.entity.CityEntity
import com.vpr.gasoline_prices_app.data.remote.GasolinePricesService
import com.vpr.gasoline_prices_app.data.remote.dto.CityWithPriceListDTO
import com.vpr.gasoline_prices_app.domain.model.GasolinePrice
import com.vpr.gasoline_prices_app.domain.repository.GasolinePriceRepository
import com.vpr.gasoline_prices_app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
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
    override suspend fun getGasolinePriceByCityAndDate(city: String, date: String): Flow<Resource<List<GasolinePrice>>> = flow {
        emit(Resource.Loading())

        val localGasolinePrice : List<GasolinePrice> = dao.getGasolinePriceByCityAndDate(city = city, date = date).map { it.toGasolinePrice() }

        emit(Resource.Loading(localGasolinePrice))

        try {
            val apiGasolinePriceList: CityWithPriceListDTO = api.getPriceByCityAndDate(city = city, date = date)
            dao.deleteGasolinePriceByCityAndDate(city = city, date = date)
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

        val newLocalGasolinePrice: List<GasolinePrice> = dao.getGasolinePriceByCityAndDate(city = city, date = date).map { it.toGasolinePrice() }
        emit(Resource.Success(newLocalGasolinePrice))
    }.catch {e ->
        emit(Resource.Error("Something went wrong"))
        e.printStackTrace()
    }


override suspend fun getGasolinePricesListByCityAndDateRange(city: String, dateStart: String, dateEnd: String): Flow<Resource<List<GasolinePrice>>> = flow {
        emit(Resource.Loading())

        val localGasolinePrice : List<GasolinePrice> = dao.getGasolinePricesByCityAndDateRange(city = city, dateStart = dateStart, dateEnd = dateEnd).map{ it -> it.toGasolinePrice()}

        emit(Resource.Loading(localGasolinePrice))

        try {
            val apiGasolinePricesList: CityWithPriceListDTO = api.getPricesListByCityAndDateRange(city = city, dateStart = dateStart, dateEnd = dateEnd)
            dao.deleteGasolinePricesByCityAndDateRange(city = city, dateStart = dateStart, dateEnd = dateEnd)
            dao.insertGasolinePriceList(apiGasolinePricesList.toGasolinePriceEntitiesList())
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

        val newLocalGasolinePrice : List<GasolinePrice> = dao.getGasolinePricesByCityAndDateRange(city = city, dateStart = dateStart, dateEnd = dateEnd).map { it -> it.toGasolinePrice()}
        emit(Resource.Success(newLocalGasolinePrice))
    }.catch {e ->
        emit(Resource.Error("Something went wrong"))
        e.printStackTrace()
    }


    override suspend fun getCities(): Flow<Resource<List<String>>>  = flow {
        emit(Resource.Loading())

        val localCities : List<String> = dao.getAllCities().map { it -> it.name }

        emit(Resource.Loading(localCities))

        try {
            val apiCities: List<String> = api.getCitiesList()
            dao.deleteAllCities()
            dao.insertCities(apiCities.map { it -> CityEntity(name = it) })
            dao.getAllCities()
        } catch (e: HttpException) {
            emit(Resource.Error(
                data = localCities,
                message = "Something went wrong. Please try again later." //todo string resource
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                data = localCities,
                message = "Please check your internet connection." //todo string resource
            ))
        } catch (e: NullPointerException) {
            emit(Resource.Error(
                data = localCities,
                message = "Data error" //todo string resource
            ))
        }

        val newLocalCities: List<String> = dao.getAllCities().map { it -> it.name }
        emit(Resource.Success(newLocalCities))
    }.catch {e ->
        emit(Resource.Error("Something went wrong"))
        e.printStackTrace()
    }


}