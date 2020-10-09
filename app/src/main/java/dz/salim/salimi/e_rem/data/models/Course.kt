package dz.salim.salimi.e_rem.data.models

data class Course (
    override var id: Int = 0,
    override var title: String = "",
    override var description: String = "",
    override var publishedDate: String = "",
    override var updatedDate: String = "",
    val content: String = "") : Content()