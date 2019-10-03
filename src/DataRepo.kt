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
    private const val DISTANCE = 100.0 //in km


    fun customerStore(fileName: String): List<Customer> {

        return File(fileName).readLines()
            .map { line -> parseJson(line) }
            .filter { customer -> calculateDistance(customer) <= DISTANCE }
            .sortedBy { customer -> customer.user_id }
    }

    private fun parseJson(jsonString: String): Customer {

        val json = JSONObject(jsonString)

        val latitude = json.getString(LATITUDE)
        val userId = json.getInt(USER_ID)
        val name = json.getString(NAME)
        val longitude = json.getString(LONGITUDE)

        return Customer(longitude, latitude, userId, name)

    }

    /*
     * Taken from https://rosettacode.org/wiki/Haversine_formula#Kotlin
     */
    private fun calculateDistance(customer: Customer): Double {

        val latRadians = toRadians(customer.latitude.toDouble())
        val intercommLatRadians = toRadians(INTERCOMM_LATITUDE)

        val latDifference = intercommLatRadians - latRadians
        val longDifference = toRadians(INTERCOMM_LONGITUDE - customer.longitude.toDouble())

        return 2 * EARTH_RADIUS * asin(
            sqrt(
                pow(sin(latDifference / 2), 2.0) + pow(sin(longDifference / 2), 2.0)
                        * cos(latRadians) * cos(intercommLatRadians)
            )
        )

    }
}