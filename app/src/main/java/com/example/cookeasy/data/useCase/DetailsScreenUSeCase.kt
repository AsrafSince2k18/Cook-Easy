package com.example.cookeasy.data.useCase

import com.example.cookeasy.data.repository.Repository

class DetailsScreenUSeCase (val repository: Repository) {

    fun getMealId(id: Int)= repository.getMealDetails(id=id)

}