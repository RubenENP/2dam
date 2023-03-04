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
import com.apollographql.apollo3.api.Mutation
import com.apollographql.apollo3.api.json.JsonWriter
import com.apollographql.apollo3.api.obj
import kotlin.String
import kotlin.Unit
import org.example.localhost.adapter.UpdateNewspaperMutation_ResponseAdapter
import org.example.localhost.adapter.UpdateNewspaperMutation_VariablesAdapter
import org.example.localhost.selections.UpdateNewspaperMutationSelections

public data class UpdateNewspaperMutation(
  public val id: String,
  public val nameNewspaper: String,
) : Mutation<UpdateNewspaperMutation.Data> {
  public override fun id(): String = OPERATION_ID

  public override fun document(): String = OPERATION_DOCUMENT

  public override fun name(): String = OPERATION_NAME

  public override fun serializeVariables(writer: JsonWriter,
      customScalarAdapters: CustomScalarAdapters): Unit {
    UpdateNewspaperMutation_VariablesAdapter.toJson(writer, customScalarAdapters, this)
  }

  public override fun adapter(): Adapter<Data> = UpdateNewspaperMutation_ResponseAdapter.Data.obj()

  public override fun rootField(): CompiledField = CompiledField.Builder(
    name = "data",
    type = org.example.localhost.type.Mutation.type
  )
  .selections(selections = UpdateNewspaperMutationSelections.__root)
  .build()

  @ApolloAdaptableWith(UpdateNewspaperMutation_ResponseAdapter.Data::class)
  public data class Data(
    public val updateNewspaper: UpdateNewspaper?,
  ) : Mutation.Data

  public data class UpdateNewspaper(
    public val id: String?,
    public val nameNewspaper: String,
  )

  public companion object {
    public const val OPERATION_ID: String =
        "663e9e67ad1d4fa5b5a32e9a0eebab95390b0b551dd5cc462e55413c37022c90"

    /**
     * The minimized GraphQL document being sent to the server to save a few bytes.
     * The un-minimized version is:
     *
     * mutation UpdateNewspaper($id: ID!, $nameNewspaper: String!) {
     *   updateNewspaper(id: $id, nameNewspaper: $nameNewspaper) {
     *     id
     *     nameNewspaper
     *   }
     * }
     */
    public val OPERATION_DOCUMENT: String
      get() =
          "mutation UpdateNewspaper(${'$'}id: ID!, ${'$'}nameNewspaper: String!) { updateNewspaper(id: ${'$'}id, nameNewspaper: ${'$'}nameNewspaper) { id nameNewspaper } }"

    public const val OPERATION_NAME: String = "UpdateNewspaper"
  }
}
