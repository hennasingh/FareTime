import data.DataRepo


const val FILEPATH = "src/assets/customers.txt"

fun main() {

    DataRepo.customerStore(FILEPATH).map { println("${it.user_id}, ${it.name}") }



}







