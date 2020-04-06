package com.jdc.restaurant.ui.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.GridLayoutAnimationController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridRecyclerView(context:Context, attributeSet: AttributeSet?, defStyleAttr: Int):RecyclerView(context, attributeSet, defStyleAttr)  {

    constructor(context:Context, attributeSet: AttributeSet?):this(context, attributeSet, 0)

    constructor(context: Context):this(context, null, 0)

    override fun attachLayoutAnimationParameters(
        child: View?,
        params: ViewGroup.LayoutParams?,
        index: Int,
        count: Int
    ) {
        if(null != adapter && layoutManager is GridLayoutManager) {

            val animationParams = params?.layoutAnimationParameters ?: GridLayoutAnimationController.AnimationParameters().also {
                params?.layoutAnimationParameters = it
            }

            val gridLayoutManager = layoutManager as GridLayoutManager
            val columns = gridLayoutManager.spanCount
            val gridAnimationParams = animationParams as GridLayoutAnimationController.AnimationParameters

            gridAnimationParams.count = count
            gridAnimationParams.index = index
            gridAnimationParams.count = count
            gridAnimationParams.columnsCount = columns
            gridAnimationParams.rowsCount = count / columns

            val invertedIndex = count - 1 - index
            gridAnimationParams.column = columns - 1 - (invertedIndex % columns)
            gridAnimationParams.row = animationParams.rowsCount - 1 - invertedIndex / columns

        } else {
            super.attachLayoutAnimationParameters(child, params, index, count)
        }
    }
}