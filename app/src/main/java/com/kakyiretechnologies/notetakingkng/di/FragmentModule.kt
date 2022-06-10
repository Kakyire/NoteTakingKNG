package com.kakyiretechnologies.notetakingkng.di

import androidx.fragment.app.Fragment
import com.kakyiretechnologies.notetakingkng.presentation.utils.OnRecyclerViewClickListener
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 10, 2022.
 * https://github.com/kakyire
 */
@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {

    @Provides
    fun provideClickListener(fragment:Fragment)= fragment as OnRecyclerViewClickListener
}