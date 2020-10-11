package dz.salim.salimi.e_rem.data.repositories

import dz.salim.salimi.e_rem.data.models.Course

class CourseRepository {

    fun getAllCourses(): List<Course> {
        val courseList = ArrayList<Course>()
        courseList.add(
            Course("", "Hello World", "HEre we go", "06.06.06. 15:30",
                "06.06.06. 15:30", "Nothing")
        )
        courseList.add(
            Course("", "Kebab", "Let's eat a kebab", "06.06.08. 15:30",
                "06.06.06. 15:30", "Nothing")
        )
        courseList.add(
            Course("", "Why should i write ?", "Idk what to write", "06.06.07. 15:30",
                "06.06.06. 15:30", "Nothing")
        )
        return courseList
    }

}