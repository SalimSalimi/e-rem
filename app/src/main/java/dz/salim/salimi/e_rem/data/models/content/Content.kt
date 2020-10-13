package dz.salim.salimi.e_rem.data.models.content

abstract class Content () {
    open var id: String = ""
    open var title: String = ""
    open var description: String = ""
    open var createdDate: String = ""
    open var updatedDate: String = ""
}