import com.example.grocerease.GrocerEaseApplication
import com.example.grocerease.model.Basket
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post

@SpringBootTest(classes = [GrocerEaseApplication::class])
@ActiveProfiles("test")
@AutoConfigureMockMvc
class BasketControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) {
    private val basePath: String = "/api/v1/baskets"

    @Test
    fun testBasketCreationAlreadyExists() {
        val mockBasket = Basket(id = 1)
        mockMvc.post(basePath) {
            contentType = MediaType.APPLICATION_JSON
            content = asJson(mockBasket)
        }.andExpect {
            status { isConflict() }
        }
    }

    @Test
    @DirtiesContext
    fun testBasketCreationPasses() {
        val mockBasket = Basket(id = 3)
        mockMvc.post(basePath) {
            contentType = MediaType.APPLICATION_JSON
            content = asJson(mockBasket)
        }.andExpect {
            status { isConflict() }
        }
    }

    @Test
    fun testBasketGetHappyPath() {
        val basketId = 1L

        mockMvc.get("$basePath/$basketId")
            .andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                }
            }
    }

    @Test
    fun testBasketGetNotFoundPath() {
        val notFoundBasketId = 100L

        mockMvc.get("$basePath/$notFoundBasketId")
            .andExpect {
                status { isNotFound() }
            }
    }

    @Test
    fun testBasketUpdateHappyPath() {
        val mockBasket = Basket(id = 1)
        mockMvc.patch(basePath) {
            contentType = MediaType.APPLICATION_JSON
            content = asJson(mockBasket)
        }.andExpect {
            status { isAccepted() }
        }
    }

    private fun asJson(basket: Basket): String = objectMapper.writeValueAsString(basket)
}
