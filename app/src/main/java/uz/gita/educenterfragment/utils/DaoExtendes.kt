package uz.gita.educenterfragment.utils

import uz.gita.educenterfragment.database.entiteis.CourseEntity
import uz.gita.educenterfragment.database.entiteis.GroupEntity
import uz.gita.educenterfragment.database.entiteis.StudentEntity
import uz.gita.educenterfragment.models.CourseData
import uz.gita.educenterfragment.models.GroupData
import uz.gita.educenterfragment.models.StudentData


fun CourseEntity.toCourseData(): CourseData {
    return CourseData(id,image,name)
}
fun GroupEntity.toGroupData(): GroupData {
    return GroupData(id,nameGroup,courseId)
}
fun StudentEntity.toStudentData(): StudentData {
    return StudentData(id, name,phone,image,groupId)
}
fun CourseData.toCourseEntity():CourseEntity{
    return CourseEntity(id,image,name)
}
fun GroupData.toGroupEntity():GroupEntity{
    return GroupEntity(id,name,courseId)
}
fun StudentData.toStudentEntity():StudentEntity{
    return StudentEntity(id,name,phone,image,group_id)
}
