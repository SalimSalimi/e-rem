package dz.salim.salimi.e_rem.ui.exercise.question

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dz.salim.salimi.e_rem.data.models.content.Question

class QuestionFormViewModel : ViewModel() {
    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    val pointsString = MutableLiveData<String>()


    init {
        _question.value = Question()
        pointsString.value = "0"
    }

    fun onAddOptionsClick() {
        _question.value!!.points = pointsString.value.toString().toInt() ?: 0
        Log.d("ViewModel", "points= ${_question.value!!.points}")
    }
}