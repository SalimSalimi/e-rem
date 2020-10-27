package dz.salim.salimi.e_rem.data.models.content.exercise

data class Question(
    var title: String = "",
    var points: Int = 0,
    var options: List<Option> = ArrayList()
)