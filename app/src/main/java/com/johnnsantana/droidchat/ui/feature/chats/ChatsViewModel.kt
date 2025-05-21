package com.johnnsantana.droidchat.ui.feature.chats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johnnsantana.droidchat.data.repository.ChatRepository
import com.johnnsantana.droidchat.model.Chat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val chatRepository: ChatRepository
): ViewModel() {

    private val _chatListUIState = MutableStateFlow<ChatsListUIState>(ChatsListUIState.Loading)
    val chatListUIState = _chatListUIState.asStateFlow()

    init {
        getChats()
    }

    fun getChats() {
        viewModelScope.launch {
            chatRepository.getChats(
                offset = 0,
                limit = 10
            ).fold(
                onSuccess = { chats ->
                    _chatListUIState.update {
                        ChatsListUIState.Success(chats = chats)
                    }

                },
                onFailure = {
                    _chatListUIState.update {
                        ChatsListUIState.Error
                    }
                }
            )
        }
    }

    sealed interface ChatsListUIState {
        data object Loading : ChatsListUIState
        data class Success(val chats: List<Chat>) : ChatsListUIState
        data object Error: ChatsListUIState

    }
}