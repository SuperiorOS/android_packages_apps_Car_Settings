/*
 * Copyright (C) 2020 The Android Open Source Project
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

package com.android.car.settings.common.rotary;

import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.NumberPicker;

/** A rotation handler for {@link NumberPicker} instances. */
public class NumberPickerRotationHandler implements View.OnGenericMotionListener {
    @Override
    public boolean onGenericMotion(View v, MotionEvent event) {
        View focusedView = v.findFocus();
        if (focusedView instanceof NumberPicker) {
            NumberPicker numberPicker = (NumberPicker) focusedView;
            float scroll = event.getAxisValue(MotionEvent.AXIS_SCROLL);
            // NumberPicker#setValue(int) doesn't notify change listener, so perform an
            // accessibility action instead.
            int scrollValue = Math.round(scroll);
            int scrollDirection = scrollValue > 0 ? AccessibilityNodeInfo.ACTION_SCROLL_FORWARD
                    : AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD;
            for (int i = 0; i < Math.abs(scrollValue); i++) {
                numberPicker.getAccessibilityNodeProvider().performAction(View.NO_ID,
                        scrollDirection, /* arguments= */ null);
            }
            return true;
        }
        return false;
    }
}
