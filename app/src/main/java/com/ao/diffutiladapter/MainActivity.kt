package com.ao.diffutiladapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ao.diffutiladapter.adapter.FooterAdapter
import com.ao.diffutiladapter.adapter.UserAdapter
import com.ao.diffutiladapter.databinding.ActivityMainBinding
import com.ao.diffutiladapter.vm.MainVM

class MainActivity : AppCompatActivity() {

    private val vm by viewModels<MainVM>()
    private val userAdapter = UserAdapter()
    private val footerAdapter =
        FooterAdapter(this::onRetry)

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // binding.recyclerView.apply {  }
        binding.recyclerView.run {
            setHasFixedSize(true)
            val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            layoutManager = linearLayoutManager

            adapter = MergeAdapter(userAdapter, footerAdapter)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0 && linearLayoutManager.findLastVisibleItemPosition() + VISIBLE_THRESHOLD >= linearLayoutManager.itemCount) {
                        vm.loadNextPage()
                    }

                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    Toast.makeText(this@MainActivity,"ScrollStateChanged",Toast.LENGTH_SHORT).show()
                }

            })

             vm.loadingStateLiveData.observe(this@MainActivity, Observer {
                 footerAdapter.submitList(it)
             })

              vm.userLiveData.observe(this@MainActivity, Observer {
                      userAdapter.submitList(it)
              })

             vm.loadNextPage()


        }
    }

    private fun onRetry() = vm.retryNextPage()

    private companion object {
        private const val VISIBLE_THRESHOLD = 5
    }


}
