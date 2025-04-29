package com.io7m.anim

import android.app.Activity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import com.google.android.material.appbar.AppBarLayout
import com.io7m.anim.main.R

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
    val customToolbar =
      this.findViewById<View>(R.id.customToolbar)

    textView.text = Html.fromHtml(
      String(this.assets.open("text.html").readBytes()),
      Html.FROM_HTML_MODE_COMPACT
    )
  }
}
