package name.modid.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class NoOffhandRocketUseClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		NoOffhandRocketUseConfig.load();

		ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("nooffhandrocketuse")
                .then(ClientCommandManager.literal("toggle")
                        .executes(context -> {
                            NoOffhandRocketUseConfig config = NoOffhandRocketUseConfig.get();
                            config.enabled = !config.enabled;
                            NoOffhandRocketUseConfig.save();

                            context.getSource().sendFeedback(Text.literal("nooffhandrocketuse:" + (config.enabled ? "Enabled" : "Disabled")));
                            return 1;
                        })))));

		UseBlockCallback.EVENT.register(((player, world, hand, hitResult) -> {
			if (!NoOffhandRocketUseConfig.get().enabled) return ActionResult.PASS;
			return hand == Hand.OFF_HAND && player.getOffHandStack().getItem() instanceof FireworkRocketItem ? ActionResult.FAIL : ActionResult.PASS;
		}));
	}
}