package com.raghurana.singleactivityapp.utilities;

import android.os.Bundle;

import com.raghurana.singleactivityapp.constants.AppConstants;
import com.raghurana.singleactivityapp.constants.FragTag;

import java.security.InvalidParameterException;

public class BundleUtil {

    public static Bundle getBundleByFragTag(final FragTag tag) {
        final Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.FRAG_TAG_BUNDLE_KEY, tag);
        return bundle;
    }

    public static Bundle getBundleByFragTag(final FragTag tag, final String title) {
        final Bundle bundle = BundleUtil.getBundleByFragTag(tag);
        bundle.putString(AppConstants.DISPALY_TITLE_KEY, title);
        return bundle;
    }

    public static String getDisplayTitle(final Bundle bundle) {
        return bundle.getString(AppConstants.DISPALY_TITLE_KEY);
    }

    public static <T> T getSerializedValue(final Bundle bundle, final String keyName, final Class<T> returnType) {

        if (bundle == null) {
            throw new InvalidParameterException("Bundle cannot be null or empty");
        }

        if (!bundle.containsKey(keyName)) {
            throw new InvalidParameterException("Bundle did not have the required Key: " + keyName);
        }

        final Object value = bundle.getSerializable(keyName);

        if (value == null) {
            return null;
        }

        if (!returnType.isInstance(value)) {
            throw new InvalidParameterException("Value cannot be casted safely to the return type");
        }

        return returnType.cast(value);
    }

    public static void removeSerializedValues(final Bundle bundle, final String keyName) {
        if (bundle == null) {
            throw new InvalidParameterException("Bundle cannot be null or empty");
        }

        bundle.remove(keyName);
    }

}

