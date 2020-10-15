package dz.salim.salimi.e_rem.data.models.content

data class Course (
    override var id: String = "",
    override var title: String = "",
    override var description: String = "",
    override var createdDate: String = "",
    override var updatedDate: String = "",
    var content: String = "") : Content()