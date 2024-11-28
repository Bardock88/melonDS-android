package me.magnum.melonds.domain.repositories

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import me.magnum.melonds.domain.model.layout.LayoutConfiguration
import java.util.*

interface LayoutsRepository {
    fun getLayouts(): Observable<List<LayoutConfiguration>>
    fun getLayout(id: UUID): Maybe<LayoutConfiguration>
    fun deleteLayout(layout: LayoutConfiguration): Completable
    fun getGlobalLayoutPlaceholder(): LayoutConfiguration
    fun observeLayout(id: UUID): Observable<LayoutConfiguration>
    fun saveLayout(layout: LayoutConfiguration)
}