package dz.salim.salimi.e_rem.data.models.user

class Teacher (
    override var id: String = "",
    override var firstName: String = "",
    override var lastName: String = "",
    override var location: String = "",
    override var schoolName: String = "",
    override var profileImageUrl: String = "",
    var bio: String = ""
): User()