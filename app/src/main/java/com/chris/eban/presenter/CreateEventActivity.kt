package com.chris.eban.presenter

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.chris.eban.R
import com.chris.eban.databinding.ActivityCreateEventBinding
import timber.log.Timber

class CreateEventActivity : BaseActivity() {

    private val TAG: String = "CreateEventActivity"

    private lateinit var binding: ActivityCreateEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_event)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.create, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_create_save -> saveEvent()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveEvent(): Boolean {
        val eventTitle = binding.etTitle.text
        val eventContent = binding.etContent.text

        if (TextUtils.isEmpty(eventTitle) &&
                TextUtils.isEmpty(eventContent)) {
            Toast.makeText(this, R.string.event_create_save_empty, Toast.LENGTH_LONG).show()
            finish()
        } else {
            Timber.tag(TAG).d("\nEventTitle:%s \nEventContent:%s", eventTitle, eventContent)
            binding.etTitle.clearFocus()
            binding.etContent.clearFocus()
            Toast.makeText(this, eventTitle.toString() + ": " + eventContent, Toast.LENGTH_LONG).show()
        }

        return true
    }
}
