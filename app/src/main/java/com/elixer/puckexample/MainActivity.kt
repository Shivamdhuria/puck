package com.elixer.puckexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toOffset
import androidx.compose.ui.unit.toSize
import com.elixer.puck.Circle
import com.elixer.puck.Utils.Behaviour.*
import com.elixer.puck.Utils.Configuration
import com.elixer.puck.Utils.Configuration.Corners
import com.elixer.puck.puck
import com.elixer.puckexample.ui.theme.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val parentSize = remember { mutableStateOf(Size.Zero) } // this keeps parents size
            PuckTheme {
                val circle = Circle(800f, 1800f, 500f)
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Box(modifier = Modifier
                        .onGloballyPositioned { coordinates ->
                            parentSize.value = coordinates.size.toSize()
                        }
                        .drawBehind {
                            drawCircle(
                                color = Color.Magenta,
                                center = Offset(x = circle.xCenter, y = circle.yCenter),
                                radius = circle.radius,
                                style = Stroke(3f)
                            )

                            drawCircle(
                                color = Color.Gray,
                                center = Offset(x = circle.xCenter, y = circle.yCenter),
                                radius = 10f,
                            )
                        }
                        .fillMaxSize()
                    ) {
                        Heading()
                   /*
                   FreeForm
                    */
                        //Get this from shared pref
                        var latestOffset: Offset

                        Card(
                            modifier = Modifier
                                .puck(parentSize,
                                    behaviour = FreeForm,
                                    isPointsTowardsCenter = true,
                                    offset = Offset(200f,500f),
                                onOffsetChanged = {intOffset->
                                    latestOffset = intOffset.toOffset()
                                    Log.d("offset", latestOffset.toString())
                                } ), backgroundColor = PINK200
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(
                                    painterResource(R.drawable.zoid),
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .height(70.dp)
                                        .width(70.dp)
                                )
                                Text(text = "Card FreeForm", fontSize = 24.sp, modifier = Modifier.padding(10.dp))
                            }

                        }

                        Button(onClick = { }, modifier = Modifier.puck(parentSize, behaviour = FreeForm, animationDuration = 700)) {
                            Text(text = "Button Composable", fontSize = 24.sp)
                        }

                        FloatingActionButton(
                            onClick = {},
                            modifier = Modifier
                                .puck(parentSize, behaviour = FreeForm)
                                .width(90.dp)
                                .height(90.dp),
                            backgroundColor = WHITE200
                        ) {
                            Image(
                                painterResource(R.drawable.pencil),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(50.dp)
                                    .rotate(135f)
                            )
                        }



                        //Sticky
                        Card(
                            modifier = Modifier
                                .puck(parentSize, behaviour = Sticky(Configuration.HorizontalEdges)), backgroundColor = PINK200
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(
                                    painterResource(R.drawable.batman),
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .height(70.dp)
                                        .width(70.dp)
                                )
                                Text(text = "Only Horztle Edges", fontSize = 24.sp, modifier = Modifier.padding(10.dp))
                            }
                        }

                        Button(onClick = { }, modifier = Modifier.puck(parentSize, behaviour = Sticky(Configuration.Edges), animationDuration = 700)) {
                            Text(text = "Sticky Edges", fontSize = 24.sp)
                        }

                        FloatingActionButton(
                            onClick = {},
                            modifier = Modifier
                                .puck(parentSize, behaviour = Sticky(Corners), animationDuration = 500)
                                .width(90.dp)
                                .height(90.dp),
                            backgroundColor = WHITE200
                        ) {
                            Image(
                                painterResource(R.drawable.pencil),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(50.dp)
                                    .rotate(135f)
                            )
                        }



                            //Gravity
                        FloatingActionButton(onClick = {}, modifier = Modifier
                            .puck(parentSize, behaviour = Gravity(circle))) {
                            Icon(Icons.Filled.ThumbUp, "", tint = Color.White, modifier = Modifier.size(40.dp))
                        }
                        FloatingActionButton(
                            onClick = {},
                            modifier = Modifier.puck(parentSize, behaviour = Gravity(circle))
                                .width(80.dp)
                                .height(80.dp),
                            backgroundColor = WHITE200
                        ) {
                            Image(
                                painterResource(R.drawable.pencil),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(30.dp)
                                    .width(30.dp)
                                    .rotate(135f)
                            )
                        }



////edges center
                        FloatingActionButton(
                            onClick = {},
                            modifier = Modifier
                                .puck(parentSize, Sticky(config = Configuration.Edges), isPointsTowardsCenter = true, animationDuration = 700)
                                .width(90.dp)
                                .height(90.dp),
                            backgroundColor = Color.Blue
                        ) {
                            Image(
                                painterResource(R.drawable.pencil),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(50.dp)
                                    .rotate(135f)
                            )
                        }
////corners
                        FloatingActionButton(
                            onClick = {},
                            modifier = Modifier
                                .puck(parentSize, Sticky(config = Corners), animationDuration = 700)
                                .width(90.dp)
                                .height(90.dp),
                            backgroundColor = Purple200
                        ) {
                            Image(
                                painterResource(R.drawable.pencil),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(50.dp)
                                    .rotate(135f)
                            )
                        }


                        //horizontal edges
                        FloatingActionButton(
                            onClick = {},
                            modifier = Modifier
                                .puck(parentSize, Sticky(config = Configuration.HorizontalEdges), animationDuration = 700)
                                .width(90.dp)
                                .height(90.dp),
                            backgroundColor = Teal200
                        ) {
                            Image(
                                painterResource(R.drawable.pencil),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(50.dp)
                                    .rotate(135f)
                            )
                        }

                        FloatingActionButton(
                            onClick = {},
                            modifier = Modifier
                                .puck(parentSize, Sticky(config = Configuration.VerticalEdges), animationDuration = 700)
                                .width(90.dp)
                                .height(90.dp),
                            backgroundColor = Color.Red
                        ) {
                            Image(
                                painterResource(R.drawable.pencil),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(50.dp)
                                    .rotate(135f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Heading() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(text = "puck", fontSize = 80.sp, color = Color.DarkGray, textAlign = TextAlign.Center)
        Text(text = "draggable composables", fontSize = 15.sp, color = Color.Gray, textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PuckTheme {

    }
}
//Draw the gravity field circle for demonstration purpose