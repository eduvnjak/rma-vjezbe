package ba.unsa.etf.rma.rma2022v.data


import com.google.gson.annotations.SerializedName

data class Actor(
    @SerializedName("name") var name: String
) {
}