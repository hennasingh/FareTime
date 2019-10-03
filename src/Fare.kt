const val FILEPATH = "src/customers.txt"

fun main() {

    DataRepo.customerStore(FILEPATH).map { println("${it.user_id}, ${it.name}") }



}







