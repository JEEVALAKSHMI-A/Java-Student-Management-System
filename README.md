# Module 02 – Student Management System

Console-based Java project for the Codomax Java Programming internship.

## Features
- Add, view, search, update and delete student records
- Uses `ArrayList` (List), `HashSet` (Set), `HashMap` (Map), and `Iterator`
- Demonstrates methods, parameters, method overloading and scope
- Handles invalid input, duplicate IDs and missing students with exceptions
- Saves and loads records using `students.txt`

## Requirements
JDK 8 or later.

## Compile and run
Open a terminal in this folder:

```bash
javac -d out src/StudentManagementSystem.java
java -cp out StudentManagementSystem
```

## Test
1. Add ID `101`, name `Anu`, course `Java`, mark `85`.
2. View all students and search for ID `101`.
3. Try adding ID `101` again; duplicate ID should be rejected.
4. Try a mark above 100; validation should reject it.
5. Exit and run again; the saved record should load.

## Concepts mapped to Module 02
| Objective | Code |
|---|---|
| Methods and parameters | addStudent, searchStudent, validateMark |
| Method overloading | createStudent overloads |
| Scope | class fields and local variables |
| List | ArrayList |
| Set | HashSet |
| Map | HashMap |
| Iterator | viewStudents |
| Exceptions | try, catch, throw, throws |
| File handling | BufferedReader, BufferedWriter, try-with-resources |

This is an educational console project, not production software.
