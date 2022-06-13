package com.kakyiretechnologies.notetakingkng.presentation.ui.addnotes

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kakyiretechnologies.notetakingkng.R
import com.kakyiretechnologies.notetakingkng.databinding.FragmentAddEditNoteBinding
import com.kakyiretechnologies.notetakingkng.domain.model.Note
import com.kakyiretechnologies.notetakingkng.domain.utils.RECORD_AUDIO_PERMISSION_REQUEST
import com.kakyiretechnologies.notetakingkng.presentation.ui.notesdetail.DetailsNoteViewModel
import com.kakyiretechnologies.notetakingkng.presentation.utils.extensions.*
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 08, 2022.
 * https://github.com/kakyire
 */
private const val TAG = "AddEditNoteFragment"

@AndroidEntryPoint
class AddEditNoteFragment : Fragment(R.layout.fragment_add_edit_note) {

    private lateinit var binding: FragmentAddEditNoteBinding

    private val navArgs by navArgs<AddEditNoteFragmentArgs>()

    private val addEditNoteViewModel by viewModels<AddEditNoteViewModel>()
    private val detailsNoteViewModel by viewModels<DetailsNoteViewModel>()

    private var note = Note()
    private var noteTitle = ""
    private var noteContent = ""


    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    private var fileName = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddEditNoteBinding.bind(view)

        setHasOptionsMenu(true)
        navArgs.noteId?.let {
            detailsNoteViewModel.getNoteDetails(it)
        }

        observeViewModel()
        onClickListeners()
        onTextChangeListeners()

    }

    private fun onClickListeners() = with(binding) {

        btnRecorder.setOnClickListener { checkPermissions() }
        btnPlay.setOnClickListener { onPlay() }

    }

    private fun onRecord() {


        if (mediaRecorder == null) {
            Log.d(TAG, "onRecord: Start recording")
            startRecording()
        } else {
            Log.d(TAG, "onRecord: Stop recording")
            stopRecording()
        }

    }

    private fun onTextChangeListeners() = with(binding) {
        edtTitle.addTextChangedListener {
            if (it != null) {
                btnRecorder.isVisible = it.isNotEmpty()
                divider.isVisible = it.isNotEmpty()

            }
        }
    }

    private fun startRecording() = with(binding) {
        btnRecorder.setImageResource(R.drawable.ic_stop)
        getValues()
        val rootPath = File(requireContext().filesDir, getString(R.string.app_name))
        if (!rootPath.exists()) rootPath.mkdirs()

        val file = File(rootPath, "$noteTitle.3gp")
        fileName = "$file"

        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(fileName)
            try {
                prepare()
                start()
            } catch (exception: IOException) {
                exception.printStackTrace()
            }

        }


    }

    private fun onPlay() {
        if (mediaPlayer != null) stopPlaying() else startPlaying()
    }

    private fun setIcon(isPlaying: Boolean = true) {
        val resource = if (isPlaying) R.drawable.ic_stop else R.drawable.ic_play
        binding.btnPlay.setImageResource(resource)
    }

    private fun startPlaying() {
        setIcon()
        mediaPlayer = MediaPlayer().apply {
            try {
                Log.d(TAG, "startPlaying: Filename = $fileName")
                setDataSource(fileName)
                prepare()
                setOnCompletionListener { stopPlaying() }
                start()
            } catch (exception: IOException) {
                exception.printStackTrace()
            }
        }
    }

    private fun stopPlaying() {
        setIcon(false)
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun releaseAll() {
        mediaPlayer?.release()
        mediaPlayer = null
        mediaRecorder?.release()
        mediaRecorder = null
    }

    private fun stopRecording() = with(binding) {
        btnRecorder.apply {
            setImageResource(R.drawable.ic_record_audio)
            hideView()
        }
        btnPlay.showView()
        mediaRecorder?.apply {
            try {
                stop()
                release()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
        mediaRecorder = null
    }

    private fun observeViewModel() {
        observeLiveData(detailsNoteViewModel.noteDetail) {
            note = it
            fileName = it.voiceNote
            preAssignValuesToViews(it)
        }
    }

    private fun preAssignValuesToViews(note: Note) = with(binding) {
        note.apply {
            edtContent.setText(content)
            edtTitle.setText(title)
        }
    }

    private fun getValues() = with(binding) {
        noteTitle = edtTitle.text.toString()
        noteContent = edtContent.text.toString()
    }

    private fun saveNote() {
        getValues()

        if (noteTitle.isEmpty()) {
            showMessage(getString(R.string.note_title_is_required))
            binding.edtTitle.requestFocus()
            return
        }
        Log.d(TAG, "saveNote: Filename :$fileName")
        note.apply {
            title = noteTitle
            content = noteContent
            modifiedOn = System.currentTimeMillis()
            voiceNote = fileName
        }

        if (navArgs.noteId != null) {
            addEditNoteViewModel.updateNote(note)
            navigateToNextPage(
                AddEditNoteFragmentDirections
                    .actionAddNotesFragmentToNotesDetailFragment(note.id)
            )
        } else {
            addEditNoteViewModel.addNote(note)
            navigateToNextPage(
                AddEditNoteFragmentDirections
                    .actionAddNotesFragmentToNotesFragment()
            )
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_edit_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menSave -> saveNote()

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RECORD_AUDIO_PERMISSION_REQUEST
            && grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            onRecord()
        }

    }

    private fun checkPermissions() {
        if (requireContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
            requireContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
            requireContext().checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
        ) {
            onRecord()
        } else {

            requestPermissions(
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO
                ), RECORD_AUDIO_PERMISSION_REQUEST
            )
        }
    }

    override fun onStop() {
        super.onStop()
        releaseAll()
    }
}