package dz.salim.salimi.e_rem.data.repositories

import androidx.lifecycle.MutableLiveData
import dz.salim.salimi.e_rem.data.models.content.exercise.Exercise
import dz.salim.salimi.e_rem.data.remote.DataFirebase
import dz.salim.salimi.e_rem.utils.CREATOR_ID
import dz.salim.salimi.e_rem.utils.EXERCISE_REF

object ExerciseRepository {

    fun addExercise(exercise: Exercise) {
        DataFirebase.addEntity(exercise, EXERCISE_REF)
    }

    fun updateExercise(exercise: Exercise) {
        DataFirebase.updateEntity(exercise, EXERCISE_REF)
    }

    fun deleteExercise(exercise: Exercise) {
        DataFirebase.deleteEntity(exercise, EXERCISE_REF)
    }

    fun getAllExercisesByCreatorId(data: MutableLiveData<List<Exercise>>, creatorId: String) {
        DataFirebase.getAllByFirstChild(data, EXERCISE_REF, CREATOR_ID, creatorId)
    }
}