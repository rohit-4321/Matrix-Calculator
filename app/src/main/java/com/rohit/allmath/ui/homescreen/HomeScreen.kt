package com.rohit.allmath.ui.homescreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.rohit.allmath.R
import com.rohit.allmath.ui.calculatorscreen.CalculatorScreen
import com.rohit.allmath.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(navController: NavHostController)
{
    tabs(navController)
}
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun tabs(navController: NavHostController) {
    var pageState = rememberPagerState()

    Column() {
        Alltabs(pageState = pageState)
        HorizontalPager(count = 2,
            state = pageState) { it ->
            when(it)
            {
                0 -> HomeScreenContent(navController)
                1 -> CalculatorScreen()
            }
        }


    }
}
@ExperimentalPagerApi
@Composable
fun Alltabs(pageState : PagerState, )
{
    var scope = rememberCoroutineScope()
    var listOfTabs = listOf(
        painterResource(id =  R.drawable.home_icon),
        painterResource(id = R.drawable.calculator_icon)
    )
    TabRow(
        selectedTabIndex = pageState.currentPage,
        modifier = Modifier
            .padding(horizontal = 100.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(20.dp)),
        divider = {
            TabRowDefaults.Divider(color = Color.Transparent , thickness = 0.dp )
        },
        indicator = {
            TabRowDefaults.Indicator(color = Color.Transparent)
        },
    ) {

        listOfTabs.forEachIndexed { index, res ->
            Tab(selected = pageState.currentPage == index,
                modifier = Modifier
                    .background(MaterialTheme.colors.background),
                onClick = {
                    scope.launch {
                        pageState.scrollToPage(index)
                    }
                },
                selectedContentColor = Color(0xffECB365),
                unselectedContentColor = Color(0xff515151),
                content = {
                    Icon(painter = res,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .size(33.dp),
                        contentDescription = null)
                })

        }
    }

}
@Composable
fun HomeScreenContent(navController: NavHostController)
{
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 9.dp, horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .border(width = 2.dp, Color(0xff505050), RoundedCornerShape(20.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xff150610),
                            Color(0xff1A0814),
                            Color(0xff1D0D18)
//                            Color(0xff25131F),
//                            Color(0xff391B30),
//                            Color(0xf25A2749)
                        )
                    )
                )
                .clickable {
                    navController.navigate(NavigationDestination.DeterminantOfMatrix.route) {
                        popUpTo(NavigationDestination.Home.route) {
                            inclusive = false
                        }
                    }
                }) {
                Text(text = "1. Determinant of a Matrix" ,
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 14.dp)
                        ,
                    fontSize = 24.sp,
                    color = Color(0xffc0c0c0)
                )
            }



        }

    }

}
@Preview
@Composable
fun HomeScreenPreview()
{
    HomeScreenContent(navController = rememberNavController())
}


