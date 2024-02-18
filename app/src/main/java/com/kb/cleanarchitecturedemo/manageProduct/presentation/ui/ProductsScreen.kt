package com.kb.cleanarchitecturedemo.manageProduct.presentation.ui

import androidx.compose.foundation.background
import com.kb.cleanarchitecturedemo.util.MyTopAppBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.kb.cleanarchitecturedemo.manageProduct.domain.model.ProductDataModel
import com.kb.cleanarchitecturedemo.manageProduct.presentation.viewModel.ProductsViewModel
import com.kb.cleanarchitecturedemo.manageProduct.presentation.viewModel.ProductsViewState
import com.kb.cleanarchitecturedemo.util.LoadingDialog

@Composable
internal fun ProductsScreen() {
    val viewModel: ProductsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    ProductsContent(state = state)
}

@Composable
fun ProductsContent(state: ProductsViewState) {
    LoadingDialog(isLoading = state.isLoading)
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        topBar = { MyTopAppBar(text = "Products") }
    ) {
        LazyVerticalStaggeredGrid(
            modifier = Modifier.padding(top = it.calculateTopPadding()),
            columns = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalItemSpacing = 10.dp
        ) {
            items(state.products) { product ->
                ProductCard(product = product)
            }
        }
    }
}

@Composable
fun ProductCard(modifier: Modifier = Modifier, product: ProductDataModel) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.FillBounds,
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = product.title, style = MaterialTheme.typography.titleMedium, maxLines = 2)
        }
    }
}