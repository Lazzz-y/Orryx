package org.gitee.orryx.core.skill

import org.bukkit.entity.Player
import org.gitee.orryx.core.kether.parameter.IParameter
import org.gitee.orryx.dao.cache.Saveable
import org.gitee.orryx.dao.pojo.PlayerSkillPO
import java.util.concurrent.CompletableFuture

interface IPlayerSkill: Saveable {

    /**
     * 玩家ID
     * */
    val id: Int

    /**
     * 拥有玩家
     * */
    val player: Player

    /**
     * 技能键名
     * */
    val key: String

    /**
     * 技能配置文件
     * */
    val skill: ISkill

    /**
     * 技能所属职业
     * */
    val job: String

    /**
     * 技能是否锁定
     * */
    val locked: Boolean

    /**
     * 技能等级
     * */
    val level: Int

    /**
     * 强制的释放技能
     *
     * 不会经过任何检测，同时Cast事件也在此call
     * @param parameter 释放参数
     * @return 释放结果
     * */
    fun cast(parameter: IParameter, consume: Boolean = true): CastResult

    /**
     * 释放技能检测
     *
     * 会经过多道检测，同时Check事件也在此call
     * @param parameter 释放参数
     * @return 释放结果
     * */
    fun castCheck(parameter: IParameter): CompletableFuture<CastResult>

    /**
     * 升级技能点检测
     * @param from 从等级
     * @param to 到等级
     * @return 需要消耗数量，是否足够
     * */
    fun upgradePointCheck(from: Int, to: Int): CompletableFuture<Pair<Int,Boolean>>

    /**
     * 升级检测
     * @param from 从等级
     * @param to 到等级
     * @return 是否可以升级
     * */
    fun upLevelCheck(from: Int, to: Int): Boolean

    /**
     * 升级成功执行
     * @param from 从等级
     * @param to 到等级
     * */
    fun upLevelSuccess(from: Int, to: Int)

    /**
     * 升级技能
     * @param level 等级
     * @return [SkillLevelResult]
     * */
    fun upLevel(level: Int): CompletableFuture<SkillLevelResult>

    /**
     * 降级技能
     * @param level 等级
     * @return [SkillLevelResult]
     * */
    fun downLevel(level: Int): CompletableFuture<SkillLevelResult>

    /**
     * 设置技能等级
     * @param level 等级
     * @return [SkillLevelResult]
     * */
    fun setLevel(level: Int): CompletableFuture<SkillLevelResult>

    /**
     * 清除数据
     * @return 是否成功
     * */
    fun clear(): CompletableFuture<Boolean>

    /**
     * 创建序列化存储数据
     * */
    fun createPO(): PlayerSkillPO
}