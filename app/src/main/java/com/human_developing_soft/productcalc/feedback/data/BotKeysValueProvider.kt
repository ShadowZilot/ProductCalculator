package com.human_developing_soft.productcalc.feedback.data

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.get
import com.human_developing_soft.productcalc.MainApp.Companion.BOT_KEY
import com.human_developing_soft.productcalc.MainApp.Companion.GROUP_ID
import javax.inject.Inject

interface BotKeysValueProvider {

    fun getBotApiValue(): BotApiData

    fun getBotApiValue(onBotApiComplete: (BotApiData) -> Unit, onFailure: () -> Unit)
}

class BotKeysValueProviderImpl @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig
) : BotKeysValueProvider {

    override fun getBotApiValue(): BotApiData {
        val botKey = remoteConfig[BOT_KEY].asString()
        val groupId = remoteConfig[GROUP_ID].asLong()
        if (botKey.isEmpty() || groupId == 0L) {
            throw WrongBotApiValueException()
        }
        return BotApiData(botKey, groupId)
    }

    override fun getBotApiValue(onBotApiComplete: (BotApiData) -> Unit, onFailure: () -> Unit) {
        try {
            onBotApiComplete.invoke(getBotApiValue())
        } catch (e: WrongBotApiValueException) {
            remoteConfig.fetchAndActivate().addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    onBotApiComplete.invoke(getBotApiValue())
                } else {
                    onFailure()
                }
            }
        }
    }
}