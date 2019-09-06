package pe.edu.upc.consumingapi.models


import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("artist")
    val artist: String,
    @SerializedName("attack")
    val attack: Int,
    @SerializedName("cardId")
    val cardId: String,
    @SerializedName("cardSet")
    val cardSet: String,
    @SerializedName("collectible")
    val collectible: Boolean,
    @SerializedName("cost")
    val cost: Int,
    @SerializedName("dbfId")
    val dbfId: String,
    @SerializedName("elite")
    val elite: Boolean,
    @SerializedName("faction")
    val faction: String,
    @SerializedName("flavor")
    val flavor: String,
    @SerializedName("health")
    val health: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("imgGold")
    val imgGold: String,
    @SerializedName("locale")
    val locale: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("playerClass")
    val playerClass: String,
    @SerializedName("race")
    val race: String,
    @SerializedName("rarity")
    val rarity: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("type")
    val type: String
)