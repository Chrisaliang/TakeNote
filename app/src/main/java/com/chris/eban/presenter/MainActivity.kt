package com.chris.eban.presenter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.chris.eban.R
import com.chris.eban.presenter.event.EventDetailActivity
import com.chris.eban.presenter.event.EventDetailActivity.Companion.PAGE_STATUS
import com.chris.eban.presenter.event.EventDetailActivity.Companion.PAGE_STATUS_EDIT
import com.chris.eban.presenter.event.EventListFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_replace, EventListFragment())
                    .commitNow()

        fab.setOnClickListener { createNewEvent() }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
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

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    companion object {
        private const val REQUEST_CODE_CREATE: Int = 0x0001
        private const val TAG: String = "MainActivity"
    }
}
