fun main(args: Array<String>) {


    println("Result =  ${testExpression("abc1234567")}")
    println("Alfabeto =  ${isAlphabaticOrder("Allefsousa")}")
    println("ValidSequence =  ${isValidSequence("Fabio@")}")
    println("isCorrectOrder =  ${isAlphabaticOrder("1994")}")
    println("Consecutive charactes =  ${hisConsecutiveRepeatCharacters("ABCaaaaabbbbb")}")

}

fun testExpression(value:String) :Boolean{
    var regexEmail = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,64}"
    val capitalLetterRegEx = ".*[A-Z]+.*"
    val lowerLetterRegEx = ".*[a-z]+.*"
    val numberRegEx = ".*[0-9]+.*"
    val specialCharacterRegEx = ".*[!&^%\$#@()/_+-]+.*"

    val isletterRepetLoweCaseSequence = ".*^([a-z])\\1{2}+.*"
    val isletterRepeatSequenceUppercase = ".*^([A-Z])\\1{2}+.*"
    val IsletterSequenceLowerCaseAlphabetic = ".*^a*b*c*d*e*f*g*h*i*j*k*l*m*n*o*p*q*r*s*t*u*v*w*x*y*z*.*"
    val IsletterSequenceUpperCaseAlphabetic = ".*^A*B*C*D*E*F*G*H*I*J*K*L*M*N*O*P*Q*rR*S*T*U*V*W*X*Y*Z*\$.*"
    var count = 0

    if (value.length in 8..16){
        val containsCapitalLetters = value.matches(capitalLetterRegEx.toRegex())
        val containsLowerLetters = value.matches(lowerLetterRegEx.toRegex())
        val containsNumber = value.matches(numberRegEx.toRegex())
        val containsSpecialCharacter = value.matches(specialCharacterRegEx.toRegex())
        val containsSpecialCharacter1 = value.matches(isletterRepetLoweCaseSequence.toRegex())
        val containsSpecialCharacter2 = value.matches(isletterRepeatSequenceUppercase.toRegex())
        val containsSpecialCharacter3 = value.matches(IsletterSequenceLowerCaseAlphabetic.toRegex())
        val containsSpecialCharacter4 = value.matches(IsletterSequenceUpperCaseAlphabetic.toRegex())
        val listBool = arrayListOf(containsCapitalLetters,containsLowerLetters,containsNumber,containsSpecialCharacter)
        val listSequence = arrayListOf(containsSpecialCharacter1,containsSpecialCharacter2,containsSpecialCharacter3,containsSpecialCharacter4)
        println("Allef"+listBool.size)
        listBool.forEach {
            if (it){
                count+=1
            }
        }

        listSequence.forEach {
            if (it){
                return false
            }
        }

    if (count >= 2){
        return true
    }
    }else{
        return false
    }
return false

}


fun isValidSequence(senha: String): Boolean {
    val listNumber = mutableListOf<Int>()
    val listStringLower = mutableListOf<String>()
    val listStringUpper = mutableListOf<String>()
    var lower = false
    var upper = false
    var numbers = false
    var special = false
    senha.forEach { it ->
        when {
            it.isDigit() -> {
                numbers = true
                listNumber.add(it.toString().toInt())
            }
            it.isLowerCase() ->{
                lower = true
                listStringLower.add(it.toString())
            }
            it.isUpperCase()->{
                upper = true
                listStringUpper.add(it.toString())

            }
            else -> special = true
        }
    }


    val listIsTrue = arrayListOf(lower,upper,numbers,special).filter { it }.size
    var regras = mutableListOf<Boolean>()

    if (listIsTrue>=2){
        println("########### Respostas ##############")

        if (listStringUpper.isNotEmpty()){
            val listUpper = listStringUpper.joinToString("")
            regras.add(isAlphabaticOrder(listUpper))
            regras.add(hisConsecutiveRepeatCharacters(listUpper))
            println("isAlphabaticOrder Upper "+ isAlphabaticOrder(listUpper) + " result $listUpper")
            println("hisConsecutiveRepeatCharacters upper "+ hisConsecutiveRepeatCharacters(listUpper) + " result $listUpper")
        }
        if (listStringLower.isNotEmpty()){
            val listLower = listStringLower.joinToString("")
            regras.add(hisConsecutiveRepeatCharacters(listLower))
            regras.add(isAlphabaticOrder(listLower))
            println("hisConsecutiveRepeatCharacters  "+  hisConsecutiveRepeatCharacters(listLower) + " result $listLower")
            println("isAlphabaticOrder "+ isAlphabaticOrder(listLower) + " result $listLower")

        }
        if (listNumber.isNotEmpty()) {
            val listNum = listNumber.joinToString("").toInt()
            regras.add(isAlphabaticOrderNumber(listNum.toString()))
//            regras.add(isRepeatOrder(listNum))
            println("isAlphabaticOrder "+ isAlphabaticOrderNumber(listNum.toString()) + " result $listNum")
//            println("isRepeatOrder "+ isRepeatOrder(listNum) + " result $listNum")

        }
        println("########### FIM Respostas ##############")
        return regras.none { it }
    }

    return false
}
fun isAlphabaticOrder(s: String): Boolean {
    val n = s.length
    if (n==1){
        return false
    }
    for (i in 1 until n) {
        if (s[i] < s[i - 1]) {
            return false
        }
    }
    return true
}
fun isAlphabaticOrderNumber(s: String): Boolean {
    val n = s.length
    if (n<=3){
        return false
    }
    for (i in 1 until n) {
        if (s[i] < s[i - 1]) {
            return false
        }
    }
    return true
}
fun hisConsecutiveRepeatCharacters(pwd: String): Boolean {
    val letter =
        pwd.split("".toRegex()).toTypedArray() // here you get each letter in to a string array
    for (i in 0 until letter.size - 2) {
        if (letter[i] == letter[i + 1] && letter[i + 1] == letter[i + 2]) {
            return true //return true as it has 3 consecutive same character
        }
    }
    return false //If you reach here that means there are no 3 consecutive characters therefore return false.
}



