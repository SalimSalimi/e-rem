package dz.salim.salimi.e_rem.data.models

data class Course (override val id: Int,
              override val title: String,
              override val description: String,
              override val published_date: String,
              override val updated_date: String): Content(id, title, description, published_date, updated_date)