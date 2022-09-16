package com.juliengabryelewicz.imcapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.juliengabryelewicz.imcapp.ui.theme.ImcAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImcAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel = viewModel<MainViewModel>()
                    val state = viewModel.state
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        TextField(
                            value = state.height,
                            onValueChange = {
                                viewModel.onEvent(ImcFormEvent.HeightChanged(it))
                            },
                            isError = state.heightError != null,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Taille (en cm)")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            )
                        )
                        if (state.heightError != null) {
                            Text(
                                text = state.heightError,
                                color = MaterialTheme.colors.error,
                                modifier = Modifier.align(Alignment.End)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        TextField(
                            value = state.weight,
                            onValueChange = {
                                viewModel.onEvent(ImcFormEvent.WeightChanged(it))
                            },
                            isError = state.weightError != null,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Poids (en kg)")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            )
                        )
                        if (state.weightError != null) {
                            Text(
                                text = state.weightError,
                                color = MaterialTheme.colors.error,
                                modifier = Modifier.align(Alignment.End)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                viewModel.onEvent(ImcFormEvent.Submit)
                            },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text(text = "Envoyer")
                        }
                        if (state.imcText != null) {
                            Text(
                                text = state.imcText,
                                color = MaterialTheme.colors.primary,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
            }
        }
    }
}