package name.modid.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class NoOffhandRocketUseClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		UseBlockCallback.EVENT.register(((player, world, hand, hitResult) ->
			hand == Hand.OFF_HAND && player.getOffHandStack().getItem() instanceof FireworkRocketItem ? ActionResult.FAIL : ActionResult.PASS));
	}
}