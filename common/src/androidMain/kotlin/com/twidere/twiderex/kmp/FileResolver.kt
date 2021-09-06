/*
 *  Twidere X
 *
 *  Copyright (C) 2020-2021 Tlaster <tlaster@outlook.com>
 * 
 *  This file is part of Twidere X.
 * 
 *  Twidere X is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  Twidere X is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with Twidere X. If not, see <http://www.gnu.org/licenses/>.
 */
package com.twidere.twiderex.kmp

import android.content.ContentResolver
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.InputStream
import java.io.OutputStream

actual class FileResolver(private val contentResolver: ContentResolver) {
    actual fun getMimeType(file: String): String? {
        return contentResolver.getType(Uri.parse(file))
    }

    actual fun getFileSize(file: String): Long? {
        return contentResolver.openFileDescriptor(Uri.parse(file), "r")?.statSize
    }

    actual fun openInputStream(file: String): InputStream? {
        return contentResolver.openInputStream(Uri.parse(file))
    }

    actual fun openOutputStream(file: String): OutputStream? {
        return contentResolver.openOutputStream(Uri.parse(file))
    }

    actual fun getMediaSize(file: String): MediaSize {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(file, options)
        return MediaSize(
            width = options.outWidth.toLong(),
            height = options.outHeight.toLong()
        )
    }
}
