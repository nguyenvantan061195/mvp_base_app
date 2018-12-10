package com.app.base.common.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.regex.Pattern;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 10:56 AM
 */
public class Preconditions {
    private static final Pattern URL_PATTERN = Pattern.compile("[a-zA-Z_-]+://\\S+");

    private Preconditions() {
        throw new AssertionError("Preconditions: Don't create instances of this object");
    }

    /**
     * Checks if the reference is not null.
     *
     * @param reference an object reference
     * @return the non-null reference
     * @throws NullPointerException if {@code reference} is null
     */
    @NonNull
    public static <T> T get(@Nullable final T reference) {
        if (reference == null) {
            throw new NullPointerException("Assertion for a nonnull object failed.");
        }
        return reference;
    }

    /**
     * Checks if the reference is not null.
     *
     * @param reference object reference
     * @return non-null reference
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(final T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    /**
     * Checks if the reference is not null.
     *
     * @param reference    object reference
     * @param errorMessage message used if the check fails
     * @return non-null reference
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(final T reference, @NonNull final String errorMessage) {
        if (reference == null) {
            throw new NullPointerException(get(errorMessage));
        }
        return reference;
    }

    private static boolean isUrl(String url) {
        return URL_PATTERN.matcher(url).matches();
    }
}
