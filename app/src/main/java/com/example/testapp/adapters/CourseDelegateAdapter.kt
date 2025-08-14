package com.example.testapp.adapters

import android.content.Context
import androidx.core.view.isVisible
import com.example.domain.entity.CourseEntity
import com.example.testapp.R
import com.example.testapp.databinding.CourseItemBinding
import com.livermor.delegateadapter.delegate.ViewBindingDelegateAdapter

class CourseDelegateAdapter(
    private val context: Context,
    private val flagClickListener: FlagClickListener,
) :
    ViewBindingDelegateAdapter<CourseEntity, CourseItemBinding>(CourseItemBinding::inflate) {

    override fun isForViewType(item: Any): Boolean = item is CourseEntity

    override fun CourseItemBinding.onBind(item: CourseEntity) {
        item.apply {
            ratingText.text = rate.toString()
            startDateTv.text = publishDate
            courseTitle.text = title
            courseDescText.text = description
            coursePriceText.text = context.getString(R.string.course_price, price)
            hasLikeFlagImgFalse.isVisible = !hasLike
            hasLikeFlagImgTrue.isVisible = hasLike
            hasLikeFlag.setOnClickListener {
                hasLike = !hasLike
                hasLikeFlagImgFalse.isVisible = !hasLike
                hasLikeFlagImgTrue.isVisible = hasLike
                flagClickListener.flagClick(item)
            }
        }
    }

    override fun CourseEntity.getItemId(): Any = title

    interface FlagClickListener {
        fun flagClick(course: CourseEntity)
    }
}