package com.example.test_valery.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test_valery.R
import com.example.test_valery.data.Resp.ResponceItem
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var item = intent.getSerializableExtra("item") as ResponceItem
        text.setText("Text: ${item.text}")
        used.setText("Used: ${item.used}")
        user.setText("User: ${item.user}")
        createdAt.setText("Created: ${item.createdAt}")
        source.setText("Source: ${item.source}")
        __v.setText("V : ${item.__v}")
        id.text = "Id: ${item._id}"
        setCount.text = "Count:${item.status.sentCount}"
        verified.text ="Verified: ${item.status.verified}"
        type.text ="Type :${item.type}"
        updatetedAt.text = "UpdateAt: ${item.updatedAt}"
        deleted.text = "Deleted : ${item.deleted}"



    }
}