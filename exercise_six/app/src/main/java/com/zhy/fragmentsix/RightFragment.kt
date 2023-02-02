package com.zhy.fragmentsix

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_right.*


class RightFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_right, container, false)
    }

   fun changeInfo(id:Int) {
        when(id){
            0->info.text=getString(R.string.info)
            1->info.text=getString(R.string.educ)
            2->info.text=getString(R.string.ecno)
            else->info.text=getString(R.string.info)
        }
    }

}