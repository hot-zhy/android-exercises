package com.zhy.fragmentsix

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_left.*

class LeftFragment : Fragment() {
    lateinit var mainActivity:MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(activity!=null){
            mainActivity=activity as MainActivity
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_left, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val right=mainActivity.supportFragmentManager.findFragmentById(R.id.rightFrag) as RightFragment
        infomation.setOnClickListener {
            right.changeInfo(0)
        }
        education.setOnClickListener {
            right.changeInfo(1)
        }
        ecnomic.setOnClickListener {
            right.changeInfo(2)
        }

    }
}