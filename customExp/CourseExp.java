package customExp;

import source.Course;

public class CourseExp extends Exception{
    private Course course;

    public CourseExp(String msg, Course c){
        super(msg);
        if(course == null){course = new Course("NO COURSE", -1, false, false);}
        else{course = c;}
    }

    @Override
    public String toString(){
        return "Course " + course.getName() + " raises error with reason: " + super.getMessage();
    }
}
