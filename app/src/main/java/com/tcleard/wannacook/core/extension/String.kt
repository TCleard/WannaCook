package com.tcleard.wannacook.core.extension

import java.text.Normalizer

private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()

fun String.unaccent(): String {
    val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
    return REGEX_UNACCENT.replace(temp, "")
}

fun String.matchQuery(query: String): Boolean =
        toLowerCase().unaccent().contains(query.toLowerCase().unaccent())

fun String.equalsQuery(query: String): Boolean =
        toLowerCase().unaccent() == query.toLowerCase().unaccent()
