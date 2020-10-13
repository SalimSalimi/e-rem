package dz.salim.salimi.e_rem.data.models.user

abstract class User () {
    open var id: String = ""
    open var firstName: String = ""
    open var lastName: String = ""
    open var location: String = ""
    open var schoolName: String = ""
    open var profileImageUrl: String = ""
}