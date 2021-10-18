/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.hilt.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.hilt.data.LoggerLocalDataSource
import com.example.android.hilt.databinding.FragmentButtonsBinding
import com.example.android.hilt.navigator.AppNavigator
import com.example.android.hilt.navigator.Screens
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Fragment that displays buttons whose interactions are recorded.
 */
@AndroidEntryPoint
class ButtonsFragment : Fragment() {

    private var _binding: FragmentButtonsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var logger: LoggerLocalDataSource
    @Inject
    lateinit var navigator: AppNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentButtonsBinding.inflate(inflater, container, false)
            .apply { _binding = this }
            .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button1.setOnClickListener {
            logger.addLog("Interaction with 'Button 1'")
        }

        binding.button2.setOnClickListener {
            logger.addLog("Interaction with 'Button 2'")
        }

        binding.button3.setOnClickListener {
            logger.addLog("Interaction with 'Button 3'")
        }

        binding.allLogs.setOnClickListener {
            navigator.navigateTo(Screens.LOGS)
        }

        binding.deleteLogs.setOnClickListener {
            logger.removeLogs()
        }
    }

    /**
     * Called when the view previously created by [.onCreateView] has
     * been detached from the fragment.  The next time the fragment needs
     * to be displayed, a new view will be created.  This is called
     * after [.onStop] and before [.onDestroy].  It is called
     * *regardless* of whether [.onCreateView] returned a
     * non-null view.  Internally it is called after the view's state has
     * been saved but before it has been removed from its parent.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
