package dz.salim.salimi.e_rem.ui.exercise.question

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dz.salim.salimi.e_rem.data.models.content.exercise.Exercise
import dz.salim.salimi.e_rem.data.models.content.exercise.Option
import dz.salim.salimi.e_rem.data.models.content.exercise.Question
import dz.salim.salimi.e_rem.data.repositories.AuthRepository
import dz.salim.salimi.e_rem.data.repositories.ExerciseRepository
import dz.salim.salimi.e_rem.utils.getCurrentTime

class QuestionFormViewModel : ViewModel() {
    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    val pointsString = MutableLiveData<String>()

    private val _listOptions = MutableLiveData<ArrayList<Option>>()
    val listOptions: LiveData<ArrayList<Option>>
        get() = _listOptions

    init {
        _question.value = Question()
        pointsString.value = "0"
        _listOptions.value = ArrayList()
    }

    fun onAddOptionsClick() {
        _question.value!!.points = pointsString.value?.toString()?.toInt() ?: 0
        Log.d("ViewModel", "points= ${_question.value!!.points}")
    }

    fun addOptions(option: String, isCorrect: Boolean) {
        val option = Option(option, isCorrect)
        _listOptions.value!!.add(option)
    }

    fun onAddQuestionClicked() {
        val questionsList = ArrayList<Question>()
        _question.value!!.options = _listOptions.value!!
        questionsList.add(_question.value!!)
        val exercise = Exercise("", "Hello", "World", getCurrentTime(),
            "", AuthRepository.LOGGED_IN_USER_UID, questionsList)
        ExerciseRepository.addExercise(exercise)
    }
}