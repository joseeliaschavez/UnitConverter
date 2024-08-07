package com.rangerforce.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rangerforce.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun UnitConverter(modifier: Modifier = Modifier) {
    val conversionUnits = listOf("Centimeters", "Inches", "Meters", "Feet", "Kilometers", "Miles")

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Unit Converter")
//        Spacer(modifier = Modifier.padding(16.dp)) // Example of using Spacer to add space between elements
        OutlinedTextField(
            value = "",
            onValueChange = { },
            placeholder = { Text("Enter value") },
            modifier = Modifier.padding(horizontal = 16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(16.dp)
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.weight(1f)) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Select")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Select")
                }
                DropdownMenu(expanded = true, onDismissRequest = { /*TODO*/ }, modifier = Modifier.align(Alignment.TopStart)) {
                    conversionUnits.forEach { unit ->
                        // Example of using DropdownMenuItem to create a dropdown menu
                        // item for each unit in the conversionUnits list
                        DropdownMenuItem(text = { Text(text = unit) }, onClick = { /*TODO*/ })
                    }
                }
            }
            Box(contentAlignment = Alignment.Center, modifier = Modifier.weight(1f)) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Select")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Select")
                }
                DropdownMenu(expanded = true, onDismissRequest = { /*TODO*/ }, modifier = Modifier.align(Alignment.TopStart)) {
                    conversionUnits.forEach { unit ->
                        // Example of using DropdownMenuItem to create a dropdown menu
                        // item for each unit in the conversionUnits list
                        DropdownMenuItem(text = { Text(text = unit) }, onClick = { /*TODO*/ })
                    }
                }
            }

        }
        Text(text = "Result", modifier = Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterTheme {
        UnitConverter()
    }
}
