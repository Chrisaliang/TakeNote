package com.chris.eban.presenter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.chris.eban.R
import com.chris.eban.presenter.event.EventDetailActivity
import com.chris.eban.presenter.event.EventDetailActivity.Companion.PAGE_STATUS
import com.chris.eban.presenter.event.EventDetailActivity.Companion.PAGE_STATUS_EDIT
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appbarConfiguration = AppBarConfiguration.Builder(
                R.id.nav_event,
                R.id.nav_gallery,
                R.id.nav_slideshow,
                R.id.nav_manage
        ).build()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
//        NavigationUI.setupActionBarWithNavController(this, navController, appbarConfiguration)
        NavigationUI.setupWithNavController(nav_view, navController)
        NavigationUI.navigateUp(navController, appbarConfiguration)

//        fab.setOnClickListener { createNewEvent() }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_CREATE) {
            if (resultCode == Activity.RESULT_OK) {
                // todo: notify fragment to update the event list
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // show notify toast
                Toast.makeText(this, R.string.event_create_save_empty, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createNewEvent() {
        val intent = Intent(this, EventDetailActivity::class.java)
        intent.putExtra(PAGE_STATUS, PAGE_STATUS_EDIT)
        startActivityForResult(intent, REQUEST_CODE_CREATE)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val REQUEST_CODE_CREATE: Int = 0x0001
        private const val TAG: String = "MainActivity"
    }
}
