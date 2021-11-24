package com.example.test_valery.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.test_valery.R
import com.example.test_valery.data.Resp.Responce
import com.example.test_valery.data.Resp.ResponceItem
import com.example.test_valery.domain.ContextApplication
import com.example.test_valery.domain.factViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    lateinit var viewmodel: factViewModel
    lateinit var factAdapter: Adapter

    private val CustomComparator =  Comparator<ResponceItem> { a, b ->
        when {
            (a._id > b._id) -> 0
            (a._id < b._id) -> -1
            else -> 1
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        factAdapter = Adapter()

        viewmodel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(ContextApplication.instance)
        ).get(factViewModel::class.java)
        viewmodel.getService()
        initRecycler()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        var item =menu?.findItem(R.id.search_action)
        var searchView = item?.actionView as SearchView
        searchView.setOnQueryTextFocusChangeListener(object : SearchView.OnQueryTextListener,
            View.OnFocusChangeListener {
            override fun onFocusChange(p0: View?, p1: Boolean) {

            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newString: String?): Boolean {
                if (newString != null) {
                        factAdapter.list.filter {
                            it.text.lowercase().contains(newString.toString().lowercase())
                        }
                }
                recycle.adapter?.notifyDataSetChanged()
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }


    private fun initRecycler() {
        recycle.apply {
            factAdapter.setOnClickLisner(object :Adapter.OnitemClickListner{
                override fun OnClickItem(position: Int) {
                    var intent = Intent(this@MainActivity,DetailActivity::class.java)
                    val item  =viewmodel.responce.value?.get(position)
                    intent.putExtra("item",item)
                    startActivity(intent)
                }
            })
            adapter = factAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

            viewmodel.getData().observe(this@MainActivity, object : Observer<Responce> {
                override fun onChanged(t: Responce?) {
                    if (t != null) {
                        t.sortWith(CustomComparator)
                        factAdapter.list = t
                        factAdapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this@MainActivity, "Error loading", Toast.LENGTH_LONG).show()
                    }


                }
            })
            viewmodel.sucsseded.observe(this@MainActivity, object : Observer<Boolean> {
                override fun onChanged(t: Boolean?) {
                    when (t) {
                        false -> progress.visibility = View.VISIBLE
                        true -> progress.visibility = View.INVISIBLE
                    }
                }
            })
        }
    }
}