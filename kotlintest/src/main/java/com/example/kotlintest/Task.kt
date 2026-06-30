package com.example.kotlintest

fun main() {
    println(analyzeIntList(listOf(0, 4, 5, 59, 97, -20, 100, 5, 67, -2, -100)))
    println("--------------------------------")
    println(passwordVerification("77777777"))
}

fun analyzeIntList(list : List<Int>): String {
    var min = list[0]
    var max = list[0]
    var sum = 0
    var numberOfEven = 0
    var numberOfOdd = 0
    for (num in list) {
        if (num > max) max = num
        if (num < min) min = num
        sum += num
        if (num % 2 == 0) numberOfEven ++
        else numberOfOdd ++
    }
    return ("Min: $min \nMax: $max \nSum: $sum \nEven: $numberOfEven \nOdd: $numberOfOdd")
}

fun passwordVerification(password : String) {
    var conditions = 0
    if (password.length >= 8) conditions++
    if (password.any({ it.isDigit()})) conditions++
    if (password.any({ it.isLowerCase()})) conditions++
    if (password.any({ it.isUpperCase()})) conditions++
    if (password.any({ !it.isLetterOrDigit()})) conditions++
    if (conditions == 5) println("Password is secure")
    if (conditions == 4) println("Password is good")
    if (conditions == 2 || conditions == 3) println("Password is middle")
    if (conditions < 2) println("Password is bad")
}