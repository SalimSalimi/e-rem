package dz.salim.salimi.e_rem.ui.exercise.question

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dz.salim.salimi.e_rem.data.models.content.Exercise
import dz.salim.salimi.e_rem.data.models.content.Question
import dz.salim.salimi.e_rem.data.repositories.AuthRepository
import dz.salim.salimi.e_rem.data.repositories.ExerciseRepository
import dz.salim.salimi.e_rem.utils.getCurrentTime

class QuestionFormViewModel : ViewModel() {
    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    val pointsString = MutableLiveData<String>()

    private val _hashOptions = MutableLiveData<HashMap<String, Boolean>>()

    init {
        _question.value = Question()
        pointsString.value = "0"
        _hashOptions.value = HashMap()
    }

    fun onAddOptionsClick() {
        _question.value!!.points = pointsString.value?.toString()?.toInt() ?: 0
        Log.d("ViewModel", "points= ${_question.value!!.points}")
    }

    fun addOptions(option: String, isCorrect: Boolean) {
        _hashOptions.value!![option] = isCorrect
    }

    fun onAddQuestionClicked() {
        val questionsList = ArrayList<Question>()
        _question.value!!.options = _hashOptions.value!!
        questionsList.add(_question.value!!)
        val exercise = Exercise("", "Hello", "World", getCurrentTime(),
            "", AuthRepository.LOGGED_IN_USER_UID, questionsList)
        ExerciseRepository.addExercise(exercise)
    }
}