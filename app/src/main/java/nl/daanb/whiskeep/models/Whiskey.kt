package nl.daanb.whiskeep.models

data class Whiskey (
    var id: Long,
    var name: String,
    var type: String,
    var abv: Float,
    var price: Float,
    var notes: List<String>
)