package ba.unsa.etf.rma.rma2022v.data

import com.google.gson.annotations.SerializedName

data class GetCreditsResponse(
    @SerializedName("cast") val actors: List<String>
)
