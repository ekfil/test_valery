package com.example.test_valery.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test_valery.R
import com.example.test_valery.data.Resp.ResponceItem
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var item = intent.getSerializableExtra("item") as ResponceItem
        text.text= "Text: ${item.text}"
        used.text="Used: ${item.used}"
        user.text = "User: ${item.user}"
        createdAt.text= "Created: ${item.createdAt}"
        source.text = "Source: ${item.source}"
        __v.text = "V : ${item.__v}"
        id.text = "Id: ${item._id}"
        setCount.text = "Count:${item.status.sentCount}"
        verified.text ="Verified: ${item.status.verified}"
        type.text ="Type :${item.type}"
        updatetedAt.text = "UpdateAt: ${item.updatedAt}"
        deleted.text = "Deleted : ${item.deleted}"



    }
}