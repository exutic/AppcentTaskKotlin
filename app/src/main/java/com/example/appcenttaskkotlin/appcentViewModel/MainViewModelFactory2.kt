package com.example.appcenttaskkotlin.appcentViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appcenttaskkotlin.repository.Repository2

class MainViewModelFactory2(
    private val repository1: Repository2
)
    : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return MainViewModel2(repository1) as T
    }
}