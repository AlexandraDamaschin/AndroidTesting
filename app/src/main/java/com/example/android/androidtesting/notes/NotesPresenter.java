package com.example.android.androidtesting.notes;


import android.support.annotation.NonNull;

import com.example.android.androidtesting.data.Note;
import com.example.android.androidtesting.data.NotesRepository;
import com.example.android.androidtesting.util.EspressoIdlingResource;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Listens to user actions from the UI ({@link NotesFragment}), retrieves the data and updates the
 * UI as required.
 * Tested in {@link .NotesPresenterTest}
 */

public class NotesPresenter implements NotesContract.UserActionsListener {
    private final NotesRepository mNotesRepository;
    private final NotesContract.View mNotesView;

    public NotesPresenter(
            @NonNull NotesRepository notesRepository, @NonNull NotesContract.View notesView) {
        mNotesRepository = checkNotNull(notesRepository, "notesRepository cannot be null");
        mNotesView = checkNotNull(notesView, "notesView cannot be null!");
    }

    @Override
    public void loadNotes(boolean forceUpdate) {
        //set indicator true
        mNotesView.setProgressIndicator(true);
        if (forceUpdate) {
            mNotesRepository.refreshData();
        }

        // The network request might be handled in a different thread so make sure Espresso knows
        // that the app is busy until the response is handled.
        EspressoIdlingResource.increment(); // App is busy until further notice

        mNotesRepository.getNotes(new NotesRepository.LoadNotesCallback() {
            @Override
            public void onNotesLoaded(List<Note> notes) {
                EspressoIdlingResource.decrement(); // Set app as idle.
                mNotesView.setProgressIndicator(false); //dismiss indicator
                mNotesView.showNotes(notes);    //show notes
            }
        });
    }

    @Override
    public void addNewNote() {
        mNotesView.showAddNote();
    }

    @Override
    public void openNoteDetails(@NonNull Note requestedNote) {
        checkNotNull(requestedNote, "requestedNote cannot be null!");
        mNotesView.showNoteDetailUi(requestedNote.getId());
    }
}
