class Fraction(private var numerator: Int = 0, private var denominator: Int = 1) {

    //input fraction from keyboard
    fun input() {
        while (true) {
            print("Enter numerator: ")
            numerator = readln().toInt()
            if (numerator == 0) {
                println("Numerator cannot be 0. Please enter again!")
                continue
            }
            break
        }

        while (true) {
            print("Enter denominator: ")
            denominator = readln().toInt()
            if (denominator == 0) {
                println("Denominator cannot be 0. Please enter again!")
                continue
            }
            break
        }
    }

    //print fraction
    fun printFrac() {
        if (denominator == 1) {
            print("$numerator")
        } else {
            print("$numerator/$denominator")
        }
    }

    // Find greatest common divisor (GCD)
    private fun gcd(a: Int, b: Int): Int {
        var x = Math.abs(a)
        var y = Math.abs(b)
        while (y != 0) {
            val temp = y
            y = x % y
            x = temp
        }
        return x
    }

    // Method to simplify fraction
    fun simplify() {
        val gcdValue = gcd(numerator, denominator)
        numerator /= gcdValue
        denominator /= gcdValue

        // Move negative sign to numerator
        if (denominator < 0) {
            numerator = -numerator
            denominator = -denominator
        }
    }

    //create a simplified copy without modifying original
    fun getSimplified(): Fraction {
        val copy = Fraction(numerator, denominator)
        copy.simplify()
        return copy
    }

    //compare with another fraction
    fun compareTo(f: Fraction): Int {
        val thisValue = numerator.toDouble() / denominator
        val fValue = f.numerator.toDouble() / f.denominator

        return when {
            thisValue < fValue -> -1
            thisValue > fValue -> 1
            else -> 0
        }
    }

    // Method to add with another fraction
    fun add(f: Fraction): Fraction {
        val newNumerator = numerator * f.denominator + f.numerator * denominator
        val newDenominator = denominator * f.denominator
        val result = Fraction(newNumerator, newDenominator)
        result.simplify()
        return result
    }

    // Method to get real value of fraction
    fun getValue(): Double {
        return numerator.toDouble() / denominator
    }
}

fun main() {
    // Input array of fractions
    print("Enter number of fractions: ")
    val n = readln().toInt()
    val fractionArray = Array(n) { Fraction() }

    println("\nINPUT")
    for (i in fractionArray.indices) {
        println("Fraction ${i + 1}:")
        fractionArray[i].input()
    }

    // Print the input array
    println("\nARRAY")
    for (i in fractionArray.indices) {
        fractionArray[i].printFrac()
        print(" ")
    }

    // Simplify all fractions in the array
    println("\nSIMPLIFY")
    val simplifiedArray = Array(n) { Fraction() }
    for (i in fractionArray.indices) {
        simplifiedArray[i] = fractionArray[i].getSimplified()

        simplifiedArray[i].printFrac()
        print(" ")
    }

    // Calculate sum of all fractions
    println("\n")
    var sum = Fraction(0, 1)
    for (f in simplifiedArray) {
        sum = sum.add(f)
    }
    print("SUM = ")
    sum.printFrac()
    println()

    // Find maximum fraction
    println("\nMAX")
    var max = simplifiedArray[0]
    var maxPosition = 0
    for (i in 1 until simplifiedArray.size) {
        if (simplifiedArray[i].compareTo(max) > 0) {
            max = simplifiedArray[i]
            maxPosition = i
        }
    }
    print("Maximum fraction: ")
    max.printFrac()
    println(" (at position ${maxPosition + 1})")

    // Sort array in descending order (using original fractions)
    println("\nDESCENDING")
    val sortedArray = fractionArray.copyOf()
    for (i in sortedArray.indices) {
        for (j in i + 1 until sortedArray.size) {
            if (sortedArray[i].compareTo(sortedArray[j]) < 0) {
                val temp = sortedArray[i]
                sortedArray[i] = sortedArray[j]
                sortedArray[j] = temp
            }
        }
    }

    for (i in sortedArray.indices) {
        sortedArray[i].printFrac()
        print(" ")
    }
}