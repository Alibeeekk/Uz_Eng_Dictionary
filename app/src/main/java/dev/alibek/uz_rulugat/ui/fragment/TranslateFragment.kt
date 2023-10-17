package dev.alibek.uz_rulugat.ui.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import com.google.android.material.button.MaterialButton
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import dev.alibek.uz_rulugat.R
import dev.alibek.uz_rulugat.model.ModelLanguage
import java.util.Locale


class TranslateFragment : Fragment(R.layout.fragment_translate) {
    lateinit var b1: MaterialButton
    lateinit var b2: MaterialButton
    lateinit var etTranslate: EditText
    lateinit var tvTarget: TextView
    lateinit var btranslate: MaterialButton

    companion object {
        private const val TAG = "MAIN_TAG"
    }


    private var languageArrayList: ArrayList<ModelLanguage>? = null

    private var sourceLanguageCode = "en"
    private var sourceLanguageTitle = "English"
    private var targetLanguageCode = "uz"
    private var targetLanguageTitle = "Uzbek"


    private lateinit var translateOption: TranslatorOptions

    private lateinit var translator: Translator

    private lateinit var progressDialog: ProgressDialog


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        b1 = view.findViewById(R.id.b1)
        b2 = view.findViewById(R.id.b2)
        etTranslate = view.findViewById(R.id.et_translate)
        tvTarget = view.findViewById(R.id.target_translateTv)
        btranslate = view.findViewById(R.id.b_translate)

        progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Please wait")
        progressDialog.setCanceledOnTouchOutside(false)


        loadAviableLanguages()

        b1.setOnClickListener {
            sourceLanguageChoose()
        }
        b2.setOnClickListener {
            targetLanguageChoose()
        }

        btranslate.setOnClickListener {
            valiadeteData()
        }

    }

    private var sourcelanguageText=""
    private fun valiadeteData() {
        sourcelanguageText = etTranslate.text.toString().trim()

        if (sourcelanguageText.isEmpty()){
            showToast("ENTER TRANSLATE TO TEXT...")

        }else{
            startTranslator()
        }

    }

    private fun startTranslator() {
        progressDialog.setMessage("Processing language model...")
        progressDialog.show()

        translateOption = TranslatorOptions.Builder()
            .setSourceLanguage(sourceLanguageCode)
            .setTargetLanguage(targetLanguageCode)
            .build()

        translator =Translation.getClient(translateOption)

        val downloadCondition = DownloadConditions.Builder()
            .requireWifi()
            .build()

        translator.downloadModelIfNeeded(downloadCondition)
            .addOnSuccessListener {

                progressDialog.setMessage("Translating...")
                translator.translate(sourcelanguageText)
                    .addOnSuccessListener {translatorText ->
                        progressDialog.dismiss()
                        tvTarget.text = translatorText

                    }
                    .addOnFailureListener {e->
                        progressDialog.dismiss()

                        showToast("Failed to translate due to ${e.message}")

                    }
            }
            .addOnFailureListener {e->
                progressDialog.dismiss()

                showToast("Failed to translate due to ${e.message}")

            }
    }

    private fun showToast(message:String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun loadAviableLanguages() {
        languageArrayList = ArrayList()

        val languageCodeList = TranslateLanguage.getAllLanguages()

        for (languageCode in languageCodeList) {
            val languageTitle = Locale(languageCode).displayLanguage
            Log.d(TAG, "loadAviableLanguages: $languageCode")
            Log.d(TAG, "loadAviableLanguages: $languageTitle")

            val modelLanguage = ModelLanguage(languageCode, languageTitle)

            languageArrayList!!.addAll(listOf(modelLanguage))
        }


    }

    private fun sourceLanguageChoose() {
        val popupMenu: PopupMenu = PopupMenu(requireContext(), b1)

        for (i in languageArrayList!!.indices) {
            popupMenu.menu.add(Menu.NONE, i, i, languageArrayList!![i].languageTitle)

        }

        popupMenu.show()

        popupMenu.setOnMenuItemClickListener { menuItem ->
            val position = menuItem.itemId
            sourceLanguageCode = languageArrayList!![position].languageCode
            sourceLanguageTitle = languageArrayList!![position].languageTitle

            b1.text = sourceLanguageTitle
            b1.hint = "Enter $sourceLanguageTitle"
            false
        }
    }

    private fun targetLanguageChoose() {
        val popupMenu: PopupMenu = PopupMenu(requireContext(), b2)

        for (i in languageArrayList!!.indices) {
            popupMenu.menu.add(Menu.NONE, i, i, languageArrayList!![i].languageTitle)

        }

        popupMenu.show()

        popupMenu.setOnMenuItemClickListener { menuItem ->

            val position = menuItem.itemId

            targetLanguageCode = languageArrayList!![position].languageCode
            targetLanguageTitle = languageArrayList!![position].languageTitle

            b2.text = targetLanguageTitle
            false
        }
    }

}