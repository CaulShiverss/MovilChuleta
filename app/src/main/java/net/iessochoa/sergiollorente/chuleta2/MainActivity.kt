package net.iessochoa.sergiollorente.chuleta2

import android.R.attr.enabled
import android.os.Bundle
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import net.iessochoa.sergiollorente.chuleta2.ui.theme.Chuleta2Theme
import androidx.compose.foundation.lazy.items


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chuleta2Theme {
                //CafeteraLayout()
                //App("Chuleta Examen")
                CartasTest()
            }
        }
    }
}

data class Option( // 1
    var checked: Boolean,
    var onCheckedChange: (Boolean) -> Unit = {},
    val label: String,
    var enabled: Boolean = true,
    //val image: Painter
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(appName: String, modifier: Modifier = Modifier) {

    // Estado CheckBox
    var checked by remember { mutableStateOf(true) }

    // Lista de Opciones para CheckBoxList

    var optionsStr: List<String> = listOf("Azucar", "Cafeina", "Matcha")

    val options: List<Option> = optionsStr.map { ingrediente ->
        val checked = remember { mutableStateOf(false) }
        Option(
            checked = checked.value,
            onCheckedChange = { checked.value = it },
            label = ingrediente,
            // image = painterResource(imageMap[ingrediente] ?: R.drawable.istockphoto_1147544807_612x612)
        )
    }

    // Estado DropDown
    val opciones = listOf("Manzana", "Plátano", "Fresa", "Naranja")
    var (seleccion, onValueChanged) = remember { mutableStateOf(opciones[0]) } // Opción por defecto

    // Estado RatingBar
    var rating by remember { mutableIntStateOf(5) }

    // Estado TextField
    var name by remember { mutableStateOf("Carlos") }

    // Estado RadioButtonRow
    var selectedOption by rememberSaveable { mutableStateOf("") }

    // Estado Switch
    var isOn by remember { mutableStateOf(true) }

    // Variables Snackbar
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    // Lista para la lazyColumn
    val elementos = listOf<String>("Texto 1", "Hola", "Mañana", "bajjjgsf", "ejemplo", "abajo", "renderizado")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        // [+] --------------------------- TopBar --------------------------- [+]
        topBar = {
            TopAppBar(
                title = {Text(text = stringResource(R.string.app_name))}
            )
        },
        // [+] --------------------------- FAB --------------------------- [+]
        floatingActionButton = {FloatingActionButton(onClick = { scope.launch { snackBarHostState.showSnackbar("Sergio Llorente González: " + seleccion, duration = SnackbarDuration.Short) } }, shape = CutCornerShape(10.dp)) {
            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorito")
        }},
        // [+] --------------------------- SnackBar --------------------------- [+]
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
    ) { innerPadding ->
        Column(
            modifier = modifier.fillMaxSize().padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn(modifier = modifier.height(100.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) { items(elementos) {
                elemento -> Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Text(
                    text = elemento,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            } }

            // [+] --------------------------- LLamada CheckBox --------------------------- [+]
            CheckboxMinimalExample(
                checked = checked,
                onCheckedChange = {checked = it}
            )

            // [+] --------------------------- LLamada Dropdown --------------------------- [+]
            DynamicSelectTextField(
                selectedValue = seleccion,
                options = opciones,
                label = "Fruta favorita",
                onValueChangedEvent = onValueChanged // Actualiza el estado al seleccionar
            )
            Text("Has seleccionado: $seleccion") // Muestra la selección actual

            // [+] --------------------------- LLamada RatingBar --------------------------- [+]
            RatingBar(
                currentRating = rating,
                onRatingChanged = { rating = it },
            )

            // [+] --------------------------- LLamada TextField --------------------------- [+]
            CustomTextField(
                value = name,
                onValueChange = {name = it},
                label = "Nombre: ",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )

            Text("Contenido: " + name)

            // [+] --------------------------- LLamada RadioButtons --------------------------- [+]
            RowRadioButtonCompose(
                listaOpciones = listOf("Opcion1", "opcion2", "Opcion3"),
                opcionSeleccionada = selectedOption,
                onOptionSelected = { selectedOption = it }
            )

            // [+] --------------------------- LLamada Switch --------------------------- [+]
            SimpleSwitch(checked = isOn, onCheckedChange = {isOn = it})

            if (isOn) Text("Encendido") else Text("Apagado")

            // [+] --------------------------- LLamada Toast / Button --------------------------- [+]
            ToastButton(
                toastText = selectedOption,
                buttonText = "Toast"
            )

            // [+] --------------------------- LLamada CheckBoxList --------------------------- [+]
            CheckBoxList(options, "Ingredientes")

        }

    }



}

// [+] --------------------------- Switch --------------------------- [+]
@Composable
fun SimpleSwitch(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange
    )
}

// [+] --------------------------- TextField --------------------------- [+]
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text(label)},
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

