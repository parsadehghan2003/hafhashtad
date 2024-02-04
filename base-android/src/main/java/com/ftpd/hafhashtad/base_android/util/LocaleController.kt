package com.ftpd.hafhashtad.base_android.util

object LocaleController {
    var isRTL = false

    fun getLanguageFlag(countryCode:String): String? {
        if (countryCode.length != 2 || countryCode == "YL") return null

        if (countryCode == "XG") {
            return "\uD83D\uDEF0"
        } else if (countryCode == "XV") {
            return "\uD83C\uDF0D"
        }


        val base = 0x1F1A5
        val chars: CharArray = countryCode.toCharArray()
        val emoji = charArrayOf(
            CharacterCompat.highSurrogate(base),
            CharacterCompat.lowSurrogate(base + chars[0].toInt()),
            CharacterCompat.highSurrogate(base),
            CharacterCompat.lowSurrogate(base + chars[1].toInt())
        )
        return String(emoji)
    }
}