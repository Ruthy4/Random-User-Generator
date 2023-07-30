package com.example.randomusergenerator.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.example.randomusergenerator.ui.resources.Dimensions.space_half
import com.example.randomusergenerator.ui.resources.Dimensions.space_x1_25
import com.example.randomusergenerator.ui.resources.FontSize.fontSize18
import com.example.randomusergenerator.ui.theme.blueLight

@Composable
fun ExpandableSearchToolbar(
    title: String,
    onSearchQueryChange: (String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    TopAppBar(
        title = { Text(text = title) },
        elevation = space_half,
        contentColor = Color.White,
        backgroundColor = blueLight,
        actions = {
            if (isExpanded) {
                SearchTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        onSearchQueryChange(it)
                    },
                    onClose = {
                        isExpanded = false
                        searchQuery = ""
                    }
                )
            } else {
                ExpandableSearchIcon(onClick = { isExpanded = true })
            }
        }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchTextField(
    value: String,
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    onValueChange: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    BasicTextField(
        modifier = modifier.padding(space_x1_25)
            .focusRequester(focusRequester)
            .onFocusChanged {
                if (it.isFocused) {
                    keyboardController?.show()
                }
            },
        value = value,
        textStyle = TextStyle(color = Color.White, fontSize = fontSize18),
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search,
            capitalization = KeyboardCapitalization.None
        ),
        keyboardActions = KeyboardActions(
            onSearch = { TODO() }
        ),
        visualTransformation = VisualTransformation.None,
        cursorBrush = SolidColor(Color.White),
        decorationBox = { innerTextField ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                innerTextField()
                SearchCloseButton(onClick = onClose)
            }
        }
    )
}
