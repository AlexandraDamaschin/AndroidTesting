package com.example.android.androidtesting.data;

import android.support.annotation.Nullable;

import com.google.common.base.Objects;

import java.util.UUID;

/**
 * Model class for a Note.
 */

public class Note {
    private final String mId;
    @Nullable
    private final String mTitle;
    @Nullable
    private final String mDescription;
    @Nullable
    private final String mImageUrl;

    public Note(@Nullable String title, @Nullable String description) {
        this(title, description, null);
    }

    //constructor
    public Note(@Nullable String title, @Nullable String description, @Nullable String imageUrl) {
        mId = UUID.randomUUID().toString();
        mTitle = title;
        mDescription = description;
        mImageUrl = imageUrl;
    }

    //get properties of a note
    public String getId() {
        return mId;
    }

    @Nullable
    public String getTitle() {
        return mTitle;
    }

    @Nullable
    public String getDescription() {
        return mDescription;
    }

    @Nullable
    public String getImageUrl() {
        return mImageUrl;
    }

    //check if title or description is null or empty
    public boolean isEmtpy() {
        return (mTitle == null || "".equals(mTitle)) &&
                (mDescription == null || "".equals(mDescription));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Note note = (Note) o;

        return Objects.equal(mId, note.mId) &&
                Objects.equal(mTitle, note.mTitle) &&
                Objects.equal(mDescription, note.mDescription) &&
                Objects.equal(mImageUrl, note.mImageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mId, mTitle, mDescription, mImageUrl);
    }

}
