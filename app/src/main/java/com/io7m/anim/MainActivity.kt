package com.io7m.anim

import android.animation.ValueAnimator
import android.app.Activity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.math.MathUtils.clamp
import com.google.android.material.appbar.AppBarLayout
import com.io7m.anim.main.R
import java.util.concurrent.atomic.AtomicReference

class MainActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    this.setContentView(R.layout.main)
  }

  override fun onStart() {
    super.onStart()

    val textView =
      this.findViewById<TextView>(R.id.text)
    val appBarLayout =
      this.findViewById<AppBarLayout>(R.id.appBar)
    val imageLayout =
      this.findViewById<ConstraintLayout>(R.id.imageOverlay)
    val image =
      this.findViewById<ImageView>(R.id.bookImage)
    val customToolbar =
      this.findViewById<View>(R.id.customToolbar)

    textView.text = Html.fromHtml(
      String(this.assets.open("text.html").readBytes()),
      Html.FROM_HTML_MODE_COMPACT
    )

    val offsetPreviousRef =
      AtomicReference(0.0)

    appBarLayout.addOnOffsetChangedListener { _, verticalOffset ->
      val totalScrollRange =
        appBarLayout.totalScrollRange
      val absOffset =
        Math.abs(verticalOffset)
      val offset =
        clamp(absOffset.toDouble() / totalScrollRange.toDouble(), 0.0, 1.0)
      val offsetPrevious =
        offsetPreviousRef.get()

      if (offset != 0.0 && offsetPrevious == 0.0) {
        val animator = ValueAnimator.ofFloat(1.0f, 0.0f)
        animator.duration = 100
        animator.start()
        animator.addUpdateListener { context ->
          image.alpha = context.animatedValue as Float
          image.scaleX = context.animatedValue as Float
          image.scaleY = context.animatedValue as Float
        }
      } else if (offset == 0.0 && offsetPrevious != 0.0) {
        val animator = ValueAnimator.ofFloat(0.0f, 1.0f)
        animator.duration = 100
        animator.startDelay = 100
        animator.start()
        animator.addUpdateListener { context ->
          image.alpha = context.animatedValue as Float
          image.scaleX = context.animatedValue as Float
          image.scaleY = context.animatedValue as Float
        }
      }

      offsetPreviousRef.set(offset)
    }
  }
}
