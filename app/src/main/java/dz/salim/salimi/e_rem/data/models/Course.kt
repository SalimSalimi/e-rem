package dz.salim.salimi.e_rem.data.models

data class Course (
    override var id: String = "",
    override var title: String = "",
    override var description: String = "",
    override var createdDate: String = "",
    override var updatedDate: String = "",
    val content: String = "") : Content()