package com.example.challengechapter7kelompok3.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.example.challengechapter7kelompok3.R


class LandingPage3Fragment : Fragment() {

    lateinit var  etName : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_landing_page3, container, false)

//        etName = view.findViewById(R.id.et_name)
//
//        etName.addTextChangedListener {
//            listener?.afterUserInputName(it.toString())
//        }

        return view
    }

//    var listener: UserNameInputListener? = null
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//
//        if (context is UserNameInputListener) {
//            listener = context
//        }
//    }
//
//    interface UserNameInputListener {
//        fun afterUserInputName(input: String)
//    }

}