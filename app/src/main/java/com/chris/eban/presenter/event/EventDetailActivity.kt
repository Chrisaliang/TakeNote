package com.chris.eban.presenter.event

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.chris.eban.R
import com.chris.eban.databinding.ActivityEventDetailBinding
import com.chris.eban.presenter.BaseActivity
import timber.log.Timber
import javax.inject.Inject

class EventDetailActivity : BaseActivity() {

    private var status: String? = null

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private var binding: ActivityEventDetailBinding? = null
    private var viewModel: EventDetailViewModel? = null

    private var imm: InputMethodManager? = null

    private val touchListener = View.OnTouchListener { v, _ ->
        showIme(v as EditText)
        true
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_detail)
        binding!!.lifecycleOwner = this

        setSupportActionBar(binding!!.toolbar2)

        setStatusBarTextColor(window, true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        viewModel = ViewModelProvider(this, factory).get(EventDetailViewModel::class.java)
        // 判断启动方式，切换编辑状态
        val intent = intent
        status = intent.getStringExtra(PAGE_STATUS)
        Timber.tag(TAG).d("page start status: %s", status)

        val itemId = intent.getLongExtra(PAGE_ITEM_ID, -1L)
        if (itemId != -1L)
            viewModel?.queryItemById(itemId)

        binding!!.detail = viewModel

        binding!!.etTitle.setOnTouchListener(touchListener)
        binding!!.etContent.setOnTouchListener(touchListener)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        when (status) {
            PAGE_STATUS_EDIT -> showToolBarMenu(menu, true)
            PAGE_STATUS_SAVE -> showToolBarMenu(menu, false)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    private fun showToolBarMenu(menu: Menu, toggle: Boolean) {

        menu.findItem(R.id.action_event_save).isVisible = toggle
        menu.findItem(R.id.action_event_edit).isVisible = !toggle
        menu.findItem(R.id.action_event_favorite).isVisible = !toggle
        menu.findItem(R.id.action_event_share).isVisible = !toggle
        menu.findItem(R.id.action_event_delete).isVisible = !toggle
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.event_save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_event_save -> {
                saveEvent()
                return true
            }
            R.id.action_event_edit -> showIme()
            android.R.id.home -> {
                hideIme()
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun hideIme() {
        if (imm == null)
            imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm!!.hideSoftInputFromWindow(binding!!.etTitle.windowToken, 0)
        binding!!.etContent.clearFocus()
        binding!!.etTitle.clearFocus()
        binding!!.etTitle.setOnTouchListener(touchListener)
        binding!!.etContent.setOnTouchListener(touchListener)
        status = PAGE_STATUS_SAVE
        invalidateOptionsMenu()
    }

    private fun showIme() {
        showIme(binding!!.etContent)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showIme(view: EditText) {
        if (imm == null)
            imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm != null) {
            view.requestFocus()
            view.setSelection(view.text.length)
            imm!!.showSoftInput(view, InputMethodManager.RESULT_SHOWN)
            status = PAGE_STATUS_EDIT
            invalidateOptionsMenu()
            view.setOnTouchListener(null)
        }
    }


    private fun setStatusBarTextColor(window: Window, lightStatusBar: Boolean) {
        // 设置状态栏字体颜色 白色与深色
        val decor = window.decorView
        var ui = decor.systemUiVisibility
        ui = ui or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ui = if (lightStatusBar) {
                ui or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                ui and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        }
        decor.systemUiVisibility = ui
    }

    private fun saveEvent() {

        hideIme()

        val title = binding!!.etTitle.text
        val content = binding!!.etContent.text

        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(content)) {
            if (viewModel!!.hadId()) viewModel!!.deleteEvent()
            setResult(Activity.RESULT_CANCELED)
            finish()
        } else {
            Timber.tag(TAG).d("\nEventTitle:%s \nEventContent:%s", title, content)
            viewModel!!.setItem(title, content)
            viewModel!!.saveEvent()
            setResult(Activity.RESULT_OK)
        }
    }

    companion object {
        const val PAGE_STATUS = "com.chris.eban.presenter.event.EventDetailActivity.pageStatus"

        const val PAGE_ITEM_ID = "com.chris.eban.presenter.event.EventDetailActivity.pageItemId"

        const val PAGE_STATUS_EDIT = "edit" // 编辑状态
        const val PAGE_STATUS_SAVE = "save" // 保存状态
        private const val TAG = "EventDetailActivity"
    }
}
