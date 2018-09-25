package com.example.android.androidtesting.addnote;

import com.example.android.androidtesting.data.Note;
import com.example.android.androidtesting.data.NotesRepository;
import com.example.android.androidtesting.util.ImageFile;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the implementation of {@link AddNotePresenter}.
 */
public class AddNotePresenterTest {
    //mocks variables
    @Mock
    private NotesRepository mNotesRepository;

    @Mock
    private ImageFile mImageFile;

    @Mock
    private AddNoteContract.View mAddNoteView;

    private AddNotePresenter mAddNotesPresenter;

    @Before
    public void setupAddNotePresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        mAddNotesPresenter = new AddNotePresenter(mNotesRepository, mAddNoteView, mImageFile);
    }

    // Save note to repo success UI
    @Test
    public void saveNoteToRepository_showsSuccessMessageUi() {
        // When the presenter is asked to save a note
        mAddNotesPresenter.saveNote("New Note Title", "Some Note Description");

        // Then a note is,
        verify(mNotesRepository).saveNote(any(Note.class)); // saved to the model
        verify(mAddNoteView).showNotesList(); // shown in the UI
    }

    // Save note error UI
    @Test
    public void saveNote_emptyNoteShowsErrorUi() {
        // When the presenter is asked to save an empty note
        mAddNotesPresenter.saveNote("", "");

        // Then an empty not error is shown in the UI
        verify(mAddNoteView).showEmptyNoteError();
    }

    // Open camera and take picture
    @Test
    public void takePicture_CreatesFileAndOpensCamera() throws IOException {
        // When the presenter is asked to take an image
        mAddNotesPresenter.takePicture();

        // Then an image file is created snd camera is opened
        verify(mImageFile).create(anyString(), anyString());
        verify(mImageFile).getPath();
        verify(mAddNoteView).openCamera(anyString());
    }

    // Save image and update UI
    @Test
    public void imageAvailable_SavesImageAndUpdatesUiWithThumbnail() {
        // Given an a stubbed image file
        String imageUrl = "path/to/file";
        when(mImageFile.exists()).thenReturn(true);
        when(mImageFile.getPath()).thenReturn(imageUrl);

        // When an image is made available to the presenter
        mAddNotesPresenter.imageAvailable();

        // Then the preview image of the stubbed image is shown in the UI
        verify(mAddNoteView).showImagePreview(contains(imageUrl));
    }

    // Image does not exists error UI
    @Test
    public void imageAvailable_FileDoesNotExistShowsErrorUi() {
        // Given the image file does not exist
        when(mImageFile.exists()).thenReturn(false);

        // When an image is made available to the presenter
        mAddNotesPresenter.imageAvailable();

        // Then an error is shown in the UI and the image file is deleted
        verify(mAddNoteView).showImageError();
        verify(mImageFile).delete();
    }

    // No image available, error UI
    @Test
    public void noImageAvailable_ShowsErrorUi() {
        // When the presenter is notified that image capturing failed
        mAddNotesPresenter.imageCaptureFailed();

        // Then an error is shown in the UI and the image file is deleted
        verify(mAddNoteView).showImageError();
        verify(mImageFile).delete();
    }

}
