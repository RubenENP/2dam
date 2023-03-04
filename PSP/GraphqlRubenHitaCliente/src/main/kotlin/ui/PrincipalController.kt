package ui

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.TableColumn
import kotlinx.coroutines.DelicateCoroutinesApi
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.launch
import modelo.Newspaper
import com.apollographql.apollo3.ApolloClient
import javafx.scene.control.TextField
import modelo.Article
import org.example.localhost.GetAllArticlesQuery
import org.example.localhost.GetAllNewspapersQuery
import org.example.localhost.UpdateNewspaperMutation
import java.net.URL
import java.time.LocalDate
import java.util.*

class PrincipalController : Initializable{
    lateinit var nombreNewsField: TextField
    lateinit var descripcionColumnArticle: TableColumn<Article, String>
    lateinit var nameColumnArticle: TableColumn<Article, String>
    lateinit var articleTable: TableView<Article>
    lateinit var newspaperReleaseDateolumn: TableColumn<Newspaper, String>
    lateinit var newspaperNameColumn: TableColumn<Newspaper, String>
    lateinit var newspaperTable: TableView<Newspaper>

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        newspaperNameColumn.cellValueFactory = PropertyValueFactory("nameNewspaper")
        newspaperReleaseDateolumn.cellValueFactory = PropertyValueFactory("releaseDate")
    }

    private fun apolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("http://localhost:8080/graphql")
            .build()
    }
    @OptIn(DelicateCoroutinesApi::class)
    @FXML
    fun reload() {
        val apolloClient = apolloClient()
        newspaperTable.items.clear()
        GlobalScope.launch(Dispatchers.JavaFx) {
            apolloClient.query(GetAllNewspapersQuery()).execute().data?.let { data ->
                data.getAllNewspapers?.map { Newspaper(it?.id?.toInt(),it?.nameNewspaper, LocalDate.parse(it?.releaseDate)) }
                    ?.forEach { newspaperTable.items.add(it) }
            }
        }
        GlobalScope.launch(Dispatchers.JavaFx) {
            articleTable.items.clear()
            apolloClient.query(GetAllArticlesQuery()).execute().data?.let { data ->
                data.getAllArticles?.map { Article(it?.id?.toInt(),it?.nombre, it?.descripcion) }
                    ?.forEach { articleTable.items.add(it) }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    @FXML
    fun updateNewspaper() {
        val apolloClient = apolloClient()
        GlobalScope.launch(Dispatchers.JavaFx) {
            apolloClient.mutation(
                UpdateNewspaperMutation(
                    newspaperTable.selectionModel.selectedItem.id.toString(), nombreNewsField.text
                )
            )
                .execute()
        }
    }
}