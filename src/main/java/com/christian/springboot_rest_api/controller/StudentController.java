package com.christian.springboot_rest_api.controller;

import com.christian.springboot_rest_api.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(
                1,
                "Christian",
                "Cruise"
        );
//        return new ResponseEntity<>(student,HttpStatus.OK);
//        return ResponseEntity.ok(student);
        return ResponseEntity.ok()
                .header("Custom-header","christian")
                .body(student);
    }

    // http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();

        students.add(new Student(1,"Chris","Jackson"));
        students.addLast(new Student(2,"John","Vispuch"));

        return ResponseEntity.ok(students);
    }

    //Spring boot rest api path variable
    // studentId - URI template variable

    @GetMapping("student/{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(
            @PathVariable("id") int studentId,
            @PathVariable("first-name") String firstName,
            @PathVariable("last-name") String lastName) {

        return ResponseEntity.ok(new Student(studentId,firstName,lastName));
    }

    //SpringBoot Rest API with RequestParam

    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable (@RequestParam int id,
                                           @RequestParam String firstName,
                                           @RequestParam String lastName) {
        return ResponseEntity.ok(new Student(id,firstName,lastName));
    }

    //Springboot Rest API that handles HTTP POST requests
    @PostMapping("create")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student);
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }


    //Spring boot REST API that handles http PUT requests
    @PutMapping("{id}/update")
    // @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Student> updateStudent(@RequestBody Student student , @PathVariable("id") int studentId) {
        System.out.println(student);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @DeleteMapping("{id}/delete")
//   // @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){
        return new ResponseEntity<>("Student deleted successfully",HttpStatus.OK);
    }
}
