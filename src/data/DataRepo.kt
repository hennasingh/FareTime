package data

import model.Customer
import org.json.JSONObject
import java.io.File
import java.lang.Math.*

object DataRepo {

        private const val LONGITUDE = "longitude"
        private const val USER_ID = "user_id"
        private const val LATITUDE = "latitude"
        private const val NAME = "name"
    private const val INTERCOMM_LONGITUDE = -6.257664
    private const val INTERCOMM_LATITUDE = 53.339428
    private const val EARTH_RADIUS = 6372.8 //in km
    private const val DISTANCE = 100 //in km


    private val customerList = ArrayList<Customer>()


    private fun customerStore(fileName: String): HashMap<Int, String> {

        val inputStream = File(fileName).inputStream()

        inputStream.bufferedReader().useLines { lines-> lines.forEach {

            jsonStringToObject(it)
            }

        }
        return calculateDistanceAndReturnClosest(customerList)
    }

    private fun jsonStringToObject(jsonString: String) {

        val json = JSONObject(jsonString)

        val latitude = json.getString(LATITUDE)
        val userId = json.getInt(USER_ID)
        val name = json.getString(NAME)
        val longitude = json.getString(LONGITUDE)

        customerList.add(Customer(longitude,latitude,userId,name))

    }

    private fun calculateDistanceAndReturnClosest(list: ArrayList<Customer>): HashMap<Int, String> {

        val invitedCustomers = HashMap<Int, String>()

        for (customer in list) {
            val latRadians = toRadians(customer.latitude.toDouble())
            val intercommLatRadians = toRadians(INTERCOMM_LATITUDE)

            val latDifference = intercommLatRadians - latRadians
            val longDifference = toRadians(INTERCOMM_LONGITUDE - customer.longitude.toDouble())

            val distanceKm = 2 * EARTH_RADIUS * asin(
                sqrt(
                    pow(sin(latDifference / 2), 2.0) + pow(sin(longDifference / 2), 2.0)
                            * cos(latRadians) * cos(intercommLatRadians)
                )
            )

            if (distanceKm <= DISTANCE) invitedCustomers[customer.user_id] = customer.name

        }

        return invitedCustomers

    }
}