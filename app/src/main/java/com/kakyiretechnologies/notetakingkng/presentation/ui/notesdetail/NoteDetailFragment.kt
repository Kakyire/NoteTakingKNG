package com.kakyiretechnologies.notetakingkng.presentation.ui.notesdetail

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SeekBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.kakyiretechnologies.notetakingkng.R
import com.kakyiretechnologies.notetakingkng.databinding.FragmentNoteDetailBinding
import com.kakyiretechnologies.notetakingkng.domain.model.Note
import com.kakyiretechnologies.notetakingkng.domain.utils.GOOGLE_SIGN_IN_REQUEST_CODE
import com.kakyiretechnologies.notetakingkng.presentation.utils.extensions.navigateToNextPage
import com.kakyiretechnologies.notetakingkng.presentation.utils.extensions.showView
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 08, 2022.
 * https://github.com/kakyire
 */

private const val TAG = "NoteDetailFragment"

@AndroidEntryPoint
class NoteDetailFragment : Fragment(R.layout.fragment_note_detail) {


    private lateinit var gso: GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient

    private var mediaPlayer: MediaPlayer? = null


    private lateinit var binding: FragmentNoteDetailBinding

    private val navArgs by navArgs<NoteDetailFragmentArgs>()

    private val viewModel by viewModels<DetailsNoteViewModel>()

    private var note = Note()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNoteDetailBinding.bind(view)

        setHasOptionsMenu(true)
        observeViewModel()
        initSignIn()
        onClickListeners()
        viewModel.getNoteDetails(navArgs.noteId)
    }

    private fun onClickListeners() = with(binding) {
        btnPlay.setOnClickListener { onPlay() }

        audioProgress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                if (fromUser) mediaPlayer?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    private fun initializeSeekBar() = with(binding) {
        audioProgress.max = mediaPlayer?.duration!!

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    audioProgress.progress = mediaPlayer!!.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (e: Exception) {
                    e.printStackTrace()
                    audioProgress.progress = 0
                }
            }
        }, 0)
    }

    private fun initSignIn() {
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
    }

    private fun signIn() {
        GoogleSignIn.getLastSignedInAccount(requireContext()) ?: return

        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQUEST_CODE)
    }

    private fun observeViewModel() {
        viewModel.noteDetail.observe(viewLifecycleOwner) {
            note = it
            assignValuesToViews(it)
        }
    }


    private fun assignValuesToViews(note: Note) = with(binding) {
        note.apply {
            tvTitle.text = title
            tvContent.text = content
            Log.d(TAG, "assignValuesToViews: Filename : $voiceNote")
            audioLayout.isVisible = !voiceNote.isNullOrEmpty()
        }
    }

    private fun initAudioItems(canInit: Boolean) = with(binding) {
        if (canInit) {
            audioLayout.showView()

            mediaPlayer = MediaPlayer().apply {
                try {
                    setDataSource(note.voiceNote)
                    prepare()
                } catch (exception: IOException) {
                    exception.printStackTrace()
                }
            }
            mediaPlayer = null

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menEdit -> navigateToNextPage(
                NoteDetailFragmentDirections
                    .actionNotesDetailFragmentToAddNotesFragment(navArgs.noteId)
            )
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        signIn()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN_REQUEST_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)


        } catch (ex: ApiException) {
            ex.printStackTrace()
        }
    }

    private fun onPlay() {
        if (mediaPlayer != null) stopPlaying() else startPlaying()
    }

    private fun startPlaying() {
        setIcon()

        mediaPlayer = MediaPlayer().apply {
            try {

                setDataSource(note.voiceNote)
                setOnCompletionListener { stopPlaying() }
                prepare()
                start()
            } catch (exception: IOException) {
                exception.printStackTrace()
            }

            mediaPlayer?.let { initializeSeekBar() }
        }
    }

    private fun setIcon(isPlaying: Boolean = true) {
        val resource = if (isPlaying) R.drawable.ic_stop else R.drawable.ic_play
        binding.btnPlay.setImageResource(resource)
    }

    private fun stopPlaying() {
        setIcon(false)
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}