package org.gitee.orryx.api

import org.bukkit.entity.Player
import org.gitee.orryx.api.interfaces.IKeyAPI
import org.gitee.orryx.core.key.BindKeyLoaderManager
import org.gitee.orryx.core.key.IBindKey
import org.gitee.orryx.core.key.IGroup
import org.gitee.orryx.core.skill.IPlayerSkill
import org.gitee.orryx.utils.getSkill
import org.gitee.orryx.utils.job
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.PlatformFactory
import java.util.concurrent.CompletableFuture

class KeyAPI: IKeyAPI {

    override fun bindSkillKeyOfGroup(skill: IPlayerSkill, group: IGroup, bindKey: IBindKey): CompletableFuture<Boolean> {
        return bindSkillKeyOfGroup(skill.player, skill.job, skill.key, group.key, bindKey.key)
    }

    override fun bindSkillKeyOfGroup(player: Player, job: String, skill: String, group: String, bindKey: String): CompletableFuture<Boolean> {
        val future = CompletableFuture<Boolean>()
        player.getSkill(job, skill).thenAccept { sk ->
            sk ?: error("玩家${player.name}在职业${job}无技能$skill")
            player.job(sk.id, job) { jb ->
                jb.setBindKey(sk, getGroup(group), getBindKey(bindKey)).thenAccept {
                    future.complete(it)
                }
            }
        }
        return future
    }

    override fun getGroup(key: String): IGroup = BindKeyLoaderManager.getGroup(key) ?: error("未找到组 $key 请在 config.yml 中配置")

    override fun getBindKey(key: String): IBindKey = BindKeyLoaderManager.getBindKey(key) ?: error("未找到绑定按键 $key 请在 keys.yml 中配置")

    companion object {

        @Awake(LifeCycle.CONST)
        fun init() {
            PlatformFactory.registerAPI<IKeyAPI>(KeyAPI())
        }
    }
}