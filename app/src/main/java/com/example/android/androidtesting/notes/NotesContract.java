package com.example.android.androidtesting.notes;

import android.support.annotation.NonNull;

import com.example.android.androidtesting.data.Note;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */

public interface NotesContract {

    interface View {

        void setProgressIndicator(boolean active);

        void showNotes(List<Note> notes);

        void showAddNote();

        void showNoteDetailUi(String noteId);
    }

    interface UserActionsListener {

        void loadNotes(boolean forceUpdate);

        void addNewNote();

        void openNoteDetails(@NonNull Note requestedNote);
    }
}
