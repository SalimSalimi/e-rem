package dz.salim.salimi.e_rem.data.models

abstract class Content (
    open val id: Int,
    open val title: String,
    open val description: String,
    open val published_date: String,
    open val updated_date: String
)