package com.elikill58.negativity.spigot.impl.item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.StringJoiner;

import org.bukkit.Material;

import com.elikill58.negativity.api.item.ItemRegistrar;
import com.elikill58.negativity.api.item.Materials;
import com.elikill58.negativity.spigot.SpigotNegativity;

public class SpigotItemRegistrar extends ItemRegistrar {

	private final HashMap<String, com.elikill58.negativity.api.item.Material> cache = new HashMap<>();

	@Override
	public synchronized com.elikill58.negativity.api.item.Material get(String id, String... alias) {
		return cache.computeIfAbsent(id, key -> new SpigotMaterial(getMaterial(key.toUpperCase(), alias), id));
	}
	
	private org.bukkit.Material getMaterial(String id, String... alias){
		Material idM = get(id);
		if(idM != null)
			return idM;

		for(String s : alias) {
			Material m = get(s);
			if(m != null)
				return m;
		}
		if(id.contains("_")) {
			for(String types : Arrays.asList("WOOL", "STAINED_CLAY", "CARPET", "STAINED_GLASS_PANE", "BANNER", "WOOD")) {
				if(id.contains(types))
					return get(types);
			}
		}
		/*org.bukkit.Material type = org.bukkit.Material.getMaterial(id.toUpperCase());
		if(type != null)
			return type;
		for(String s : alias) {
			type = org.bukkit.Material.getMaterial(s.toUpperCase());
			if(type != null)
				return type;
		}*/
		StringJoiner sj = new StringJoiner(", ", " : ", "");
		for(String tempAlias : alias) {
			if(tempAlias.equals(Materials.IGNORE_KEY))
				return null; // ignore not found item
			sj.add(tempAlias);
		}
		SpigotNegativity.getInstance().getLogger().info("[SpigotItemRegistrar] Cannot find material " + id + sj);
		return null;
	}
	
	private Material get(String name) {
		name = name.toUpperCase();
		try {
			return (Material) Material.class.getField(name).get(Material.class);
		} catch (IllegalArgumentException | IllegalAccessException | SecurityException e2) {
			e2.printStackTrace();
		} catch (NoSuchFieldException e) {}
		if(SpigotNegativity.isMohist) {
			try {
				return Material.valueOf(name);
			} catch (Exception e) {}
		}
		return null;
	}
}
