package com.example.Factura10.mapper

import com.example.Factura10.dto.ProductDto
import com.example.Factura10.model.Product

object ProductMapper {
    fun mapToDto(product: Product): ProductDto{
        return ProductDto(
                product.id,
          "${product.description}${product.brand}"
        )
    }
}