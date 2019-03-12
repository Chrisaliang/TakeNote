package com.chris.eban.presenter;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.chris.eban.R;
import com.chris.eban.databinding.ActivityCreateEventBinding;
import com.chris.eban.domain.usecase.EventSaveInsert;
import com.chris.eban.presenter.event.EventItem;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import timber.log.Timber;

public class EventDetailActivity extends BaseActivity {

    private static final String TAG = "EventDetailActivity";

    @Inject
    EventSaveInsert eventSaveInsert;

    private ActivityCreateEventBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_event);
        setSupportActionBar(binding.toolbar2);

        setStatusBarTextColor(getWindow(), true);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create_save:
            case android.R.id.home:
                saveEvent();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
        Editable title = binding.etTitle.getText();
        Editable content = binding.etContent.getText();

        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(content)) {
            setResult(RESULT_CANCELED);
            finish();
        } else {
            Timber.tag(TAG).d("\nEventTitle:%s \nEventContent:%s", title, content);
            binding.etTitle.clearFocus();
            binding.etContent.clearFocus();
            Toast.makeText(this, title + ": " + content, Toast.LENGTH_LONG).show();

            EventItem eventItem = new EventItem();
            eventItem.title = title.toString();
            eventItem.content = content.toString();
            eventSaveInsert.setItem(eventItem);

            eventSaveInsert.execute().subscribe();


            setResult(RESULT_OK);
        }
    }
}
