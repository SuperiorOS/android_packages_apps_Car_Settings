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
package com.android.car.settings.common;

import android.provider.Settings;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.car.settings.applications.ApplicationsSettingsFragment;
import com.android.car.settings.bluetooth.BluetoothSettingsFragment;
import com.android.car.settings.datetime.DatetimeSettingsFragment;
import com.android.car.settings.display.DisplaySettingsFragment;
import com.android.car.settings.location.LocationSettingsFragment;
import com.android.car.settings.sound.SoundSettingsFragment;
import com.android.car.settings.users.UsersListFragment;
import com.android.car.settings.wifi.WifiSettingsFragment;


/**
 * Maps an Action string to a {@link Fragment} that can handle this Action.
 */
public class FragmentResolver {
    private FragmentResolver() {}

    /**
     * Returns a {@link Fragment} that can handle the given action, returns {@code null} if no
     * {@link Fragment} that can handle this action can be found.
     */
    static @Nullable Fragment getFragmentForAction(String action) {
        switch (action) {
            case Settings.ACTION_LOCATION_SOURCE_SETTINGS:
                return new LocationSettingsFragment();

            case android.net.wifi.WifiManager.ACTION_PICK_WIFI_NETWORK:
            case Settings.ACTION_WIFI_SETTINGS:
            case Settings.ACTION_WIRELESS_SETTINGS:
                return new WifiSettingsFragment();

            case Settings.ACTION_USER_SETTINGS:
            case Settings.ACTION_SECURITY_SETTINGS:
                return new UsersListFragment();

            case Settings.ACTION_BLUETOOTH_SETTINGS:
                return new BluetoothSettingsFragment();

            case Settings.ACTION_DATE_SETTINGS:
                return new DatetimeSettingsFragment();

            case Settings.ACTION_SOUND_SETTINGS:
                return new SoundSettingsFragment();

            case Settings.ACTION_DISPLAY_SETTINGS:
            case Settings.ACTION_NIGHT_DISPLAY_SETTINGS:
                return new DisplaySettingsFragment();

            case Settings.ACTION_APPLICATION_SETTINGS:
            case Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS:
            case Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS:
                return new ApplicationsSettingsFragment();
            default:
                return null;
        }
    }
}