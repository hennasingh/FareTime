package data

import model.Customer
import org.json.JSONObject
import java.io.File

object DataRepo {

        private const val LONGITUDE = "longitude"
        private const val USER_ID = "user_id"
        private const val LATITUDE = "latitude"
        private const val NAME = "name"
    private const val INTERCOMM_LONGITUDE = -6.257664
    private const val INTERCOMM_LATITUDE = 53.339428

    private val customerList = ArrayList<Customer>()


    private fun customerStore(fileName: String) {

        val inputStream = File(fileName).inputStream()

        inputStream.bufferedReader().useLines { lines-> lines.forEach {

            jsonStringToObject(it)
            }

        }
        calculateDistanceAndReturnClosest(customerList)
    }

    private fun jsonStringToObject(jsonString: String) {

        val json = JSONObject(jsonString)

        val latitude = json.getString(LATITUDE)
        val userId = json.getInt(USER_ID)
        val name = json.getString(NAME)
        val longitude = json.getString(LONGITUDE)

        customerList.add(Customer(longitude,latitude,userId,name))

    }

    private fun calculateDistanceAndReturnClosest(customerList: ArrayList<Customer>): ArrayList<Customer> {
        

    }
}