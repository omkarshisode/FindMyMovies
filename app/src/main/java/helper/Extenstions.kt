package helper

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(urlString: String?) {
    if (urlString == null) {
        return
    }
    Glide.with(this)
        .load(urlString)
        .into(this)
}