// [+] --------------------------- CheckBoxSimple --------------------------- [+]
@Composable
fun CheckboxMinimalExample(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            "Minimal checkbox"
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }

    Text(
        if (checked) "Checkbox is checked" else "Checkbox is unchecked"
    )
}

// [+] --------------------------- LabelledCheckBox --------------------------- [+]
@Composable
fun LabelledCheckBox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors()
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.height(48.dp)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            colors = colors
        )
        Spacer(Modifier.width(32.dp))
        Text(label)
    }
}

// [+] --------------------------- CheckBoxList --------------------------- [+]
@Composable
fun CheckBoxList(options: List<Option>, title: String) {

    Column {

        Text(text = title, textAlign = TextAlign.Center)
        Spacer(Modifier.size(16.dp))
        options.forEach { option ->
            LabelledCheckBox(
                checked = option.checked,
                onCheckedChange = option.onCheckedChange,
                label = option.label,
                enabled = option.enabled
            )
        }

    }

}

// [+] --------------------------- Dropdown --------------------------- [+]
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicSelectTextField(
    selectedValue: String,
    options: List<String>,
    label: String,
    onValueChangedEvent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true, // El campo de texto no se edita directamente
            value = selectedValue,
            onValueChange = {}, // Vacío porque es readOnly
            label = { Text(text = label) },
            trailingIcon = {
                // Icono que indica si el menú está desplegado o no
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = OutlinedTextFieldDefaults.colors(), // Colores por defecto
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryEditable, true) // Ancla el menú al TextField
                .fillMaxWidth()
        )
        // El menú desplegable en sí
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false } // Acción al cerrar el menú
        ) {
            // Itera sobre las opciones para crear cada item del menú
            options.forEach { option: String ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        expanded = false // Cierra el menú
                        onValueChangedEvent(option) // Llama al callback con la opción seleccionada
                    }
                )
            }
        }
    }
}

// [+] --------------------------- RadioButton --------------------------- [+]
@Composable
fun RowRadioButtonCompose(
    listaOpciones: List<String>,
    opcionSeleccionada: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .selectableGroup()
            .padding(all = 30.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        listaOpciones.forEach { operation ->
            Row(
                modifier = Modifier
                    .weight(1f)
                    .selectable(
                        selected = opcionSeleccionada == operation,
                        onClick = { onOptionSelected(operation) },
                        role = Role.RadioButton
                    )
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = opcionSeleccionada == operation,
                    onClick = null
                )
                Text(
                    text = operation,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

// [+] --------------------------- Button / Toast --------------------------- [+]
@Composable
fun ToastButton(
    toastText: String,
    buttonText: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Button(
        onClick = { Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show() },
        modifier = modifier.width(300.dp),
        enabled = true,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF6200EE),
            contentColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(5.dp),
        border = BorderStroke(1.dp, Color.White)
    ) {
        Text(buttonText)
    }
}


// [+] --------------------------- RatingBar --------------------------- [+]
@Composable
fun RatingBar(
    maxRating: Int = 5,
    currentRating: Int,
    onRatingChanged: (Int) -> Unit,
    iconSelect: ImageVector = Icons.Filled.Star,
    iconUnSelect: ImageVector = Icons.Outlined.Star,
    modifier: Modifier = Modifier,
    color: Color = Color.Red
) {
    // 1. Aplica el modifier del parámetro al componente raíz (el Row)
    Row(modifier = modifier) {
        for (i in 1..maxRating) {
            Icon(
                imageVector = if (i <= currentRating) iconSelect else iconUnSelect,
                contentDescription = "Star $i",
                // 2. Crea un Modifier nuevo y local para cada Icon
                modifier = Modifier.clickable {
                    onRatingChanged(
                        if (i == 1 && currentRating == 1) 0 else i
                    )
                },
                tint = if (i <= currentRating) color else Color.Unspecified
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChuletaPreview() {
    Chuleta2Theme {
        App("Chuleta Preview")
    }
}