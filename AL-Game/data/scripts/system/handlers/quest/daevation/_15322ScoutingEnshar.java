/**
 * This file is part of Aion-Lightning <aion-lightning.org>.
 *
 *  Aion-Lightning is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Aion-Lightning is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details. *
 *  You should have received a copy of the GNU General Public License
 *  along with Aion-Lightning.
 *  If not, see <http://www.gnu.org/licenses/>.
 */
package quest.daevation;

import com.aionemu.gameserver.model.DialogAction;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.services.QuestService;
import com.aionemu.gameserver.world.zone.ZoneName;


/**
 * @author FrozenKiller
 */
public class _15322ScoutingEnshar extends QuestHandler {
	
    public static final int questId = 15322;
	private final static int[] mobs = {219670, 219671, 219672, 219673, 219674, 219675, 219676, 219677, 219678, 219679, 219988};
	private final static int[] mobs2 = {219702, 219703, 219704, 219705, 219706, 219707, 219708, 219709, 219710, 219711, 219712, 219713, 219857};
	private final static int[] mobs3 = {219733, 219734, 219735, 219737, 219740, 219741, 219992};
	private final static int[] mobs4 = {219752, 219753, 219754, 219755, 219756, 219757, 219758, 219759, 219760, 219761};
	private final static int[] mobs5 = {219791, 219792, 219793, 219794, 219795, 219796, 219797, 219798, 219799, 219800, 219997, 220012};

    public _15322ScoutingEnshar() {
        super(questId);
    }

    @Override
    public void register() {
		qe.registerQuestNpc(805330).addOnQuestStart(questId); 
        qe.registerQuestNpc(805330).addOnTalkEvent(questId); // Potencia
		qe.registerOnEnterZone(ZoneName.get("WASHRUN_STRETCH_220080000"), questId);
		qe.registerOnEnterZone(ZoneName.get("AURELIAN_TIMBERS_220080000"), questId);
		qe.registerOnEnterZone(ZoneName.get("BONECREAK_VALLEY_220080000"), questId);
		qe.registerOnEnterZone(ZoneName.get("THE_SANGUISAND_220080000"), questId);
		qe.registerOnEnterZone(ZoneName.get("WYRMCAST_LANDS_220080000"), questId);
		for (int mob : mobs) {
			qe.registerQuestNpc(mob).addOnKillEvent(questId);
        }
		for (int mob2 : mobs2) {
			qe.registerQuestNpc(mob2).addOnKillEvent(questId);
        }
		for (int mob3 : mobs3) {
			qe.registerQuestNpc(mob3).addOnKillEvent(questId);
        }
		for (int mob4 : mobs4) {
			qe.registerQuestNpc(mob4).addOnKillEvent(questId);
        }
		for (int mob5 : mobs5) {
			qe.registerQuestNpc(mob5).addOnKillEvent(questId);
        }
    }
	
	@Override
	public boolean onDialogEvent(QuestEnv env) {
		Player player = env.getPlayer();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        int targetId = env.getTargetId();
        DialogAction dialog = env.getDialog();
		
		if (qs == null || qs.getStatus() == QuestStatus.NONE || qs.canRepeat()) {
            if (targetId == 805330) { // Potencia
				switch (dialog) {
                    case QUEST_SELECT: {
                        return sendQuestDialog(env, 4762);
                    }
                    case QUEST_ACCEPT_1:
                    case QUEST_ACCEPT_SIMPLE:
						QuestService.startQuest(env); 
                        return closeDialogWindow(env);
					case QUEST_REFUSE_1:
					case QUEST_REFUSE_SIMPLE:
						return closeDialogWindow(env);
				default:
					break;
                }
            }
		} else if (qs.getStatus() == QuestStatus.REWARD) {
			if (targetId == 805330){
				switch (dialog) {
                    case USE_OBJECT: {
                        return sendQuestDialog(env, 10002);
                    }
                    case SELECT_QUEST_REWARD: {
                        return sendQuestDialog(env, 5);
                    }
                    case SELECTED_QUEST_NOREWARD: {
                        return sendQuestEndDialog(env);
                    }
                    default:
                        break;
                }
			}
		}
		return false;
	}
	@Override
    public boolean onKillEvent(QuestEnv env) {
		Player player = env.getPlayer();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs == null || qs.getStatus() != QuestStatus.START) {
            return false;
        }
		
		int var = qs.getQuestVarById(0);
		int var1 = qs.getQuestVarById(1);
		if (var == 1 && var1 >= 0 && var1 < 9) {
			return defaultOnKillEvent(env, mobs, var1, var1 + 1, 1);
		} else if (var == 1 && var1 == 9) {
			qs.setQuestVarById(1, 0);
			changeQuestStep(env, 1, 2, false); //2
			updateQuestStatus(env);
			return true;
		}
		if (var == 3 && var1 >= 0 && var1 < 9) {
			return defaultOnKillEvent(env, mobs2, var1, var1 + 1, 1);
		} else if (var == 3 && var1 == 9) {
			qs.setQuestVarById(1, 0);
			changeQuestStep(env, 3, 4, false); //4
			updateQuestStatus(env);
			return true;
		}
		if (var == 5 && var1 >= 0 && var1 < 9) {
			return defaultOnKillEvent(env, mobs3, var1, var1 + 1, 1);
		} else if (var == 5 && var1 == 9) {
			qs.setQuestVarById(1, 0);
			changeQuestStep(env, 5, 6, false); //6
			updateQuestStatus(env);
			return true;
		}
		if (var == 7 && var1 >= 0 && var1 < 9) {
			return defaultOnKillEvent(env, mobs4, var1, var1 + 1, 1);
		} else if (var == 7 && var1 == 9) {
			qs.setQuestVarById(1, 0);
			changeQuestStep(env, 7, 8, false); //8
			updateQuestStatus(env);
			return true;
		}
		if (var == 9 && var1 >= 0 && var1 < 9) {
			return defaultOnKillEvent(env, mobs5, var1, var1 + 1, 1);
		} else if (var == 9 && var1 == 9) {
			qs.setQuestVarById(1, 0);
			qs.setQuestVar(10);
			qs.setStatus(QuestStatus.REWARD); //10 Reward
			updateQuestStatus(env);
			return true;
		}
		return false;
	}
	
    @Override
    public boolean onEnterZoneEvent(QuestEnv env, ZoneName zoneName) {
        Player player = env.getPlayer();
		if (player == null) {
			return false;
        }
		
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs != null && qs.getStatus() == QuestStatus.START) {
		int var = qs.getQuestVarById(0);
			if (var == 0 && zoneName == ZoneName.get("WASHRUN_STRETCH_220080000")) {
				changeQuestStep(env, 0, 1, false); // 1
				return true;
            } else if (var == 2 && zoneName == ZoneName.get("AURELIAN_TIMBERS_220080000")) {
				changeQuestStep(env, 2, 3, false); // 3
				return true;				
			} else if (var == 4 && zoneName == ZoneName.get("BONECREAK_VALLEY_220080000")) {
				changeQuestStep(env, 4, 5, false); // 5
				return true;				
			} else if (var == 6 && zoneName == ZoneName.get("THE_SANGUISAND_220080000")) {
				changeQuestStep(env, 6, 7, false); // 7
				return true;				
			} else if (var == 8 && zoneName == ZoneName.get("WYRMCAST_LANDS_220080000")) {
				changeQuestStep(env, 8, 9, false); // 9
				return true;				
			}
		}
		return false;
    }
}