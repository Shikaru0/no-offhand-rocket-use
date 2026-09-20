# no-offhand-rocket-use
A minimal Fabric minecraft mod that disables the use of firework rockets
when their in your offhand.

## How does it work?
It checks if the current hand is `OFF_HAND`, and if the offHandStack is a type of `FireworkRocketItem`,
if so it returns `ActionResult.FAIL` which cancels the use of the rocket.
If the mod is disabled, it simply returns `ActionResult.PASS`
```
UseBlockCallback.EVENT.register(((player, world, hand, hitResult) -> {
	if (!NoOffhandRocketUseConfig.get().enabled) return ActionResult.PASS;
		return hand == Hand.OFF_HAND && player.getOffHandStack().getItem() instanceof FireworkRocketItem ? ActionResult.FAIL : ActionResult.PASS;
	}))
```
## Configuration
The mod uses a `nooffhandrocketuse.json` in the fabric config directory as configuration file.

### Enabling / Disabling
You can toggle the mod, using:
```
/nooffhandrocketuse toggle
```
_This also saves the content to the config file._