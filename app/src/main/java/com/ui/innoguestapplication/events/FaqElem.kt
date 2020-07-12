package com.ui.innoguestapplication.events

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
class FaqElem(@SerializedName("question")var question: String,
              @SerializedName("answer")var answer: String,
              @SerializedName("lang")var lang: String) {

}