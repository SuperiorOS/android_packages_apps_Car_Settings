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

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;

import androidx.annotation.CallSuper;
import androidx.preference.Preference;

/**
 * Controller that used to update the summary of a preference.
 */
public class UpdateSummaryPreferenceController extends PreferenceController<Preference> {

    private String mSummary;

    public UpdateSummaryPreferenceController(Context context, String preferenceKey,
            FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override
    @CallSuper
    protected void updateState(Preference preference) {
        preference.setSummary(mSummary);
    }

    /** Sets summary text */
    public void setSummary(String summary) {
        mSummary = summary;
        refreshUi();
    }
}
