import data.DataRepo
import model.Customer
import org.json.JSONObject
import java.io.File

const val FILEPATH = "src/assets/customers.txt"

fun main() {

    val dataRepo = DataRepo()

    val customerList = dataRepo.customerStore(FILEPATH)

    val inputStream = File(FILEPATH).inputStream()

    inputStream.bufferedReader().useLines { lines-> lines.forEach {

        println{it}
        jsonStringToObject(it)
    }

    }
}

private fun jsonStringToObject(jsonString: String) {

    val json = JSONObject(jsonString)

    println{json.getString("latitude")}

}





