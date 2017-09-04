/**
 * Copyright 2014 AnjLab
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.anjlab.android.iab.v3

import android.os.Parcel
import android.os.Parcelable

data class TransactionDetails(val purchaseInfo: PurchaseInfo) : Parcelable {

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(this.purchaseInfo, flags)
    }

    protected constructor(inParcel: Parcel) : this(inParcel.loadParcelable<PurchaseInfo>())

    companion object {

        @Suppress("unused")
        @JvmField
        val CREATOR: Parcelable.Creator<TransactionDetails> = object : Parcelable.Creator<TransactionDetails> {
            override fun createFromParcel(source: Parcel): TransactionDetails {
                return TransactionDetails(source)
            }

            override fun newArray(size: Int): Array<TransactionDetails?> {
                return arrayOfNulls(size)
            }
        }
    }
}

inline fun <reified T : Parcelable> Parcel.loadParcelable(): T {
    return this.readParcelable(T::class.java.classLoader)
}