package com.github.jwg4.lemonade_mod.commands

import com.github.jwg4.lemonade_mod.errors.CommandError
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender
import net.minecraft.util.BlockPos

class SimpleCommand : CommandBase {
    private val commandName: String
    private val runnable: ProcessCommandRunnable
    private var tabRunnable: TabCompleteRunnable? = null

    constructor(commandName: String, runnable: ProcessCommandRunnable) {
        this.commandName = commandName
        this.runnable = runnable
    }

    constructor(commandName: String, runnable: ProcessCommandRunnable, tabRunnable: TabCompleteRunnable?) {
        this.commandName = commandName
        this.runnable = runnable
        this.tabRunnable = tabRunnable
    }

    abstract class ProcessCommandRunnable {
        abstract fun processCommand(sender: ICommandSender?, args: Array<String>?)
    }

    interface TabCompleteRunnable {
        fun tabComplete(sender: ICommandSender?, args: Array<String>?, pos: BlockPos?): List<String>
    }

    override fun canCommandSenderUseCommand(sender: ICommandSender): Boolean {
        return true
    }

    override fun getCommandName(): String {
        return commandName
    }

    override fun getCommandUsage(sender: ICommandSender): String {
        return "/$commandName"
    }

    override fun processCommand(sender: ICommandSender, args: Array<String>) {
        try {
            runnable.processCommand(sender, args)
        } catch (e: Throwable) {
            throw CommandError("Error while executing command /$commandName", e)
        }
    }

    override fun addTabCompletionOptions(sender: ICommandSender, args: Array<String>, pos: BlockPos): List<String>? {
        return if (tabRunnable != null) tabRunnable!!.tabComplete(sender, args, pos) else null
    }
}