package com.chris.eban.presenter.event;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.chris.eban.R;
import com.chris.eban.databinding.ActivityEventDetailBinding;
import com.chris.eban.presenter.BaseActivity;

import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import timber.log.Timber;

public class EventDetailActivity extends BaseActivity {

    public static final String PAGE_STATUS = "com.chris.eban.presenter.event.EventDetailActivity.pageStatus";
    public static final String PAGE_ITEM = "com.chris.eban.presenter.event.EventDetailActivity.pageItem";
    public static final String PAGE_STATUS_EDIT = "edit"; // 编辑状态
    public static final String PAGE_STATUS_SAVE = "save"; // 保存状态
    private static final String TAG = "EventDetailActivity";

    private String status;
    @Inject
    ViewModelProvider.Factory factory;

    private boolean saved = false;

    private ActivityEventDetailBinding binding;
    private EventDetailViewModel viewModel;
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private InputMethodManager imm;

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            showIme((EditText) v);
            return true;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_detail);
        binding.setLifecycleOwner(this);
        setSupportActionBar(binding.toolbar2);

        setStatusBarTextColor(getWindow(), true);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        viewModel = ViewModelProviders.of(this, factory).get(EventDetailViewModel.class);
        // 判断启动方式，切换编辑状态
        Intent intent = getIntent();
        status = intent.getStringExtra(PAGE_STATUS);
        Timber.tag(TAG).d("page start status: %s", status);
        EventItem item = Objects.requireNonNull(intent.getExtras()).getParcelable(PAGE_ITEM);
        viewModel.setItem(item);
        binding.setDetail(viewModel);

        binding.etTitle.setOnTouchListener(touchListener);
        binding.etTitle.addTextChangedListener(watcher);
        binding.etContent.setOnTouchListener(touchListener);
        binding.etContent.addTextChangedListener(watcher);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        switch (status) {
            case PAGE_STATUS_EDIT:
                showToolBarMenu(menu, true);
                break;
            case PAGE_STATUS_SAVE:
                showToolBarMenu(menu, false);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    private void showToolBarMenu(Menu menu, boolean toggle) {

        menu.findItem(R.id.action_event_save).setVisible(toggle);
        menu.findItem(R.id.action_event_edit).setVisible(!toggle);
        menu.findItem(R.id.action_event_favorite).setVisible(!toggle);
        menu.findItem(R.id.action_event_share).setVisible(!toggle);
        menu.findItem(R.id.action_event_delete).setVisible(!toggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.event_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_event_save:
                saveEvent();
                return true;
            case R.id.action_event_edit:
                showIme();
                break;
            case android.R.id.home:
                hideIme();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void hideIme() {
        if (imm == null)
            imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.etTitle.getWindowToken(), 0);
        binding.etContent.clearFocus();
        binding.etTitle.clearFocus();
        status = PAGE_STATUS_SAVE;
        invalidateOptionsMenu();
    }

    private void showIme() {
        showIme(binding.etTitle);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void showIme(EditText view) {
        if (imm == null)
            imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) {
            view.requestFocus();
            view.setSelection(view.getText().length());
            imm.showSoftInput(view, InputMethodManager.RESULT_SHOWN);
            status = PAGE_STATUS_EDIT;
            invalidateOptionsMenu();
            view.setOnTouchListener(null);
        }
    }


    public void setStatusBarTextColor(Window window, boolean lightStatusBar) {
        // 设置状态栏字体颜色 白色与深色
        View decor = window.getDecorView();
        int ui = decor.getSystemUiVisibility();
        ui |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (lightStatusBar) {
                ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
        }
        decor.setSystemUiVisibility(ui);
    }

    private void saveEvent() {

        if (saved) return;
        saved = true;
        hideIme();

        Editable title = binding.etTitle.getText();
        Editable content = binding.etContent.getText();

        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(content)) {
            setResult(RESULT_CANCELED);
            finish();
        } else {
            Timber.tag(TAG).d("\nEventTitle:%s \nEventContent:%s", title, content);
            viewModel.setItem(title, content);
            viewModel.saveEvent();
            setResult(RESULT_OK);
        }
    }
}
