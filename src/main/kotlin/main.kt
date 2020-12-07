fun main() {
    while(true) {
        print("Введите, прошедшее с момента публикации сообщения, сек. (пустая строка - выход): ")
        val input = readLine()
        if(input!!.isEmpty()) {
            println("Работа завершена.")
            return
        }

        val seconds = input.toInt()
        println("\"" + agoToText(seconds) + "\"")
    }
}

fun agoToText(seconds: Int): String {
    return "был(а) " + when {
        seconds < 61                -> "только что"
        seconds < 60 * 60           -> "${minutesToText(seconds / 60)} назад "
        seconds < 24 * 60 * 60      -> "${hoursToText(seconds / 60 / 60)} назад"
        seconds < 2 * 24 * 60 * 60  -> "сегодня"
        seconds < 3 * 24 * 60 * 60  -> "вчера"
        else -> "давно"
    }
}

fun minutesToText(minutes: Int): String {
    return when (endsVariant(minutes)) {
        1 -> "$minutes минуту"
        2 -> "$minutes минуты"
        else -> "$minutes минут"
    }
}

fun hoursToText(hours: Int): String {
    return when (endsVariant(hours)) {
        1 -> "$hours час"
        2 -> "$hours часа"
        else -> "$hours часов"
    }
}

fun endsVariant(number: Int): Int {
    val stringValue = number.toString()
    val lastLiteral = stringValue.takeLast(1).toInt()

    val notInSecondDecade =
        stringValue.length > 1 && (stringValue.takeLast(2).toInt() < 10 || stringValue.takeLast(2).toInt() > 20)

    return when {
        lastLiteral == 1 && notInSecondDecade -> 1      // 1, 21, 31,.. 101
        lastLiteral in 2..4 && notInSecondDecade -> 2   // 2...4, 22...24,.. 102...104
        else -> 3                                       // 5...20, 25...30, 35...40,.. 105...120
    }
}