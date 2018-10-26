/*
 * Copyright (C) 2018 The Android Open Source Project
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

package com.android.car.settings.applications;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;

import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.NoSetupPreferenceController;

/** Business logic for the Application field in the application details page. */
public class ApplicationPreferenceController extends NoSetupPreferenceController {

    private PackageManager mPackageManager;
    private ResolveInfo mResolveInfo;

    public ApplicationPreferenceController(Context context, String preferenceKey,
            FragmentController fragmentController) {
        super(context, preferenceKey, fragmentController);
        mPackageManager = mContext.getPackageManager();
    }

    /** Sets the resolve info which is used to load the app name and icon. */
    public void setResolveInfo(ResolveInfo resolveInfo) {
        mResolveInfo = resolveInfo;
    }

    @Override
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);
        verifyResolveInfo();
        Preference preference = screen.findPreference(getPreferenceKey());
        preference.setTitle(mResolveInfo.loadLabel(mPackageManager));
        preference.setIcon(mResolveInfo.loadIcon(mPackageManager));
    }

    private void verifyResolveInfo() {
        if (mResolveInfo == null) {
            throw new IllegalStateException(
                    "ResolveInfo should be set before calling this function");
        }
    }
}
