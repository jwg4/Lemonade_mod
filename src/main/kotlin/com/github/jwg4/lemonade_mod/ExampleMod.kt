package com.github.jwg4.lemonade_mod

import com.github.jwg4.lemonade_mod.commands.CommandManager
import com.github.jwg4.lemonade_mod.config.ConfigManager
import com.github.jwg4.lemonade_mod.config.categories.ExampleModConfig
import com.github.jwg4.lemonade_mod.features.ChatFeatures
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

@Mod(modid = ExampleMod.MOD_ID, useMetadata = true)
class ExampleMod {

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        configManager = ConfigManager()
        MinecraftForge.EVENT_BUS.register(configManager)
    }

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        CommandManager()

        MinecraftForge.EVENT_BUS.register(ChatFeatures())
    }

    companion object {
        lateinit var configManager: ConfigManager
        const val MOD_ID = "lemonademod"

        @JvmStatic
        val version: String
            get() = Loader.instance().indexedModList[MOD_ID]!!.version

        val config: ExampleModConfig
            get() = configManager.config ?: error("config is null")
    }
}
