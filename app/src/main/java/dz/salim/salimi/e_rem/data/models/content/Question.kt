package dz.salim.salimi.e_rem.data.models.content

class Question(
    var title: String = "",
    var points: Int = 0,
    var options: HashMap<String, Boolean> = HashMap()
)