package com.example.crud;
import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Firebase
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public static class Student {
        private String id;
        private String name;
        private String email;

        public Student() {
            // Default constructor required for calls to DataSnapshot.getValue(Student.class)
        }

        public Student(String id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        // Getters and setters...
        public static class FirebaseDatabaseHelper {
            private FirebaseDatabase mDatabase;
            private DatabaseReference mReferenceStudents;

            public FirebaseDatabaseHelper() {
                mDatabase = FirebaseDatabase.getInstance();
                mReferenceStudents = mDatabase.getReference("students");
            }

            public void addStudent(Student student) {
                String id = mReferenceStudents.push().getKey();
                student.setId(id);
                mReferenceStudents.child(id).setValue(student);
            }

            public void updateStudent(String id, Student student) {
                mReferenceStudents.child(id).setValue(student);
            }

            public void deleteStudent(String id) {
                mReferenceStudents.child(id).removeValue();
            }

            public DatabaseReference getReference() {
                return mReferenceStudents;
            }

            private void setId(String id) {
            }
        }
    }
}



