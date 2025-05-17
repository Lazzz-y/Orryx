package org.gitee.orryx.core.selector.stream

import org.gitee.orryx.core.container.IContainer
import org.gitee.orryx.core.parser.StringParser
import org.gitee.orryx.core.selector.ISelectorStream
import org.gitee.orryx.core.station.Plugin
import org.gitee.orryx.core.targets.PlayerTarget
import org.gitee.orryx.module.wiki.Selector
import org.gitee.orryx.module.wiki.SelectorType
import org.gitee.orryx.utils.bukkitPlayer
import org.serverct.ersha.dungeon.DungeonPlus
import org.serverct.ersha.dungeon.common.team.type.PlayerStateType
import taboolib.module.kether.ScriptContext

@Plugin("DungeonPlus")
object Team: ISelectorStream {

    override val keys = arrayOf("team")

    override val wiki: Selector
        get() = Selector.new("dp队伍过滤", keys, SelectorType.STREAM)
            .addExample("@team")
            .addExample("!@team")
            .description("只保留队内人员,或只保留队外人员")

    override fun processStream(container: IContainer, context: ScriptContext, parameter: StringParser.Entry) {
        val teamPlayers = DungeonPlus.teamManager.getTeam(context.bukkitPlayer())?.getPlayers(PlayerStateType.ONLINE) ?: return
        if (parameter.reverse) {
            container.removeIf {
                it is PlayerTarget && teamPlayers.contains(it.getSource())
            }
        } else {
            container.removeIf {
                !(it is PlayerTarget && teamPlayers.contains(it.getSource()))
            }
        }
    }
}