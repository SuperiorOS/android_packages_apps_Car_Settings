/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.car.settings.common;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.preference.PreferenceViewHolder;

import com.android.car.settings.R;
import com.android.car.ui.preference.CarUiPreference;

/**
 * A Preference that allows a summary text to be on the different side of the title.
 */
public class SummaryPreference extends CarUiPreference {

    public SummaryPreference(Context context, AttributeSet attrs,
            int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public SummaryPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public SummaryPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SummaryPreference(Context context) {
        super(context);
        init(context, /* attrs= */ null);
    }

    private void init(Context context, AttributeSet attrs) {
        setLayoutResource(R.layout.summary_preference);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        View itemView = holder.itemView;
        setTextView(itemView, R.id.title, getTitle());
        setTextView(itemView, R.id.summary, getSummary());
    }

    private void setTextView(View view, @IdRes int id, CharSequence text) {
        TextView textView = view.findViewById(id);
        if (textView != null) {
            textView.setText(text);
            textView.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        }
    }
}
