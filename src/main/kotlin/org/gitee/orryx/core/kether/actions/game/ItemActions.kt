package org.gitee.orryx.core.kether.actions.game

import org.gitee.orryx.core.targets.PlayerTarget
import org.gitee.orryx.module.wiki.Action
import org.gitee.orryx.module.wiki.Type
import org.gitee.orryx.utils.*
import taboolib.module.kether.KetherParser
import java.util.concurrent.CompletableFuture

object ItemActions {

    @KetherParser(["itemInMainHand"], namespace = ORRYX_NAMESPACE, shared = true)
    private fun actionItemInMainHand() = combinationParser(
        Action.new("Game原版游戏", "获取玩家主手物品", "itemInMainHand", true)
            .description("获取玩家主手物品")
            .addContainerEntry(optional = true, default = "@self")
            .result("物品", Type.ITEM_STACK)
    ) {
        it.group(
            theyContainer(true)
        ).apply(it) { container ->
            future {
                val player = container.orElse(self()).firstInstance<PlayerTarget>()
                CompletableFuture.completedFuture(player.getSource().inventory.itemInMainHand)
            }
        }
    }

    @KetherParser(["itemInOffHand"], namespace = ORRYX_NAMESPACE, shared = true)
    private fun actionItemInOffHand() = combinationParser(
        Action.new("Game原版游戏", "获取玩家副手物品", "itemInOffHand", true)
            .description("获取玩家副手物品")
            .addContainerEntry(optional = true, default = "@self")
            .result("物品", Type.ITEM_STACK)
    ) {
        it.group(
            theyContainer(true)
        ).apply(it) { container ->
            future {
                val player = container.orElse(self()).firstInstance<PlayerTarget>()
                CompletableFuture.completedFuture(player.getSource().inventory.itemInOffHand)
            }
        }
    }

    @KetherParser(["itemOnCursor"], namespace = ORRYX_NAMESPACE, shared = true)
    private fun actionItemOnCursor() = combinationParser(
        Action.new("Game原版游戏", "获取玩家光标物品", "itemOnCursor", true)
            .description("获取玩家光标物品")
            .addContainerEntry(optional = true, default = "@self")
            .result("物品", Type.ITEM_STACK)
    ) {
        it.group(
            theyContainer(true)
        ).apply(it) { container ->
            future {
                val player = container.orElse(self()).firstInstance<PlayerTarget>()
                CompletableFuture.completedFuture(player.getSource().itemOnCursor)
            }
        }
    }

    @KetherParser(["helmet"], namespace = ORRYX_NAMESPACE, shared = true)
    private fun actionHelmet() = combinationParser(
        Action.new("Game原版游戏", "获取玩家头上物品", "helmet", true)
            .description("获取玩家光标物品")
            .addContainerEntry(optional = true, default = "@self")
            .result("物品", Type.ITEM_STACK)
    ) {
        it.group(
            theyContainer(true)
        ).apply(it) { container ->
            future {
                val player = container.orElse(self()).firstInstance<PlayerTarget>()
                CompletableFuture.completedFuture(player.getSource().inventory.helmet)
            }
        }
    }

    @KetherParser(["chestplate"], namespace = ORRYX_NAMESPACE, shared = true)
    private fun actionChestplate() = combinationParser(
        Action.new("Game原版游戏", "获取玩家胸上物品", "chestplate", true)
            .description("获取玩家胸上物品")
            .addContainerEntry(optional = true, default = "@self")
            .result("物品", Type.ITEM_STACK)
    ) {
        it.group(
            theyContainer(true)
        ).apply(it) { container ->
            future {
                val player = container.orElse(self()).firstInstance<PlayerTarget>()
                CompletableFuture.completedFuture(player.getSource().inventory.chestplate)
            }
        }
    }

    @KetherParser(["leggings"], namespace = ORRYX_NAMESPACE, shared = true)
    private fun actionLeggings() = combinationParser(
        Action.new("Game原版游戏", "获取玩家腿上物品", "leggings", true)
            .description("获取玩家腿上物品")
            .addContainerEntry(optional = true, default = "@self")
            .result("物品", Type.ITEM_STACK)
    ) {
        it.group(
            theyContainer(true)
        ).apply(it) { container ->
            future {
                val player = container.orElse(self()).firstInstance<PlayerTarget>()
                CompletableFuture.completedFuture(player.getSource().inventory.leggings)
            }
        }
    }

    @KetherParser(["boots"], namespace = ORRYX_NAMESPACE, shared = true)
    private fun actionBoots() = combinationParser(
        Action.new("Game原版游戏", "获取玩家脚上物品", "boots", true)
            .description("获取玩家脚上物品")
            .addContainerEntry(optional = true, default = "@self")
            .result("物品", Type.ITEM_STACK)
    ) {
        it.group(
            theyContainer(true)
        ).apply(it) { container ->
            future {
                val player = container.orElse(self()).firstInstance<PlayerTarget>()
                CompletableFuture.completedFuture(player.getSource().inventory.boots)
            }
        }
    }
}