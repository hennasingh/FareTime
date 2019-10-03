import data.DataRepo
import model.Customer
import org.json.JSONObject
import java.io.File

const val FILEPATH = "src/assets/customers.txt"

fun main() {

    val customerList = DataRepo.customerStore(FILEPATH)



}







