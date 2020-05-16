package com.programmergabut.academy.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.programmergabut.academy.R
import com.programmergabut.academy.data.source.local.entity.CourseEntity
import com.programmergabut.academy.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_bookmark.*

class BookmarkFragment : Fragment(), BookmarkFragmentCallback  {

    private lateinit var viewModel: BookmarkViewModel
    private lateinit var adapter: BookmarkAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        itemTouchHelper.attachToRecyclerView(rv_bookmark)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[BookmarkViewModel::class.java]
            adapter = BookmarkAdapter(this)

            progress_bar.visibility = View.VISIBLE
            viewModel.getBookmarks().observe(viewLifecycleOwner, Observer{ courses ->
                progress_bar.visibility = View.GONE
                adapter.submitList(courses)
                adapter.notifyDataSetChanged()
            })

            rv_bookmark.layoutManager = LinearLayoutManager(context)
            rv_bookmark.setHasFixedSize(true)
            rv_bookmark.adapter = adapter
        }
    }

    override fun onShareClick(course: CourseEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder.from(requireActivity()).apply {
                setType(mimeType)
                setChooserTitle("Bagikan aplikasi ini sekarang.")
                setText(resources.getString(R.string.share_text, course.title))
                startChooser()
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            // Aksi di bawah digunakan untuk melakukan swap ke kenan dan ke kiri
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {

                // Sebelum melakukan penghapusan, course harus mendapatkan posisi dari item yang di swipe
                val swipedPosition = viewHolder.adapterPosition

                // Kemudian memanggil CourseEntity sesuai posisi ketika diswipe
                val courseEntity = adapter.getSwipedData(swipedPosition)

                // Melakukan setBookmark untuk menghapus bookmark dari list course
                courseEntity?.let { viewModel.setBookmark(it) }

                // Memanggil Snackbar untuk melakukan pengecekan, apakah benar melakukan penghapusan bookmark
                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)

                // Mengembalikan item yang terhapus
                snackbar.setAction(R.string.message_ok) { v -> courseEntity?.let { viewModel.setBookmark(it) } }

                // Menampilkan snackbar
                snackbar.show()
            }
        }
    })


}
