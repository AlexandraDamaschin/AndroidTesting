package com.example.android.androidtesting;

import com.example.android.androidtesting.data.*;
import com.example.android.androidtesting.data.NoteRepositories;
import com.example.android.androidtesting.data.NotesRepository;
import com.example.android.androidtesting.util.*;
import com.example.android.androidtesting.util.ImageFile;

/**
 * Enables injection of mock implementations for {@link ImageFile} and
 * {@link NotesRepository} at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
public class Injection {

    public static ImageFile provideImageFile() {
        return new FakeImageFileImpl();
    }

    public static NotesRepository provideNotesRepository() {
        return NoteRepositories.getInMemoryRepoInstance(new FakeNotesServiceApiImpl());
    }
}
