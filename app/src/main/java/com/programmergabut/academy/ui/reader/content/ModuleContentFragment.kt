package com.programmergabut.academy.ui.reader.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.programmergabut.academy.R
import com.programmergabut.academy.data.source.local.entity.ModuleEntity
import com.programmergabut.academy.ui.reader.CourseReaderViewModel
import com.programmergabut.academy.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_module_content.*

/**
 * A simple [Fragment] subclass.
 */
class ModuleContentFragment : Fragment() {

    companion object {
        val TAG = ModuleContentFragment::class.java.simpleName

        fun newInstance(): ModuleContentFragment {
            return ModuleContentFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {

            val factor = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(requireActivity(), factor)[CourseReaderViewModel::class.java]
            val module = viewModel.getSelectedModule()

            progress_bar.visibility = View.VISIBLE
            viewModel.getSelectedModule().observe(viewLifecycleOwner, Observer{ module ->
                if (module != null) {
                    progress_bar.visibility = View.GONE
                    populateWebView(module)
                }
            })

        }
    }

    private fun populateWebView(module: ModuleEntity) {
        web_view.loadData(module.contentEntity?.content, "text/html", "UTF-8")
    }


}
