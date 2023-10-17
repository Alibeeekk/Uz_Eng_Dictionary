package dev.alibek.uz_rulugat.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import dev.alibek.uz_rulugat.R


class AboutFragment : Fragment(R.layout.fragment_about) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        val ivData:ImageView=view.findViewById(R.id.iv_data)

        ivData.setOnClickListener {
            intent()
            showToast(message = "Data Ta'lim Stansiyasi")
        }
    }

    private fun showToast(message: String) {

        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()

    }

    private fun intent() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://datatalim.uz"))
        startActivity(intent)

    }

}