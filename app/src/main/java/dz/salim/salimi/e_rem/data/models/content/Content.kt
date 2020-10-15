package dz.salim.salimi.e_rem.data.models.content

import dz.salim.salimi.e_rem.data.models.Entity

abstract class Content : Entity() {
    open var id: String = ""
    open var title: String = ""
    open var description: String = ""
    open var createdDate: String = ""
    open var updatedDate: String = ""
}