package com.alectorous.naturalsynergy.player.event;

import java.util.HashMap;

import com.alectorous.naturalsynergy.player.EnumClass;
import com.alectorous.naturalsynergy.player.PlayerData;

import net.minecraft.enchantment.EnchantmentArrowFire;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

@Mod.EventBusSubscriber
public class PlayerEventHandler {
	
	private static HashMap<String, PlayerData> cachedData = new HashMap<>();
	
	@SubscribeEvent
	public static void playerJoin(PlayerLoggedInEvent event) {
		if (!event.player.getEntityData().hasKey("class")) {
			PlayerData data = new PlayerData();
			data.loadDefaults();
			data.saveToPlayer(event.player);
		}
	}
	
	@SubscribeEvent
	public static void playerDeath(LivingDeathEvent event) {
		
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			PlayerData data = new PlayerData();
			data.readFromPlayer(player);
			if (!(data.getPlayerClass() == EnumClass.NONE)) {
				cachedData.put(player.getName(), data);
				cachedData.remove(player.getName());
			}
		}
		
	}	
	
	@SubscribeEvent
	public static void playerRespawn(PlayerRespawnEvent event) {
		if (cachedData.containsKey(event.player.getName())) {
			PlayerData data = cachedData.get(event.player.getName());
			data.saveToPlayer(event.player);
		}
	}
	
	@SubscribeEvent
	public static void playerLooseArrowEvent(ArrowLooseEvent event) {
	}	
	
	@SubscribeEvent
	public static void playerDamaged(LivingDamageEvent event) {
		System.out.println(event.getSource().damageType);
		if (event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving(); 
			if (event.isCancelable()) {
				PlayerData data = new PlayerData();
				data.readFromPlayer(player);
				
				if (data.getPlayerClass() == EnumClass.FIRE) {
					if (event.getSource() == DamageSource.IN_FIRE || event.getSource() == DamageSource.ON_FIRE) {
						if (data.getLevel() >= 1) {
							event.setCanceled(true);
						}
					}
					else if (event.getSource() == DamageSource.LAVA) {
						if (data.getLevel() >= 4) {
							event.setCanceled(true);
						}
					}
					else if (event.getSource().getDamageType().equals("explosion.player")) {
						if (data.getLevel() >= 2) {
							event.setCanceled(true);
						}
					}
					
				}
			}
		}
	}
}
