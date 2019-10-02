package data

import model.Customer
import org.json.JSONObject
import java.io.File

class DataRepo {

    companion object {

        private const val LONGITUDE = "longitude"
        private const val USER_ID = "user_id"
        private const val LATITUDE = "latitude"
        private const val NAME = "name"
    }

   private  val customerList = ArrayList<Customer>()

    fun customerStore(fileName: String) : ArrayList<Customer>{

        val inputStream = File(fileName).inputStream()

        inputStream.bufferedReader().useLines { lines-> lines.forEach {

            println{it}
            jsonStringToObject(it)
            }

        }
        return customerList
    }

    private fun jsonStringToObject(jsonString: String) {

        val json = JSONObject(jsonString)

        val latitude = json.getString(LATITUDE)
        val userId = json.getInt(USER_ID)
        val name = json.getString(NAME)
        val longitude = json.getString(LONGITUDE)

        customerList.add(Customer(longitude,latitude,userId,name))

    }
}