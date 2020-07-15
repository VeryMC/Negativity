package com.elikill58.negativity.protocols;

import org.bukkit.event.entity.EntityRegainHealthEvent;

import com.elikill58.negativity.common.NegativityPlayer;
import com.elikill58.negativity.common.entity.Player;
import com.elikill58.negativity.common.events.EventListener;
import com.elikill58.negativity.common.events.Listeners;
import com.elikill58.negativity.common.events.player.PlayerInteractEvent;
import com.elikill58.negativity.common.item.Material;
import com.elikill58.negativity.common.item.Materials;
import com.elikill58.negativity.common.location.Difficulty;
import com.elikill58.negativity.common.potion.PotionEffect;
import com.elikill58.negativity.common.potion.PotionEffectType;
import com.elikill58.negativity.universal.Cheat;
import com.elikill58.negativity.universal.CheatKeys;
import com.elikill58.negativity.universal.FlyingReason;
import com.elikill58.negativity.universal.Negativity;
import com.elikill58.negativity.universal.ReportType;
import com.elikill58.negativity.universal.Version;
import com.elikill58.negativity.universal.utils.UniversalUtils;

public class Regen extends Cheat implements Listeners {
	
	public Regen() {
		super(CheatKeys.REGEN, true, Materials.GOLDEN_APPLE, CheatCategory.PLAYER, true, "regen", "autoregen");
	}

	@EventListener
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Material m = p.getItemInHand().getType();
		if (m.equals(Materials.GOLDEN_APPLE) || m.equals(Materials.GOLDEN_CARROT))
			NegativityPlayer.getNegativityPlayer(p).flyingReason = FlyingReason.REGEN;
	}

	@EventListener
	public void onRegen(EntityRegainHealthEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		Player p = (Player) e.getEntity();
		NegativityPlayer np = NegativityPlayer.getNegativityPlayer(p);
		boolean hasPotion = false;
		for (PotionEffect pe : p.getActivePotionEffect())
			if (pe.getType().equals(PotionEffectType.POISON) || pe.getType().equals(PotionEffectType.BLINDNESS)
					|| pe.getType().equals(PotionEffectType.WITHER)
					|| pe.getType().equals(PotionEffectType.SLOW_DIGGING)
					|| pe.getType().equals(PotionEffectType.WEAKNESS) || pe.getType().equals(PotionEffectType.CONFUSION)
					|| pe.getType().equals(PotionEffectType.HUNGER))
				hasPotion = true;
		if (hasPotion)
			np.flyingReason = FlyingReason.POTION;
		else
			np.flyingReason = FlyingReason.REGEN;
		long actual = System.currentTimeMillis(), dif = actual - np.LAST_REGEN;
		if (np.LAST_REGEN != 0 && !p.hasPotionEffect(PotionEffectType.REGENERATION) && np.hasDetectionActive(this)
				&& (np.LAST_REGEN != System.currentTimeMillis() && Version.getVersion().isNewerOrEquals(Version.V1_14))
				&& !p.getWorld().getDifficulty().equals(Difficulty.PEACEFUL)) {
			int ping = p.getPing();
			if (dif < (Version.getVersion().getTimeBetweenTwoRegenFromVersion() + ping)) {
				boolean mayCancel = Negativity.alertMod(dif < (50 + ping) ? ReportType.VIOLATION : ReportType.WARNING, p, this,
						UniversalUtils.parseInPorcent(200 - dif - ping), "Player regen, last regen: " + np.LAST_REGEN
									+ " Actual time: " + actual + " Difference: " + dif + "ms",
									hoverMsg("main", "%time%", dif));
				if(isSetBack() && mayCancel)
					e.setCancelled(true);
			}
		}
		np.LAST_REGEN = actual;
	}
}
