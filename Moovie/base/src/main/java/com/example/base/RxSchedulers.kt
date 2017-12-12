package com.example.base

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

data class RxSchedulers (
        val network: Scheduler = Schedulers.io(),
        val ui: Scheduler = AndroidSchedulers.mainThread(),
        val data: Scheduler = Schedulers.single()
)
