package com.rangerforce.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rangerforce.unitconverter.ui.theme.UnitConverterTheme
import java.util.Locale

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
    val (fromUnit, setFromUnit) = remember { mutableStateOf(conversionUnits[0]) }
    val (toUnit, setToUnit) = remember { mutableStateOf(conversionUnits[1]) }
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("Result") }

    val headerTextStyle = TextStyle(
        fontSize = 24.sp
    )

    fun getInputValue(): Double {
        return inputValue.toDoubleOrNull() ?: 0.0
    }

    fun handleFromSelectionChange(unit: String) {
        setFromUnit(unit)
        outputValue = convert(getInputValue(), unit, toUnit)
    }

    fun handleToSelectionChange(unit: String) {
        setToUnit(unit)
        outputValue = convert(getInputValue(), fromUnit, unit)
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Unit Converter",
//            style = MaterialTheme.typography.headlineMedium,
            style = headerTextStyle, // Example of using a custom text style, but you should use the MaterialTheme.typography styles
            modifier = Modifier.padding(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                value = inputValue,
                onValueChange = {
                    inputValue = it
                    outputValue = convert(getInputValue(), fromUnit, toUnit)
                },
                placeholder = { Text("Enter value") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Force Android to show a numeric keyboard
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1.5f))
            ComboBox(options = conversionUnits, fromUnit, ::handleFromSelectionChange, modifier = Modifier.weight(1f))
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                value = outputValue,
                onValueChange = {},
                placeholder = { Text("Result") },
                readOnly = true,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1.5f))
            ComboBox(options = conversionUnits, toUnit, ::handleToSelectionChange, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun ComboBox(options: List<String>,
        selectedValue: String,
        onSelectionChange: (String) -> Unit,
        modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        Button(onClick = { expanded = !expanded }) {
            Text(text = selectedValue)
            Icon(Icons.Default.ArrowDropDown, contentDescription = "Select")
        }
        DropdownMenu(expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.align(Alignment.TopStart)) {
            options.forEach { option ->
                // Example of using DropdownMenuItem to create a dropdown menu
                // item for each option in the conversionUnits list
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        onSelectionChange(option)
                        expanded = false
                    })
            }
        }
    }
}

fun convert(inputValue: Double, fromUnit: String, toUnit: String): String {
    // Conversion logic
    // First convert to base unit (meter)
    val baseValue = when (fromUnit) {
        "Centimeters" -> inputValue / 100
        "Inches" -> inputValue / 39.3701
        "Feet" -> inputValue / 3.28084
        "Kilometers" -> inputValue * 1000
        "Miles" -> inputValue * 1609.34
        else -> inputValue
    }
    // Then convert to target unit
    val convertedValue = when (toUnit) {
        "Centimeters" -> baseValue * 100
        "Inches" -> baseValue * 39.3701
        "Feet" -> baseValue * 3.28084
        "Kilometers" -> baseValue / 1000
        "Miles" -> baseValue / 1609.34
        else -> baseValue
    }
    return String.format(Locale.getDefault(), "%.4f", convertedValue)
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterTheme {
        UnitConverter()
    }
}
