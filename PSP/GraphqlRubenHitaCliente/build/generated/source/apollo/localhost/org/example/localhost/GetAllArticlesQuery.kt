//
// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL version '3.7.3'.
//
package org.example.localhost

import com.apollographql.apollo3.annotations.ApolloAdaptableWith
import com.apollographql.apollo3.api.Adapter
import com.apollographql.apollo3.api.CompiledField
import com.apollographql.apollo3.api.CustomScalarAdapters
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.api.json.JsonWriter
import com.apollographql.apollo3.api.obj
import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import org.example.localhost.adapter.GetAllArticlesQuery_ResponseAdapter
import org.example.localhost.selections.GetAllArticlesQuerySelections

public class GetAllArticlesQuery() : Query<GetAllArticlesQuery.Data> {
  public override fun equals(other: Any?): Boolean = other != null && other::class == this::class

  public override fun hashCode(): Int = this::class.hashCode()

  public override fun id(): String = OPERATION_ID

  public override fun document(): String = OPERATION_DOCUMENT

  public override fun name(): String = OPERATION_NAME

  public override fun serializeVariables(writer: JsonWriter,
      customScalarAdapters: CustomScalarAdapters): Unit {
    // This operation doesn't have any variable
  }

  public override fun adapter(): Adapter<Data> = GetAllArticlesQuery_ResponseAdapter.Data.obj()

  public override fun rootField(): CompiledField = CompiledField.Builder(
    name = "data",
    type = org.example.localhost.type.Query.type
  )
  .selections(selections = GetAllArticlesQuerySelections.__root)
  .build()

  @ApolloAdaptableWith(GetAllArticlesQuery_ResponseAdapter.Data::class)
  public data class Data(
    public val getAllArticles: List<GetAllArticle?>?,
  ) : Query.Data

  public data class GetAllArticle(
    public val id: String?,
    public val nombre: String,
    public val descripcion: String,
  )

  public companion object {
    public const val OPERATION_ID: String =
        "4b2a42d8a3bc99914c3bebcfeea80b7b60d2551689d7fea481dd9d9fa62844eb"

    /**
     * The minimized GraphQL document being sent to the server to save a few bytes.
     * The un-minimized version is:
     *
     * query GetAllArticles {
     *   getAllArticles {
     *     id
     *     nombre
     *     descripcion
     *   }
     * }
     */
    public val OPERATION_DOCUMENT: String
      get() = "query GetAllArticles { getAllArticles { id nombre descripcion } }"

    public const val OPERATION_NAME: String = "GetAllArticles"
  }
}