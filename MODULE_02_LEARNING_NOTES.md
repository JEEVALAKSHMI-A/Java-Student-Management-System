# Module 02 – Learning Notes: Methods, Collections & Exceptions

## 1. Methods
A method is a reusable block of code. It can receive parameters and return a value.
```java
static int add(int a, int b) { return a + b; }
```

## 2. Method overloading
Multiple methods can share a name if their parameter lists differ in number, type, or order. Return type alone is not enough.

## 3. Parameter passing and scope
Java uses pass-by-value. Local variables exist inside their method/block; instance variables belong to an object; static variables belong to the class.

## 4. Collections Framework
- List: ordered; allows duplicates. Examples: ArrayList, LinkedList.
- Set: unique elements. Examples: HashSet, TreeSet.
- Map: key-value pairs with unique keys. Examples: HashMap, TreeMap.
- Iterator: traverses a collection with hasNext() and next().

## 5. Exception handling
- try: code that may throw an exception.
- catch: handles an exception.
- finally: cleanup code that normally runs after try/catch.
- throw: explicitly throws an exception.
- throws: declares exceptions a method may pass to its caller.

## 6. File handling
BufferedReader and FileReader read text files. BufferedWriter and FileWriter write text files. Try-with-resources closes resources automatically.

## 7. Mini project
The Student Management System demonstrates CRUD (create, read, update, delete), collections, exception handling and file persistence.
