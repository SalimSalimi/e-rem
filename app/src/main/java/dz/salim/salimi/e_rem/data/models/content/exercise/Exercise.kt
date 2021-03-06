package dz.salim.salimi.e_rem.data.models.content.exercise

import dz.salim.salimi.e_rem.data.models.content.Content

data class Exercise (
    override var id: String = "",
    override var title: String = "",
    override var description: String = "",
    override var createdDate: String = "",
    override var updatedDate: String = "",
    override var creatorId: String = "",
    var questionsList: List<Question> = ArrayList(),
    var maxPoints: Int = 0
) : Content()