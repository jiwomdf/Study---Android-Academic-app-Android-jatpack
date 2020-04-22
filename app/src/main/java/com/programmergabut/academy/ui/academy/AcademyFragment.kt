package com.programmergabut.academy.ui.academy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.programmergabut.academy.R
import com.programmergabut.academy.utils.DataDummy
import kotlinx.android.synthetic.main.fragment_academy.*

/**
 * A simple [Fragment] subclass.
 */
class AcademyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_academy, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {

            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[AcademyViewModel::class.java]
            val courses = viewModel.getCourses()

            val academyAdapter = AcademyAdapter()
            academyAdapter.setCourses(courses)

            rv_academy.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
            }

        }
    }



}
