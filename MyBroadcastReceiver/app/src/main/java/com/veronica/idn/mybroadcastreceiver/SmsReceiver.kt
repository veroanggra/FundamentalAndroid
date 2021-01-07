package com.veronica.idn.mybroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage

class SmsReceiver : BroadcastReceiver() {
    companion object{
        private val TAG = SmsReceiver::class.java.simpleName
    }

    override fun onReceive(context: Context, intent: Intent) {

    }

    private fun getIncomingMessage(mObject: Any, bundle: Bundle):SmsMessage{
        val currentSMS : SmsMessage
        val format = bundle.getString("format")
        currentSMS = if (Build.VERSION.SDK_INT >= 23){
            SmsMessage.createFromPdu(mObject as ByteArray, format)
        }else SmsMessage.createFromPdu(mObject as ByteArray)
        return currentSMS
    }
}