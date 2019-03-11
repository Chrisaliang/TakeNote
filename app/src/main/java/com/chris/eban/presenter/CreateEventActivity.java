package com.chris.eban.presenter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.chris.eban.R;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import timber.log.Timber;

import com.chris.eban.databinding.ActivityCreateEventBinding;
import com.chris.eban.domain.EventListRepository;
import com.chris.eban.domain.usecase.EventSave;
import com.chris.eban.presenter.event.EventItem;

import javax.inject.Inject;

public class CreateEventActivity extends BaseActivity {

    private static final String TAG = "CreateEventActivity";

    @Inject
    EventSave eventSave;

    private ActivityCreateEventBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_event);
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
                saveEvent();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveEvent() {
        Editable title = binding.etTitle.getText();
        Editable content = binding.etContent.getText();

        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(content)) {
            Toast.makeText(this, R.string.event_create_save_empty, Toast.LENGTH_LONG).show();
            finish();
        } else {
            Timber.tag(TAG).d("\nEventTitle:%s \nEventContent:%s", title, content);
            binding.etTitle.clearFocus();
            binding.etContent.clearFocus();
            Toast.makeText(this, title + ": " + content, Toast.LENGTH_LONG).show();

            EventItem eventItem = new EventItem();

            eventSave.setItem(eventItem);

            eventSave.execute().subscribe();

        }
    }
}
