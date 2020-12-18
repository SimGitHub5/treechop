package ht.treechop.client;

import ht.treechop.TreeChopMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.LinkedList;
import java.util.List;

@Mod.EventBusSubscriber(modid = TreeChopMod.MOD_ID, value = Side.CLIENT)
public class KeyBindings {

    public static final String CATEGORY = "HT's TreeChop";
    public static ActionableKeyBinding toggleChopping;

    public static List<ActionableKeyBinding> allKeyBindings = new LinkedList<>();

    public static void init() {
        registerKeyBinding("toggle_chopping", getKey(Keyboard.KEY_N), Client::toggleChopping);
        registerKeyBinding("toggle_felling", getKey(Keyboard.KEY_NONE), Client::toggleFelling);
        registerKeyBinding("cycle_sneak_behavior", getKey(Keyboard.KEY_NONE), Client::cycleSneakBehavior);
    }

    private static ActionableKeyBinding registerKeyBinding(String name, int defaultKey, Runnable callback) {
        ActionableKeyBinding keyBinding = new ActionableKeyBinding(
                String.format("key.%s.%s", TreeChopMod.MOD_ID, name),
                defaultKey,
                callback
        );

        ClientRegistry.registerKeyBinding(keyBinding);

        allKeyBindings.add(keyBinding);

        return keyBinding;
    }

    static int getKey(int key) {
        return key;
    }

    @SubscribeEvent
    public static void buttonPressed(InputEvent.KeyInputEvent event) {
        if (event.isCanceled()
                || Minecraft.getMinecraft().currentScreen != null
                || Minecraft.getMinecraft().world == null
                || !Keyboard.getEventKeyState()
                || Keyboard.isRepeatEvent()) {
            return;
        }

        for (ActionableKeyBinding keyBinding : allKeyBindings) {
            if (Keyboard.getEventKey() == keyBinding.getKeyCode()) {
                keyBinding.onPress();
                return;
            }
        }
    }

    private static class ActionableKeyBinding extends KeyBinding {

        private final Runnable callback;

        public ActionableKeyBinding(String resourceName, int inputByCode, Runnable callback) {
            super(resourceName, KeyConflictContext.GUI, inputByCode, CATEGORY);
            this.callback = callback;
        }

        public void onPress() {
            callback.run();
        }

    }
}