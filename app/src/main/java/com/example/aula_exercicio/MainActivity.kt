package com.example.aula_exercicio

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsProperties.ContentDescription
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aula_exercicio.ui.theme.Aula_ExercicioTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Aula_ExercicioTheme {
                MainScreenContent(DrawerState(initialValue = DrawerValue.Closed))
                }
            }
        }
    }

@Composable
fun MainScreenContent(drawerState: DrawerState){
    val scoper = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState( drawerState = drawerState)
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "TaskTodayAPP") },
                navigationIcon = {
                    IconButton(onClick = {
                        scoper.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Drawer Menu"
                        )
                    }
                }
            )
        },
        drawerBackgroundColor = Color.Red,
        drawerGesturesEnabled = drawerState.isOpen,
        drawerContent = {
                        Box(
                            modifier = Modifier
                                .background(Color.Magenta)
                                .height(16.dp)
                        ){
                            Text(text = "Opções")
                        }
            Column() {
                Text(text = "Opção de Menu 1")
                Text(text = "Opção de Menu 2")
                Text(text = "Opção de Menu 3")


            }
        },

    content = {
            paddingValues -> Log.i("paddinValues", "$paddingValues")
        Column(
            modifier = Modifier
                .background(Color.Yellow)
                .fillMaxSize()
        ) {
            val calendar = Calendar.getInstance()
            listOf<Tarefa>()
            MytaskWidgetList()
            MySearchField(modificador = Modifier.fillMaxWidth())
            MyTaskWidget(
                modificador = Modifier.fillMaxWidth(),
                taksName = "Preparar Aula LazyList/LazyColum",
                taksDetails = "É bem melhor usar lazilist ao inves de colum",
                deadEndDate = Date()
            )
            MyTaskWidget(
                modificador = Modifier.fillMaxWidth(),
                taksName = "Prova Matemática",
                taksDetails = "Estudar Calculo capito 1 e 2",
                deadEndDate = Date()
            )
            Text("Task3")
            Text("Task4")

        }
    },
bottomBar = {
    BottomAppBar(
        content = { Text( "asdf")}
    )

    }
    )
}


@Composable
fun MytaskWidgetList(listaDeTarefas:List<Tarefa>){
    listaDeTarefas.forEach(action = { Log.i("#####%%%%%#####", "${it.}")})

}

@Composable
fun MySearchField(modificador:Modifier){
    TextField(value = "", 
        onValueChange = {},
        modifier = modificador, 
        placeholder = { Text(text = "Pesquisar Tarefa")},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Searsh Icon")
        }
        )
}

@Composable
fun MyTaskWidget(
    modificador: Modifier,
    taksName: String,
    taksDetails:String,
    deadEndDate: Date
    ){
    val DateFormatter = SimpleDateFormat("EEE, MMM dd, YYYY", Locale.getDefault())
    Row(modifier = modificador) {
        Column() {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Icons of a pendent task"
            )
            Text(
                text =  DateFormatter.format(deadEndDate),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
        }


        Column(modifier = modificador
            .border(width = 1.dp, color = Color.Black)
            .padding(3.dp)
        ) {
            Text(
                text =  taksName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = taksDetails,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            )
    }

   }
    Spacer(modifier = Modifier.height(16.dp))
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreenContent(DrawerState(initialValue = DrawerValue.Closed))
}