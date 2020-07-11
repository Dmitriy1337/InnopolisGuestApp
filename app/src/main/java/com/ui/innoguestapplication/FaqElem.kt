package com.ui.innoguestapplication

class FaqElem(var question: String, var answer: String, var lang: String) {

    override fun toString(): String {
        return "FaqElem: que-$question;\nanswer-$answer;\nlang-$lang\n";
    }
}