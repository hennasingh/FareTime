package test.data

import data.DataRepo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import java.io.File

class DataRepoTest {

    companion object {
        const val FILEPATH_INPUT = "src/assets/customers.txt"
        const val FILEPATH_OUTPUT = "src/test/assets/output.txt"
    }

    private lateinit var input: List<String>

    @Before
    fun getInput() {
        input = File(FILEPATH_INPUT).readLines()
    }

    @Test
    fun inputFileNotEmpty() {
        assertNotNull(input)
    }

    @Test
    fun customerStoreTest() {
        val actual = DataRepo.customerStore(FILEPATH_INPUT)
            .map { "${it.user_id}, ${it.name}" }

        val expected = File(FILEPATH_OUTPUT).readLines()

        assertEquals(expected, actual)
    }
}