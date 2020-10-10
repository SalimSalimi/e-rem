package dz.salim.salimi.e_rem.data.models

abstract class Content () {
    open var id: Int = 0
    open var title: String = ""
    open var description: String = ""
    open var createdDate: String = ""
    open var updatedDate: String = ""
}