package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.util.ActionResult;

public class AIVillagersClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if(world.isClient && entity instanceof VillagerEntity) {
				MinecraftClient.getInstance().setScreen(new VillagerDialogueScreen());
				return ActionResult.SUCCESS;
			}
			return ActionResult.PASS;
		});
	}
}