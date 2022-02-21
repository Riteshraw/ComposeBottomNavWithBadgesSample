package com.example.composebottomnavwithbadges.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = viewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "ChatScreen")
        /*var listState: LazyListState =
            if (!viewModel.isListStateInitialised()) {
                rememberLazyListState()
            } else {
                viewModel.listState
            }*/

        var listState = rememberLazyListState()
        /*listState = LazyListState(
            5,0
        )*/

        if (viewModel.firstItemIndex != null) {
            viewModel.firstItemIndex
            viewModel.firstItemScrollOffset
        }

        Text(text = "First index : ${listState.firstVisibleItemIndex}")
        Text(text = "First visible offset : ${listState.firstVisibleItemScrollOffset}")
        Text(text = "Scroll progress : ${listState.isScrollInProgress}")
        viewModel.saveState(listState.firstVisibleItemIndex, listState.firstVisibleItemScrollOffset)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(top = 8.dp)
        ) {
            items(items = viewModel.listResponse) { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}
