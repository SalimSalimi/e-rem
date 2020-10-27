package dz.salim.salimi.e_rem.ui.exercise.question.OptionsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import dz.salim.salimi.e_rem.data.models.content.exercise.Option
import dz.salim.salimi.e_rem.databinding.OptionsQuestionItemBinding

class OptionListAdapter (private val optionsList: LiveData<ArrayList<Option>>): RecyclerView.Adapter<OptionListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = OptionsQuestionItemBinding.inflate(inflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int =
            optionsList.value?.size ?: 0

    inner class ViewHolder(private val binding: OptionsQuestionItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val option = optionsList.value?.get(position)
            binding.option = option

            onChangeChecked(position)
            onDeleteBtnClicked(position)
        }

        private fun onChangeChecked(position: Int) {
            binding.checkBox.setOnCheckedChangeListener { _, b ->
                optionsList.value!![position].isCorrect = b
            }
        }

        private fun onDeleteBtnClicked(position: Int) {
            binding.imageButton.setOnClickListener {
                optionsList.value!!.removeAt(position)
                notifyItemChanged(position)
            }
        }
    }

}